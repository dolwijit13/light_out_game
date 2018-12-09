package application.Mode;

import application.Main;
import application.Button.BackButton;
import application.Button.OKButton;
import application.Button.PictureWithTextButton;
import application.Button.ToMainMenuButton;
import application.LevelSelection.ClassicLevelSelection;
import application.LevelSelection.DrawLevelSelection;
import application.LevelSelection.TriColorLevelSelection;
import application.PlayerData.PlayerInfo;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class ModeSelection extends StackPane
{
	VBox vBox;
	
	private class ModeFrame extends VBox
	{
		Label modeName;
		PictureWithTextButton playButton;
		PictureWithTextButton howToPlayButton;
		public ModeFrame(int mode, boolean isLocked)
		{
			super(13);
			setAlignment(Pos.TOP_CENTER);
			setPrefHeight(220);
			setPrefWidth(450);
			//playButton = new Button("Play");
			VBox buttonVBox = new VBox(13);
			buttonVBox.setPadding(new Insets(0, 106, 0, 106));
			playButton = new PictureWithTextButton(59, 188, 6, "PLAY",34);
			howToPlayButton = new PictureWithTextButton(59, 188, 6, "HOW TO PLAY",26);
			buttonVBox.getChildren().addAll(playButton,howToPlayButton);
			howToPlayButton.setOnMouseClicked(new EventHandler<MouseEvent>()
			{
				@Override
				public void handle(MouseEvent event)
				{
					showHowToPlay(mode);
				}

			});
			switch(mode) {
				case 0:
					modeName = new Label("CLASSIC MODE");
					modeName.setStyle("-fx-font-size: 30px; -fx-font-family:\"Arial Black\"; -fx-background-color: #FF4343; -fx-background-radius: 10;");
					playButton.setOnMouseClicked(new EventHandler<MouseEvent>()
					{
						@Override
						public void handle(MouseEvent event)
						{
							ClassicLevelSelection classicLevelSelection = new ClassicLevelSelection();
							Main.changeScene(classicLevelSelection);
						}

					});
					setStyle("-fx-background-color: #FFC2C2; -fx-background-radius: 10;");
					break;
				case 1:
					modeName = new Label("TIMER MODE");
					modeName.setStyle("-fx-font-size: 30px; -fx-font-family:\"Arial Black\"; -fx-background-color: #FFD728; -fx-background-radius: 10;");
					playButton.setOnMouseClicked(new EventHandler<MouseEvent>()
					{
						@Override
						public void handle(MouseEvent event)
						{
							TimerMode timerMode = new TimerMode(1,60,0,0,null);
							Main.changeScene(timerMode);
						}

					});
					setStyle("-fx-background-color: #FFED9F; -fx-background-radius: 10;");
					break;
				case 2:
					modeName = new Label("DRAW MODE");
					modeName.setStyle("-fx-font-size: 30px; -fx-font-family:\"Arial Black\"; -fx-background-color: #2BCA2F; -fx-background-radius: 10;");
					playButton.setOnMouseClicked(new EventHandler<MouseEvent>()
					{
						@Override
						public void handle(MouseEvent event)
						{
							DrawLevelSelection drawLevelSelection = new DrawLevelSelection();
							Main.changeScene(drawLevelSelection);
						}

					});
					setStyle("-fx-background-color: #99C99A; -fx-background-radius: 10;");
					break;
				case 3:
					modeName = new Label("TRICOLOR MODE");
					modeName.setStyle("-fx-font-size: 30px; -fx-font-family:\"Arial Black\"; -fx-background-color: #3F94FF; -fx-background-radius: 10;");
					playButton.setOnMouseClicked(new EventHandler<MouseEvent>()
					{
						@Override
						public void handle(MouseEvent event)
						{
							TriColorLevelSelection triColorLevelSelection = new TriColorLevelSelection();
							Main.changeScene(triColorLevelSelection);
						}

					});
					setStyle("-fx-background-color: #95C4FF; -fx-background-radius: 10;");
					break;
			}
			//modeName.setStyle("-fx-font-size: 20px; -fx-text-fill: white; -fx-font-family:\"Arial Black\";");
			modeName.setAlignment(Pos.CENTER);
			modeName.setPrefHeight(60);
			modeName.setPrefWidth(450);
			getChildren().add(modeName);
			if(isLocked) {
				HBox lockedModeHBox = new HBox(10);
				VBox lockedText = new VBox();
				lockedText.setAlignment(Pos.CENTER);
				Label lockedLabel = new Label("Unlock this mode by completing");
				lockedLabel.setStyle("-fx-font-size: 18px; -fx-font-family:\"Arial Black\";");
				Label conditionLabel = null;
				switch(mode) {
					case 1:
						conditionLabel = new Label("5 in Classic Mode.");
						break;
					case 2:
						conditionLabel = new Label("10 in Classic Mode.");
						break;
					case 3:
						conditionLabel = new Label("15 in Classic Mode.");
						break;
				}
				conditionLabel.setStyle("-fx-font-size: 18px; -fx-font-family:\"Arial Black\";");
				lockedText.getChildren().addAll(lockedLabel,conditionLabel);
				ImageView lockedMode = new ImageView(new Image(ClassLoader.getSystemResource("assets/locked_mode.png").toString()));
				lockedMode.setFitHeight(116.5);
				lockedMode.setFitWidth(101.5);
				lockedModeHBox.getChildren().addAll(lockedMode,lockedText);
				getChildren().add(lockedModeHBox);
			}else {
				getChildren().addAll(buttonVBox);
			}
		}
	}

	public ModeSelection()
	{
		setAlignment(Pos.CENTER);
		vBox = new VBox();
		vBox.setAlignment(Pos.TOP_CENTER);
		//vBox.setPadding(new Insets(10, 10, 10, 10));
		vBox.setBackground(new Background(new BackgroundFill(Color.web("#9D9D9D"), null, null)));
		Label sceneTitle = new Label("SELECT MODE");
		sceneTitle.setPrefHeight(150);
		sceneTitle.setPrefWidth(1280);
		sceneTitle.setAlignment(Pos.CENTER);
		sceneTitle.setStyle("-fx-font-size: 48px; -fx-text-fill: white; -fx-font-family:\"Arial Black\"; -fx-background-color: #4F4F4F; -fx-background-radius: 0;");
		
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setPadding(new Insets(0,0,10,0));
		grid.setVgap(10);
		grid.setHgap(10);
		grid.setStyle("-fx-background-color: rgba(255, 255, 255, 0.5);");
		
		ModeFrame classicFrame = new ModeFrame(0,false);
		ModeFrame timerFrame = new ModeFrame(1,PlayerInfo.getClassicPassedLevel() < 5);
		ModeFrame drawFrame = new ModeFrame(2,PlayerInfo.getClassicPassedLevel() < 10);
		ModeFrame triColorFrame = new ModeFrame(3,PlayerInfo.getClassicPassedLevel() < 15);
		grid.add(classicFrame, 1, 1);
		grid.add(timerFrame, 2, 1);
		grid.add(drawFrame, 1, 2);
		grid.add(triColorFrame, 2, 2);
		
		BackButton backButton = new BackButton(100,120,1);
		
		vBox.getChildren().addAll(sceneTitle,grid,backButton);
		
		getChildren().add(vBox);
		
	}
	
	private void showHowToPlay(int mode) {
		vBox.setDisable(true);
		VBox okButtonBox = new VBox();
		okButtonBox.setAlignment(Pos.CENTER_RIGHT);
		okButtonBox.setPadding(new Insets(0,200,0,0));
		OKButton okButton = new OKButton(100, 120);
		okButtonBox.getChildren().add(okButton);
		VBox howToPlay = new VBox();
		howToPlay.setPrefSize(900, 700);
		howToPlay.setAlignment(Pos.CENTER);
		okButton.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent arg0)
			{
				getChildren().remove(howToPlay);
				vBox.setDisable(false);
			}
		});
		ImageView help3 = new ImageView(new Image(ClassLoader.getSystemResource("assets/howtoplay/"+mode+".png").toString()));
		howToPlay.getChildren().addAll(help3,okButtonBox);
		getChildren().add(howToPlay);
	}
}