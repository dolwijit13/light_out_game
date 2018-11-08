package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ClassicMode extends Mode
{

	private static String levelFile = "level.txt";
	private static String[] levels;

	public ClassicMode(int level)
	{
		String[] start = levels[level - 1].split(" ");
		level--;
		int n = 5 + level / 5;
		Board board = new Board(n, 2, level+1);
		for (int i = 0; i < start.length; i++)
		{
			int temp = Integer.parseInt(start[i]);
			board.changeColor(temp / n, temp % n, true);
		}
		GameMenu gameMenu = new ClassicGameMenu(10);
		getChildren().addAll(board, gameMenu);
	}

	public static void readLevel()
	{
		File file = null;
		BufferedReader fileReader = null;
		String thisLine = null;
		try
		{
			file = new File(levelFile);
			fileReader = new BufferedReader(new FileReader(file));
			while ((thisLine = fileReader.readLine()) != null)
			{
				levels = thisLine.split("-");
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				fileReader.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
}
