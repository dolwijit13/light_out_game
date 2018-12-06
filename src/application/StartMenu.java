package application;

import application.PlayerData.CreateNewPlayer;
import application.PlayerData.LeaderBoard;
import application.PlayerData.LoadingSelection;
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
		Button leaderboard = new Button("Leaderboard");
		Button exit = new Button("Exit");
		add(logo, 0, 1);
		add(newGame, 0, 2);
		add(loadGame, 0, 3);
		add(leaderboard, 0, 4);
		add(exit, 0, 5);

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
				LoadingSelection loadingSelection = new LoadingSelection(true);
				Main.changeScene(loadingSelection);
			}

		});
		
		leaderboard.setOnMouseClicked(new EventHandler<MouseEvent>()
		{
			@Override
			public void handle(MouseEvent event)
			{
				LeaderBoard leaderBoard = new LeaderBoard(0);
				Main.changeScene(leaderBoard);
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
