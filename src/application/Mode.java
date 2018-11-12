package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public abstract class Mode extends StackPane
{
	Board board;
	GameMenu gameMenu;
	HBox hBox;
	PassLevel passLevel;

	public Mode()
	{
		setAlignment(Pos.CENTER);
		hBox = new HBox(10);
		hBox.setPadding(new Insets(10, 10, 10, 10));
		
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
			}
			else if (button == MouseButton.SECONDARY)
			{
				Light b = (Light) event.getSource();
				int ID = Integer.parseInt(b.getId());
				int x = ID / n, y = ID % n; // System.out.println("a " +ID+" "+x+" "+y);
				board.changeColor(x, y, false);
			}
			gameMenu.addPenalty(5);
			if (isWinLevel())
			{
				showPassLevel();
			}
			if(board.canHelp1() && gameMenu instanceof ClassicGameMenu) {
				((ClassicGameMenu) gameMenu).getHelp1Button().setDisable(false);
			}
			else if(gameMenu instanceof ClassicGameMenu) {
				((ClassicGameMenu) gameMenu).getHelp1Button().setDisable(true);
			}
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
		return true;
	}

	public void showPassLevel()
	{
		//passLevel = new PassLevel(board.getCurLevel(), gameMenu.getPenalty());
		passLevel.setPenalty(gameMenu.getPenalty());
		getChildren().add(passLevel);
	}
	
	protected abstract void toNextLevel();
	
	protected abstract void resetBoard();
}
