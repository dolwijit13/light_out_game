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
		//System.out.println(PlayerInfo.getSelectedPlayerInfo().name);
		setAlignment(Pos.CENTER);
		setVgap(10);
		setHgap(10);
		setPadding(new Insets(5, 5, 5, 5));
		Button logo = new Button("AU");
		Button play = new Button("Play");
		Button gallery = new Button("Gallery");
		Button howToPlay = new Button("How to play");
		Button leaderboard = new Button("Leaderboard");
		Button backAndSave = new Button("Back");
		add(logo, 0, 1);
		add(play, 0, 2);
		add(gallery, 0, 3);
		add(howToPlay, 0, 4);
		add(leaderboard, 0, 5);
		add(backAndSave, 0, 6);
		play.setOnMouseClicked(new EventHandler<MouseEvent>()
		{
			@Override
			public void handle(MouseEvent event)
			{
				ModeSelection modeSelection = new ModeSelection();
				Main.changeScene(modeSelection);
			}

		});
		
		gallery.setOnMouseClicked(new EventHandler<MouseEvent>()
		{
			@Override
			public void handle(MouseEvent event)
			{
				Gallery gallery = new Gallery();
				Main.changeScene(gallery);
			}

		});

		backAndSave.setOnMouseClicked(new EventHandler<MouseEvent>()
		{
			@Override
			public void handle(MouseEvent event)
			{
				SavingSelection savingSelection = new SavingSelection();
				Main.changeScene(savingSelection);
			}
		});
	}
}
