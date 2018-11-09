package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class ClassicMode extends Mode
{

	private static String levelFile = "level.txt";
	private static String[] levels;

	public ClassicMode(int level)
	{
		String[] start = levels[level - 1].split(" ");
		level--;
		int n = 5 + level / 5;
		
		board = new Board(n, 2, level+1);
		gameMenu = new ClassicGameMenu();
		passLevel = new PassLevel(board.getCurLevel(), gameMenu.getPenalty());
		setToNextLevelButton(passLevel.getToNextLevelButton());
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
