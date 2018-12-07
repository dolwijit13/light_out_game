package application.Mode;

import application.Main;
import application.Button.ToMainMenuButton;
import application.LevelSelection.ClassicLevelSelection;
import application.LevelSelection.DrawLevelSelection;
import application.LevelSelection.TriColorLevelSelection;
import application.PlayerData.PlayerInfo;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class ModeSelection extends VBox
{
	
	private class ModeFrame extends VBox
	{
		Text modeName;
		Button playButton;
		Button howToPlayButton;
		public ModeFrame(int mode, boolean isLocked)
		{
			super(10);
			setAlignment(Pos.CENTER);
			setPrefHeight(250);
			setPrefWidth(400);
			playButton = new Button("Play");
			howToPlayButton = new Button("How to play");
			switch(mode) {
				case 0:
					modeName = new Text("CLASSIC MODE");
					playButton.setOnMouseClicked(new EventHandler<MouseEvent>()
					{
						@Override
						public void handle(MouseEvent event)
						{
							ClassicLevelSelection classicLevelSelection = new ClassicLevelSelection();
							Main.changeScene(classicLevelSelection);
						}

					});
					setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
					break;
				case 1:
					modeName = new Text("TIMER MODE");
					playButton.setOnMouseClicked(new EventHandler<MouseEvent>()
					{
						@Override
						public void handle(MouseEvent event)
						{
							TimerMode timerMode = new TimerMode(1,60,0,0,null);
							Main.changeScene(timerMode);
						}

					});
					setBackground(new Background(new BackgroundFill(Color.YELLOW, null, null)));
					break;
				case 2:
					modeName = new Text("DRAW MODE");
					playButton.setOnMouseClicked(new EventHandler<MouseEvent>()
					{
						@Override
						public void handle(MouseEvent event)
						{
							DrawLevelSelection drawLevelSelection = new DrawLevelSelection();
							Main.changeScene(drawLevelSelection);
						}

					});
					setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));
					break;
				case 3:
					modeName = new Text("TRICOLOR MODE");
					playButton.setOnMouseClicked(new EventHandler<MouseEvent>()
					{
						@Override
						public void handle(MouseEvent event)
						{
							TriColorLevelSelection triColorLevelSelection = new TriColorLevelSelection();
							Main.changeScene(triColorLevelSelection);
						}

					});
					setBackground(new Background(new BackgroundFill(Color.BLUE, null, null)));
					break;
			}
			modeName.setStyle("-fx-font-size: 20px; -fx-text-fill: white; -fx-font-family:\"Arial Black\";-fx-fill: #555;");
			getChildren().add(modeName);
			if(isLocked) {
				
			}else {
				getChildren().addAll(playButton,howToPlayButton);
			}
		}
	}

	public ModeSelection()
	{
		super(10);
		setAlignment(Pos.CENTER);
		setPadding(new Insets(10, 10, 10, 10));
		setBackground(new Background(new BackgroundFill(Color.LIGHTGREY, null, null)));
		Text sceneTitle = new Text("SELECT MODE");
		sceneTitle.setStyle("-fx-font-size: 32px; -fx-font-family:\"Arial Black\";-fx-fill: #555;");
		
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setVgap(15);
		grid.setHgap(15);
		
		ModeFrame classicFrame = new ModeFrame(0,false);
		ModeFrame timerFrame = new ModeFrame(1,PlayerInfo.getClassicPassedLevel() < 5);
		ModeFrame drawFrame = new ModeFrame(2,PlayerInfo.getClassicPassedLevel() < 10);
		ModeFrame triColorFrame = new ModeFrame(3,PlayerInfo.getClassicPassedLevel() < 15);
		grid.add(classicFrame, 1, 1);
		grid.add(timerFrame, 2, 1);
		grid.add(drawFrame, 1, 2);
		grid.add(triColorFrame, 2, 2);
		
		ToMainMenuButton toMainMenuButton = new ToMainMenuButton();
		
		this.getChildren().addAll(sceneTitle,grid,toMainMenuButton);
		
	}
	
}