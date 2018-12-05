package application.Mode;

import java.util.Deque;
import java.util.LinkedList;

import application.GameLogic.Board;
import application.GameLogic.Light;
import application.GameMenu.ClassicGameMenu;
import application.GameMenu.GameMenu;
import application.PassLevel.PassLevel;
import application.PlayerData.PlayerInfo;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public abstract class Mode extends StackPane
{
	protected int mode;
	protected Board board;
	protected GameMenu gameMenu;
	protected HBox hBox;
	protected PassLevel passLevel;
	protected Deque<Integer> undoDeq;

	public Mode()
	{
		setAlignment(Pos.CENTER);
		hBox = new HBox(10);
		hBox.setPadding(new Insets(10, 10, 10, 10));
		undoDeq = new LinkedList<Integer>();
		getChildren().addAll(hBox);
	}

	protected void setToNextLevelButton(Button toNextLevelButton)
	{
		toNextLevelButton.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent arg0)
			{
				toNextLevel();
			}
		});
	}

	protected void setRestartButton(Button restartButton)
	{
		restartButton.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent arg0)
			{
				resetBoard();
			}
		});
	}
	
	protected void setUndoButton(Button undoButton)
	{
		undoButton.setDisable(true);
		undoButton.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent arg0)
			{
				undoBoard();
			}
		});
	}

	protected final EventHandler<MouseEvent> mouseClick = new EventHandler<MouseEvent>()
	{
		@Override
		public void handle(MouseEvent event)
		{
			MouseButton button = event.getButton();
			int n = board.getN();
			if (button == MouseButton.PRIMARY)
			{
				Light b = (Light) event.getSource();
				int ID = Integer.parseInt(b.getId());
				int x = ID / n, y = ID % n; // System.out.println("a " +ID+" "+x+" "+y);
				board.changeColor(x, y, true);
				undoDeq.addLast(ID);
				while(undoDeq.size() >5)
					undoDeq.removeFirst();
			}
			else if (button == MouseButton.SECONDARY)
			{
				Light b = (Light) event.getSource();
				int ID = Integer.parseInt(b.getId());
				int x = ID / n, y = ID % n; // System.out.println("a " +ID+" "+x+" "+y);
				board.changeColor(x, y, false);
			}
			gameMenu.addPenalty(5);
			if (isWinLevel() && mode != 1)
			{
				setPenalty();
				showPassLevel();
			}else if(isWinLevel() && mode == 1){
				toNextLevel();
			}
			if (board.canHelp1() && gameMenu instanceof ClassicGameMenu)
			{
				((ClassicGameMenu) gameMenu).getHelp1Button().setDisable(false);
			}
			else if (gameMenu instanceof ClassicGameMenu)
			{
				((ClassicGameMenu) gameMenu).getHelp1Button().setDisable(true);
			}
			Button undoButton = gameMenu.getUndoButton();
			undoButton.setDisable(false);
		}
	};

	public boolean isWinLevel()
	{
		int n = board.getN();
		for (int i = 0; i < n; i++)
		{
			for (int j = 0; j < n; j++)
			{
				if (board.getLight(i, j).getCurrentState() != 0)
				{
					return false;
				}
			}
		}
		//return false;
		return true;
	}

	public void showPassLevel()
	{
		// passLevel = new PassLevel(board.getCurLevel(), gameMenu.getPenalty());
		passLevel.setPenalty(gameMenu.getPenalty());
		getChildren().add(passLevel);
	}

	protected abstract void toNextLevel();

	protected abstract void resetBoard();
	
	protected abstract void setPenalty();
	
	protected void undoBoard()
	{
		if(undoDeq.isEmpty())
			return;
		int lastId = undoDeq.getLast();
		undoDeq.removeLast();
		int n=board.getN();
		board.changeColor(lastId/n, lastId%n, true);
		if(this instanceof TriColorMode)
		{
			board.changeColor(lastId/n, lastId%n, true);
		}
		gameMenu.addPenalty(-5);
		if(undoDeq.isEmpty())
		{
			Button undoButton = gameMenu.getUndoButton();
			undoButton.setDisable(true);
		}
	}
}
