package application.Mode;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

import application.Main;
import application.Button.GameMenuButton;
import application.GameLogic.Board;
import application.GameLogic.Light;
import application.GameMenu.TriColorGameMenu;
import application.PassLevel.TriColorPassLevel;
import application.PlayerData.PlayerInfo;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class TriColorMode extends Mode
{
	private static InputStream levelFile = TriColorMode.class.getClassLoader().getResourceAsStream("TriColorLevel.txt");
	private static String[] levels;
	protected int level;

	public TriColorMode(int level)
	{
		mode = 3;
		
		this.level = level;
		String[] start = levels[level - 1].split(" ");
		level--;
		int n = 4 + level / 5;

		board = new Board(n, 3, level + 1, mode);
		gameMenu = new TriColorGameMenu(level+1);
		setResetButton(gameMenu.getResetButton());
		setUndoButton(gameMenu.getUndoButton());
		setHelp1Button(((TriColorGameMenu) gameMenu).getHelp1Button());
		setHelp2Button(((TriColorGameMenu) gameMenu).getHelp2Button());
		setHelp3Button(((TriColorGameMenu) gameMenu).getHelp3Button());
		for (int i = 0; i < start.length; i++)
		{
			int temp = Integer.parseInt(start[i]);
			board.changeColor(temp / n, temp % n, true);
		}

		for (int i = 0; i < n; i++)
		{
			for (int j = 0; j < n; j++)
			{
				board.getLight(i, j).setOnMouseClicked(mouseClick);
			}
		}

		modeHBox.getChildren().addAll(board, gameMenu);
	}

	public static void readLevel()
	{
		try
		{
			String levelString = "";
			char c = '1';
			while (c != '*')
			{
				c = (char) levelFile.read();
				if (c == '*')
					break;
				levelString += c;
			}
			levels = levelString.split("-");

		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	protected void toNextLevel()
	{
		TriColorMode nextLevel = new TriColorMode(board.getCurLevel() + 1);
		Main.changeScene(nextLevel);
	}

	@Override
	protected void resetBoard()
	{
		Main.changeScene(new TriColorMode(board.getCurLevel()));
	}

	@Override
	protected void setPenalty()
	{
		PlayerInfo.setTriColorPassedLevel(level);
		PlayerInfo.setTriColorPenalty(level, gameMenu.getPenalty());
	}

	private void setResetButton(Button resetButton)
	{
		resetButton.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent arg0)
			{
				GameMenuButton.playSoundEffect();
				resetBoard();
			}
		});
	}

	private void setHelp1Button(Button help1Button)
	{
		help1Button.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent arg0)
			{
				gameMenu.addPenalty(250);
				setPenalty();
				int n=board.getN();
				for(int i=0 ;i<n;i++)
				{
					for(int j=0;j<n;j++)
					{
						if(board.getLight(i, j).getCurrentState()!=0)
						{
							board.getLight(i, j).changeColor();
						}
						if(board.getLight(i, j).getCurrentState()!=0)
						{
							board.getLight(i, j).changeColor();
						}
					}
				}
				showPassLevel();
			}
		});
	}

	private void setHelp2Button(Button help2Button)
	{
		help2Button.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent arg0)
			{
				gameMenu.addPenalty(50);
				board.setCanHelp2(false);
				help2Button.setDisable(true);
				int n = 4 + (level-1) / 5;
				ArrayList<Integer> shouldPress = new ArrayList<Integer>();
				for (int i= 0; i < n; i++)
				{
					for(int j=0;j<n;j++)
					{
						if(board.getLight(i, j).isShouldPress())
						{
							shouldPress.add(i*n+j);
						}
					}
				}
				
				Random rand = new Random();
				int sz = shouldPress.size();
				int idx = shouldPress.get(rand.nextInt(sz));
				int i = idx / n, j = idx % n;
				Light light = board.getLight(i, j);
				light.setMinSize();
				light.setBorder("-fx-border-color: #FF0000; -fx-border-width: 3px;");
			}
		});
	}
	
	private void setHelp3Button(Button help3Button)
	{
		help3Button.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent arg0)
			{
				gameMenu.addPenalty(15);
				showHelp3(3,4 + (level-1) / 5);
			}
		});
	}
	
	@Override
	public void showPassLevel()
	{
		TriColorPassLevel passLevel = new TriColorPassLevel(board.getCurLevel(), gameMenu.getPenalty());
		setToNextLevelButton(passLevel.getToNextLevelButton());
		setRestartButton(passLevel.getRestartButton());
		Main.playSoundEffect("congrats.mp3");
		disableBoard();
		passLevel.setPenalty(gameMenu.getPenalty());
		modeHBox.getChildren().remove(gameMenu);
		modeHBox.getChildren().add(passLevel);
	}

}
