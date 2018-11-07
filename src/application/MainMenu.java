package application;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class MainMenu extends GridPane
{
	public MainMenu()
	{
		setAlignment(Pos.CENTER);
		setVgap(10);
		setHgap(10);
		setPadding(new Insets(5, 5, 5, 5));
		Button logo = new Button("AU");
		Button play = new Button("Play");
		Button gallery = new Button("Gallery");
		Button howToPlay = new Button("How to play");
		Button leaderboard = new Button("Leaderboard");
		Button back = new Button("Back");
		add(logo, 0, 1);
		add(play, 0, 2);
		add(gallery, 0, 3);
		add(howToPlay, 0, 4);
		add(leaderboard, 0, 5);
		add(back, 0, 6);
		play.setOnMouseClicked(new EventHandler<MouseEvent>()
		{
			@Override
			public void handle(MouseEvent event)
			{
				LevelSelection levelSelection = new LevelSelection();
				Main.changeScene(levelSelection);
			}

		});

		back.setOnMouseClicked(new EventHandler<MouseEvent>()
		{
			@Override
			public void handle(MouseEvent event)
			{
				StartMenu startMenu = new StartMenu();
				Main.changeScene(startMenu);
			}

		});
	}
}
