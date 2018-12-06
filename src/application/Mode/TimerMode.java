package application.Mode;

import java.util.Random;

import application.Main;
import application.GameLogic.Board;
import application.GameMenu.TimerGameMenu;
import application.PassLevel.ClassicPassLevel;
import application.PlayerData.PlayerInfo;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class TimerMode extends Mode{
	
	private int n;
	private int passedLevel;
	private int level;
	private int timeLeft;
	private int[] pressed;
	private Thread timerThread;
	
	public TimerMode(int level, int time, int penalty, int passedLevel, int[] tmp)
	{
		mode = 1;
		this.passedLevel = passedLevel;
		this.timeLeft = time;
		this.level = level;
		this.n = (level-1)/5;
		board = new Board(n+4, 2, level, 1);
		gameMenu = new TimerGameMenu(0);
		gameMenu.addPenalty(penalty); 
		((TimerGameMenu) gameMenu).setPassedLevelLabel(passedLevel);
		setNewPuzzleButton(((TimerGameMenu) gameMenu).getNewPuzzleButton());
		passLevel = new ClassicPassLevel(board.getCurLevel(), gameMenu.getPenalty());
		setResetButton(gameMenu.getResetButton());
		setUndoButton(gameMenu.getUndoButton());
		if(tmp == null) {
			System.out.println("gen");
			tmp = new int[2*n+3 + (level-1)%5];
			Random rand = new Random();
			for(int i = 0; i < tmp.length; i++) {
				boolean chk = false;
				while(!chk) {
					boolean chk2 = false;
					int t = rand.nextInt((n+4)*(n+4));
					for(int j = 0; j < tmp.length; j++) {
						if(tmp[j] == t) {
							chk2 = true;
							break;
						}
					}
					if(!chk2) {
						tmp[i] = t;
						System.out.println(t);
						chk = true;
					}
				}
			}
		}
		pressed = tmp;
		for(int i = 0; i < pressed.length; i++) {
			board.changeColor(pressed[i]/(n+4), pressed[i]%(n+4), true);
		}
		for (int i = 0; i < n+4; i++)
		{
			for (int j = 0; j < n+4; j++)
			{
				board.getLight(i, j).setOnMouseClicked(mouseClick);
			}
		}
		hBox.getChildren().addAll(board, gameMenu);
		
		timerThread = new Thread(() -> {
			while(true){
				try {
					((TimerGameMenu) gameMenu).setTimeLeft(timeLeft);
					((TimerGameMenu) gameMenu).drawCurrentTimeString(((TimerGameMenu) gameMenu).getGc());
					Thread.sleep(1000);
					timeLeft--;
					//System.out.println(timeLeft);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("Stop Timer Thread");
					break;
				}
			}
		});
		timerThread.start();
	}

	private void setResetButton(Button resetButton)
	{
		resetButton.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent arg0)
			{
				resetBoard();
			}
		});
	}
	
	private void setNewPuzzleButton(Button newPuzzleButton)
	{
		newPuzzleButton.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent arg0)
			{
				timerThread.suspend();
				TimerMode timerMode = new TimerMode(level, timeLeft, gameMenu.getPenalty()+500, passedLevel, null);
				Main.changeScene(timerMode);
			}
		});
	}
	
	@Override
	protected void toNextLevel() {
		// TODO Auto-generated method stub
		timerThread.suspend();
		TimerMode nextLevel = new TimerMode(level+1, timeLeft, gameMenu.getPenalty(), passedLevel+1, null);
		Main.changeScene(nextLevel);
	}

	@Override
	protected void resetBoard() {
		timerThread.suspend();
		TimerMode timerMode = new TimerMode(level, timeLeft, gameMenu.getPenalty(), passedLevel, pressed);
		Main.changeScene(timerMode);
	}

	@Override
	protected void setPenalty() {
		return;
	}

}
