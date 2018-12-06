package application.Mode;

import java.util.Random;

import application.Main;
import application.GameLogic.Board;
import application.GameMenu.TimerGameMenu;
import application.PassLevel.ClassicPassLevel;
import application.PassLevel.TimerPassLevel;
import application.PlayerData.PlayerInfo;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class TimerMode extends Mode
{

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
		this.n = (level - 1) / 5;
		board = new Board(n + 4, 2, level, 1);
		gameMenu = new TimerGameMenu(0);
		gameMenu.addPenalty(penalty);
		passLevel = new TimerPassLevel(gameMenu.getPenalty(), passedLevel);
		setRestartButton(passLevel.getRestartButton());
		((TimerGameMenu) gameMenu).setPassedLevelLabel(passedLevel);
		setNewPuzzleButton(((TimerGameMenu) gameMenu).getNewPuzzleButton());
		setResetButton(gameMenu.getResetButton());
		setUndoButton(gameMenu.getUndoButton());
		if (tmp == null)
		{
			System.out.println("gen");
			tmp = new int[2 * n + 3 + (level - 1) % 5];
			Random rand = new Random();
			for (int i = 0; i < tmp.length; i++)
			{
				boolean chk = false;
				while (!chk)
				{
					boolean chk2 = false;
					int t = rand.nextInt((n + 4) * (n + 4));
					for (int j = 0; j < tmp.length; j++)
					{
						if (tmp[j] == t)
						{
							chk2 = true;
							break;
						}
					}
					if (!chk2)
					{
						tmp[i] = t;
						System.out.println(t);
						chk = true;
					}
				}
			}
		}
		pressed = tmp;
		for (int i = 0; i < pressed.length; i++)
		{
			board.changeColor(pressed[i] / (n + 4), pressed[i] % (n + 4), true);
		}
		for (int i = 0; i < n + 4; i++)
		{
			for (int j = 0; j < n + 4; j++)
			{
				board.getLight(i, j).setOnMouseClicked(mouseClick);
			}
		}
		hBox.getChildren().addAll(board, gameMenu);

		timerThread = new Thread(() -> {
			while (true)
			{
				try
				{
					((TimerGameMenu) gameMenu).setTimeLeft(timeLeft);
					((TimerGameMenu) gameMenu).drawCurrentTimeString(((TimerGameMenu) gameMenu).getGc());
					Thread.sleep(1000);
					timeLeft--;
					if(timeLeft == 0) {
						javafx.application.Platform.runLater(new Runnable() {
				            @Override public void run() {
				                showPassLevel();
				            }
				        });
						break;
					}
					 System.out.println(timeLeft);
				}
				catch (InterruptedException e)
				{
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
				TimerMode timerMode = new TimerMode(level, timeLeft, gameMenu.getPenalty(), passedLevel, pressed);
				Main.changeScene(timerMode);
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
				TimerMode timerMode = new TimerMode(level, timeLeft, gameMenu.getPenalty() + 500, passedLevel, null);
				Main.changeScene(timerMode);
			}
		});
	}

	@Override
	protected void toNextLevel()
	{
		// TODO Auto-generated method stub
		TimerMode nextLevel = new TimerMode(level + 1, timeLeft + 15, gameMenu.getPenalty(), passedLevel + 1, null);
		Main.changeScene(nextLevel);
	}

	@Override
	protected void resetBoard()
	{
		TimerMode timerMode = new TimerMode(1, 60, 0, 0, null);
		Main.changeScene(timerMode);
	}

	@Override
	protected void setPenalty()
	{
		PlayerInfo.setTimerPassedLevel(passedLevel);
		PlayerInfo.setTimerPenalty(gameMenu.getPenalty());
		return;
	}
	
	@Override
	public void timerNextLevel() {
		timerThread.interrupt();
		for (int i = 0; i < n + 4; i++)
		{
			for (int j = 0; j < n + 4; j++)
			{
				board.getLight(i, j).setOnMouseClicked(null);
			}
		}
		Thread t = new Thread(() -> {
			for(int i = 0; i < (n+4)*(n+4); i++)
			{
				try
				{
					board.changeColor(i/(n+4), i%(n+4), false);;
					Thread.sleep(75);
					board.changeColor(i/(n+4), i%(n+4), false);;
					Thread.sleep(75);
					// System.out.println(timeLeft);
				}
				catch (InterruptedException e)
				{
					System.out.println("Stop Timer Thread");
					break;
				}
			}
			javafx.application.Platform.runLater(new Runnable() {
	            @Override public void run() {
	                toNextLevel();
	            }
	        });
		});
		t.start();
	}
}