package application.Mode;

import java.io.IOException;
import java.io.InputStream;

import application.GameLogic.Board;
import application.GameMenu.ClassicGameMenu;
import application.GameMenu.TriColorGameMenu;
import application.PassLevel.PassLevel;

public class TriColorMode extends Mode
{
	private static InputStream levelFile = TriColorMode.class.getClassLoader().getResourceAsStream("TriColorLevel.txt");
	private static String[] levels;
	protected int level;
	
	public TriColorMode(int level)
	{
		this.level = level;
		String[] start = levels[level - 1].split(" ");
		level--;
		int n = 4 + level / 5;

		board = new Board(n, 2, level + 1,0);
		gameMenu = new TriColorGameMenu();
		passLevel = new PassLevel(board.getCurLevel(), gameMenu.getPenalty());
		setToNextLevelButton(passLevel.getToNextLevelButton());
		setResetButton(gameMenu.getResetButton());
		setRestartButton(passLevel.getRestartButton());
		setUndoButton(gameMenu.getUndoButton());
		setHelp1Button(((ClassicGameMenu) gameMenu).getHelp1Button());
		setHelp2Button(((ClassicGameMenu) gameMenu).getHelp2Button());
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
			String levelString="";
			char c ='1';
			while(c!='*')
			{
				c = (char) levelFile.read();
				if(c=='*')
					break;
				levelString+=c;
			}
			levels=levelString.split("-");
			
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	protected void toNextLevel()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void resetBoard()
	{
		// TODO Auto-generated method stub
		
	}

}
