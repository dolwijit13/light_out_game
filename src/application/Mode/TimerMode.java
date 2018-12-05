package application.Mode;

import java.util.Random;

import application.Main;
import application.GameLogic.Board;
import application.GameMenu.TimerGameMenu;
import application.PassLevel.ClassicPassLevel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class TimerMode extends Mode{
	
	private int n;
	private int level;
	private int timeLeft;
	private int[] pressed;
	private int oldPenalty;
	
	public TimerMode(int level, int timeLeft, int penalty, int[] tmp)
	{
		mode = 1;
		this.level = level;
		n = (level-1)/5;
		board = new Board(n+4, 2, level, 1);
		gameMenu = new TimerGameMenu(0);
		oldPenalty = penalty;
		gameMenu.addPenalty(penalty); 
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
	
	@Override
	protected void toNextLevel() {
		// TODO Auto-generated method stub
		TimerMode nextLevel = new TimerMode(level+1, timeLeft, gameMenu.getPenalty(), null);
		Main.changeScene(nextLevel);
	}

	@Override
	protected void resetBoard() {
		TimerMode timerMode = new TimerMode(level, timeLeft, oldPenalty, pressed);
		Main.changeScene(timerMode);
	}

	@Override
	protected void setPenalty() {
		// TODO Auto-generated method stub
		return;
	}

}
