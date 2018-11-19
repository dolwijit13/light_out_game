package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class StartMenu extends GridPane
{
	public StartMenu()
	{
		setAlignment(Pos.CENTER);
		setVgap(10);
		setHgap(10);
		setPadding(new Insets(5, 5, 5, 5));
		Button logo = new Button("AU");
		Button newGame = new Button("New Game");
		Button loadGame = new Button("Load Game");
		Button exit = new Button("Exit");
		add(logo, 0, 1);
		add(newGame, 0, 2);
		add(loadGame, 0, 3);
		add(exit, 0, 4);

		newGame.setOnMouseClicked(new EventHandler<MouseEvent>()
		{
			@Override
			public void handle(MouseEvent event)
			{
				CreateNewPlayer creatNewPlayer = new CreateNewPlayer();
				Main.changeScene(creatNewPlayer);
			}

		});
		
		loadGame.setOnMouseClicked(new EventHandler<MouseEvent>()
		{
			@Override
			public void handle(MouseEvent event)
			{
				LoadingSelection loadingSelection = new LoadingSelection();
				Main.changeScene(loadingSelection);
			}

		});

		exit.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				Main.exit();
			}
		});
	}
}
