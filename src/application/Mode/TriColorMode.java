package application.Mode;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

import application.Main;
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
		mode = 2;
		
		this.level = level;
		String[] start = levels[level - 1].split(" ");
		level--;
		int n = 4 + level / 5;

		board = new Board(n, 3, level + 1, 2);
		gameMenu = new TriColorGameMenu();
		passLevel = new TriColorPassLevel(board.getCurLevel(), gameMenu.getPenalty());
		setToNextLevelButton(passLevel.getToNextLevelButton());
		setResetButton(gameMenu.getResetButton());
		setRestartButton(passLevel.getRestartButton());
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

		hBox.getChildren().addAll(board, gameMenu);
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
				gameMenu.addPenalty(999);
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
				gameMenu.addPenalty(999);
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
				
				//*
				Random rand = new Random();
				int sz = shouldPress.size();
				int idx = shouldPress.get(rand.nextInt(sz));
				int i = idx / n, j = idx % n;
				Light light = board.getLight(i, j);
				String style = light.getStyle();
				light.setMinSize();
				light.setStyle(style + "-fx-border-color: #000080; -fx-border-width: 3px;");
				//*/
				
				/*
				for(int i=0;i<shouldPress.size();i++)
				{
					int tmp=shouldPress.get(i);
					int x=tmp/n;
					int y=tmp%n;
					board.changeColor(x, y, true);
				}
				*/
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
				gameMenu.addPenalty(999);
				int n = 4 + (level-1) / 5;
				for(int i=0;i<n;i++)
				{
					for(int j=0;j<n;j++)
					{
						System.out.print(board.getLight(i, j).isShouldPress()+" ");
					}
					System.out.println("");
				}
			}
		});
	}

}