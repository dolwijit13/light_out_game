package application.Mode;

import application.Main;
import application.MainMenu;
import application.Button.BackButton;
import application.Button.OKButton;
import application.Button.PictureWithTextButton;
import application.LevelSelection.ClassicLevelSelection;
import application.LevelSelection.DrawLevelSelection;
import application.LevelSelection.TriColorLevelSelection;
import application.PlayerData.PlayerInfo;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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

public class ModeSelection extends StackPane {
	private VBox modeSelectionVBox;

	private class ModeFrame extends VBox {

		public ModeFrame(int mode, boolean isLocked) {
			super(13);
			setAlignment(Pos.TOP_CENTER);
			setPrefHeight(220);
			setPrefWidth(450);

			Label modeName = new Label("");
			modeName.setAlignment(Pos.CENTER);
			modeName.setPrefHeight(60);
			modeName.setPrefWidth(450);

			PictureWithTextButton playButton = new PictureWithTextButton(59, 188, 6, "PLAY", 34);
			PictureWithTextButton howToPlayButton = new PictureWithTextButton(59, 188, 6, "HOW TO PLAY", 26);
			howToPlayButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					PictureWithTextButton.playSoundEffect();
					showHowToPlay(mode);
				}
			});
			VBox buttonVBox = new VBox(13);
			buttonVBox.setPadding(new Insets(0, 106, 0, 106));
			buttonVBox.getChildren().addAll(playButton, howToPlayButton);
			switch (mode) {
			case 0:
				modeName.setText("CLASSIC MODE");
				modeName.setStyle(
						"-fx-font-size: 30px; -fx-font-family:\"Arial Black\"; -fx-background-color: #FF4343; -fx-background-radius: 10;");
				playButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						PictureWithTextButton.playSoundEffect();
						ClassicLevelSelection classicLevelSelection = new ClassicLevelSelection();
						Main.changeScene(classicLevelSelection);
					}
				});
				setStyle("-fx-background-color: #FFC2C2; -fx-background-radius: 10;");
				break;
			case 1:
				modeName.setText("TIMER MODE");
				modeName.setStyle(
						"-fx-font-size: 30px; -fx-font-family:\"Arial Black\"; -fx-background-color: #FFD728; -fx-background-radius: 10;");
				playButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						PictureWithTextButton.playSoundEffect();
						TimerMode timerMode = new TimerMode(1, 60, 0, 0, null);
						Main.changeScene(timerMode);
					}

				});
				setStyle("-fx-background-color: #FFED9F; -fx-background-radius: 10;");
				break;
			case 2:
				modeName.setText("DRAW MODE");
				modeName.setStyle(
						"-fx-font-size: 30px; -fx-font-family:\"Arial Black\"; -fx-background-color: #2BCA2F; -fx-background-radius: 10;");
				playButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						PictureWithTextButton.playSoundEffect();
						DrawLevelSelection drawLevelSelection = new DrawLevelSelection();
						Main.changeScene(drawLevelSelection);
					}

				});
				setStyle("-fx-background-color: #99C99A; -fx-background-radius: 10;");
				break;
			case 3:
				modeName.setText("TRICOLOR MODE");
				modeName.setStyle(
						"-fx-font-size: 30px; -fx-font-family:\"Arial Black\"; -fx-background-color: #3F94FF; -fx-background-radius: 10;");
				playButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						PictureWithTextButton.playSoundEffect();
						TriColorLevelSelection triColorLevelSelection = new TriColorLevelSelection();
						Main.changeScene(triColorLevelSelection);
					}

				});
				setStyle("-fx-background-color: #95C4FF; -fx-background-radius: 10;");
				break;
			}
			getChildren().add(modeName);
			if (isLocked) {
				HBox lockedModeHBox = new HBox(10);
				VBox lockedText = new VBox();
				lockedText.setAlignment(Pos.CENTER);
				Label lockedLabel = new Label("Unlock this mode by completing");
				lockedLabel.setStyle("-fx-font-size: 18px; -fx-font-family:\"Arial Black\";");
				Label conditionLabel = null;
				switch (mode) {
				case 1:
					conditionLabel = new Label("level 5 in Classic Mode.");
					break;
				case 2:
					conditionLabel = new Label("level 10 in Classic Mode.");
					break;
				case 3:
					conditionLabel = new Label("level 15 in Classic Mode.");
					break;
				}
				conditionLabel.setStyle("-fx-font-size: 18px; -fx-font-family:\"Arial Black\";");
				lockedText.getChildren().addAll(lockedLabel, conditionLabel);
				ImageView lockedMode = new ImageView(
						new Image(ClassLoader.getSystemResource("assets/locked_mode.png").toString()));
				lockedMode.setFitHeight(116.5);
				lockedMode.setFitWidth(101.5);
				lockedModeHBox.getChildren().addAll(lockedMode, lockedText);
				getChildren().add(lockedModeHBox);
			} else {
				getChildren().addAll(buttonVBox);
			}
		}
	}

	public ModeSelection() {
		setAlignment(Pos.CENTER);
		modeSelectionVBox = new VBox();
		modeSelectionVBox.setAlignment(Pos.TOP_CENTER);
		modeSelectionVBox.setBackground(new Background(new BackgroundFill(Color.web("#9D9D9D"), null, null)));

		Label sceneTitle = new Label("SELECT MODE");
		sceneTitle.setPrefHeight(150);
		sceneTitle.setPrefWidth(1280);
		sceneTitle.setAlignment(Pos.CENTER);
		sceneTitle.setStyle(
				"-fx-font-size: 48px; -fx-text-fill: white; -fx-font-family:\"Arial Black\"; -fx-background-color: #4F4F4F;");

		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setPadding(new Insets(0, 0, 10, 0));
		grid.setVgap(10);
		grid.setHgap(10);
		grid.setStyle("-fx-background-color: rgba(255, 255, 255, 0.5);");

		ModeFrame classicFrame = new ModeFrame(0, false);
		ModeFrame timerFrame = new ModeFrame(1, PlayerInfo.getClassicPassedLevel() < 5);
		ModeFrame drawFrame = new ModeFrame(2, PlayerInfo.getClassicPassedLevel() < 10);
		ModeFrame triColorFrame = new ModeFrame(3, PlayerInfo.getClassicPassedLevel() < 15);
		grid.add(classicFrame, 1, 1);
		grid.add(timerFrame, 2, 1);
		grid.add(drawFrame, 1, 2);
		grid.add(triColorFrame, 2, 2);

		BackButton backButton = new BackButton(100, 120, new MainMenu());

		modeSelectionVBox.getChildren().addAll(sceneTitle, grid, backButton);

		getChildren().add(modeSelectionVBox);

	}

	private void showHowToPlay(int mode) {
		modeSelectionVBox.setDisable(true);

		VBox howToPlay = new VBox();
		howToPlay.setPrefSize(900, 700);
		howToPlay.setAlignment(Pos.CENTER);

		ImageView imageView = new ImageView(
				new Image(ClassLoader.getSystemResource("assets/howtoplay/" + mode + ".png").toString()));

		OKButton okButton = new OKButton(100, 120);
		okButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				OKButton.playSoundEffect();
				getChildren().remove(howToPlay);
				modeSelectionVBox.setDisable(false);
			}
		});

		VBox okButtonBox = new VBox();
		okButtonBox.setAlignment(Pos.CENTER_RIGHT);
		okButtonBox.setPadding(new Insets(0, 200, 0, 0));
		okButtonBox.getChildren().add(okButton);

		howToPlay.getChildren().addAll(imageView, okButtonBox);

		getChildren().add(howToPlay);
	}
}