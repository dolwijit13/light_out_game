package application.Mode;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

import application.Main;
import application.GameLogic.Board;
import application.GameLogic.Light;
import application.GameMenu.drawGameMenu;
import application.PassLevel.drawPassLevel;
import application.PlayerData.PlayerInfo;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class DrawMode extends Mode
{
	private static InputStream levelFile = DrawMode.class.getClassLoader().getResourceAsStream("DrawLevel.txt");
	private static String[] levels;
	private static String[] initialBoards;
	private ArrayList<Integer>initalBoard;
	protected int level;

	public DrawMode(int level)
	{
		mode = 2;

		this.level = level;
		String[] start = levels[level - 1].split(" ");
		
		String[] initialTmp = initialBoards[level - 1].split(" ");
		initalBoard = new ArrayList<Integer>();
		level--;
		int n = 4 + level / 5;	

		board = new Board(n, 2, level + 1, 2);
		gameMenu = new drawGameMenu();
		passLevel = new drawPassLevel(board.getCurLevel(), gameMenu.getPenalty());
		setToNextLevelButton(passLevel.getToNextLevelButton());
		setResetButton(gameMenu.getResetButton());
		setRestartButton(passLevel.getRestartButton());
		setUndoButton(gameMenu.getUndoButton());
		setHelp1Button(((drawGameMenu) gameMenu).getHelp1Button());
		setHelp2Button(((drawGameMenu) gameMenu).getHelp2Button());
		setHelp3Button(((drawGameMenu) gameMenu).getHelp3Button());
		
		for (int i = 0; i < initialTmp.length; i++)
		{
			int temp = Integer.parseInt(initialTmp[i]);
			initalBoard.add(temp);
			board.changeColor(temp /n, temp %n, false);
		}	
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
			String initalString = "";
			int mode = 0;
			// 0->normal click 1->initail board
			char c = '1';
			while (c != '*')
			{
				c = (char) levelFile.read();
				if (c == '*')
				{
					break;
				}
				else if (c == '/')
				{
					mode = 1;
					continue;
				}
				else if (c == '-')
				{
					levelString += c;
					initalString += c;
					mode = 0;
					continue;
				}
				if (mode == 0)
				{
					levelString += c;
				}
				else
				{
					initalString += c;
				}
			}
			levels = levelString.split("-");
			initialBoards = initalString.split("-");

		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	protected void toNextLevel()
	{
		DrawMode nextLevel = new DrawMode(board.getCurLevel() + 1);
		Main.changeScene(nextLevel);
	}

	@Override
	protected void resetBoard()
	{
		Main.changeScene(new DrawMode(board.getCurLevel()));
	}

	@Override
	protected void setPenalty()
	{
		PlayerInfo.setDrawPassedLevel(level);
		PlayerInfo.setDrawPenalty(level, gameMenu.getPenalty());
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
				int n = 4 + (level - 1) / 5;
				ArrayList<Integer> shouldPress = new ArrayList<Integer>();
				for (int i = 0; i < n; i++)
				{
					for (int j = 0; j < n; j++)
					{
						if (board.getLight(i, j).isShouldPress())
						{
							shouldPress.add(i * n + j);
						}
					}
				}

				// *
				Random rand = new Random();
				int sz = shouldPress.size();
				int idx = shouldPress.get(rand.nextInt(sz));
				int i = idx / n, j = idx % n;
				Light light = board.getLight(i, j);
				String style = light.getStyle();
				light.setMinSize();
				light.setStyle(style + "-fx-border-color: #000080; -fx-border-width: 3px;");
				// */

				/*
				 * for(int i=0;i<shouldPress.size();i++) { int tmp=shouldPress.get(i); int
				 * x=tmp/n; int y=tmp%n; board.changeColor(x, y, true); }
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
				int n = 4 + (level - 1) / 5;
				for (int i = 0; i < n; i++)
				{
					for (int j = 0; j < n; j++)
					{
						System.out.print(board.getLight(i, j).isShouldPress() + " ");
					}
					System.out.println("");
				}
			}
		});
	}

	@Override
	public boolean isWinLevel()
	{
		int n = board.getN();
		for (int i = 0; i < n; i++)
		{
			for (int j = 0; j < n; j++)
			{
				int idx=i*n+j;
				int curState = board.getLight(i, j).getCurrentState();
				if(initalBoard.contains(idx) && curState==0)
				{
					return false;
				}
				else if(!initalBoard.contains(idx) && curState==1)
				{
					return false;
				}
			}
		}
		// return false;
		return true;
	}
}
