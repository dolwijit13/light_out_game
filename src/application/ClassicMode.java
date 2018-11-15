package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
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

		board = new Board(n, 2, level + 1);
		gameMenu = new ClassicGameMenu();
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

	@Override
	protected void toNextLevel()
	{
		ClassicMode nextLevel = new ClassicMode(board.getCurLevel() + 1);
		Main.changeScene(nextLevel);
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
				BoardSolver boardSolver;
				try
				{
					boardSolver = new BoardSolver(board);
					boardSolver.printShouldPress();
				}
				catch (FileNotFoundException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
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

	@Override
	protected void resetBoard()
	{
		Main.changeScene(new ClassicMode(board.getCurLevel()));
	}
}
