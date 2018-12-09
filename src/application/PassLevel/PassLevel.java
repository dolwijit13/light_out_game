package application.PassLevel;

import application.Button.BackButton;
import application.Button.GameMenuButton;
import application.LevelSelection.ClassicLevelSelection;
import application.LevelSelection.DrawLevelSelection;
import application.LevelSelection.TriColorLevelSelection;
import application.Mode.ModeSelection;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public abstract class PassLevel extends VBox {
	private static final String color0[] = { "#FF4343", "#FFD728", "#2BCA2F", "#3F94FF" };
	private static final String color1[] = { "#FFC2C2", "#FFED9F", "#99C99A", "#95C4FF" };
	protected Label clearLevelLabel;
	private Label penaltyLabel;
	protected VBox unlockVBox;
	protected GameMenuButton restartButton;
	protected GameMenuButton toNextLevelButton;
	protected HBox backHBox;

	public PassLevel(int mode, int level, int penalty, int maxLevel) {
		super(10);
		setAlignment(Pos.TOP_CENTER);
		setPrefWidth(540);
		setMaxHeight(700);
		setStyle("-fx-background-color: " + color1[mode] + "; -fx-border-color: " + color0[mode]
				+ ";-fx-border-width: 4px;");

		ImageView congrats = new ImageView(new Image(ClassLoader.getSystemResource("assets/congrats.png").toString()));

		clearLevelLabel = new Label("");
		clearLevelLabel.setPrefWidth(540);
		clearLevelLabel.setPrefHeight(80);
		clearLevelLabel.setAlignment(Pos.CENTER);
		clearLevelLabel.setStyle(
				"-fx-font-size: 32px; -fx-font-family:\"Arial Black\";-fx-background-color: " + color0[mode] + ";");

		penaltyLabel = new Label("Penalty: " + penalty);
		penaltyLabel.setPrefWidth(540);
		penaltyLabel.setPrefHeight(80);
		penaltyLabel.setAlignment(Pos.CENTER);
		penaltyLabel.setStyle("-fx-font-size: 32px; -fx-font-fill: black; -fx-font-family:\"Arial Black\";");

		getChildren().addAll(clearLevelLabel, congrats, penaltyLabel);

		restartButton = new GameMenuButton(100, 100, "reset.png");
		toNextLevelButton = new GameMenuButton(100, 100, "next.png");

		unlockVBox = new VBox(10);
		unlockVBox.setAlignment(Pos.CENTER);

		HBox buttonHBox = new HBox(10);
		buttonHBox.setAlignment(Pos.CENTER);
		if (level != maxLevel) {
			clearLevelLabel.setText("LEVEL " + level + " CLEAR");
			Label unlockLevelLabel = new Label("Unlocked level " + (level + 1));
			Label unlockPictureLabel = new Label("Unlocked new picture in gallery");
			unlockLevelLabel.setStyle("-fx-font-size: 24px; -fx-font-fill: black; -fx-font-family:\"Arial Black\";");
			unlockPictureLabel.setStyle("-fx-font-size: 24px; -fx-font-fill: black; -fx-font-family:\"Arial Black\";");
			buttonHBox.getChildren().addAll(restartButton, toNextLevelButton);
			unlockVBox.getChildren().addAll(unlockLevelLabel, unlockPictureLabel);
			if (mode != 0) {
				unlockVBox.getChildren().remove(unlockPictureLabel);
			}
			getChildren().addAll(buttonHBox);
		} else {
			clearLevelLabel.setText("YOU CLEAR ALL " + maxLevel + " LEVEL!!!");
			Label unlockPictureLabel = new Label("Unlocked the LAST picture in gallery");
			unlockPictureLabel.setStyle("-fx-font-size: 24px; -fx-font-fill: black; -fx-font-family:\"Arial Black\";");
			buttonHBox.getChildren().add(restartButton);
			unlockVBox.getChildren().addAll(unlockPictureLabel);
			getChildren().addAll(buttonHBox);
		}

		BackButton backButton = null;
		if (mode == 0)
			backButton = new BackButton(100, 120, new ClassicLevelSelection());
		if (mode == 1)
			backButton = new BackButton(100, 120, new ModeSelection());
		if (mode == 2)
			backButton = new BackButton(100, 120, new DrawLevelSelection());
		if (mode == 3)
			backButton = new BackButton(100, 120, new TriColorLevelSelection());

		backHBox = new HBox();
		backHBox.setAlignment(Pos.BOTTOM_RIGHT);
		backHBox.getChildren().add(backButton);
	}

	public GameMenuButton getToNextLevelButton() {
		return toNextLevelButton;
	}

	public GameMenuButton getRestartButton() {
		return restartButton;
	}

	public void setPenalty(int penalty) {
		penaltyLabel.setText("Penalty: " + penalty);
	}
}