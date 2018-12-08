package application.PassLevel;

import application.Button.BackButton;
import application.Button.GameMenuButton;
import application.Button.ToMainMenuButton;
import application.PlayerData.PlayerInfo;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public abstract class PassLevel extends VBox
{
	protected GameMenuButton restartButton;
	protected GameMenuButton toNextLevelButton;
	
	protected BackButton toLevelSelectionButton;

	protected Label clearLevelLabel;
	protected Label penaltyLabel;
	protected VBox unlockLabel;
	
	protected HBox hBox = new HBox(10);
	
	protected ImageView congrats;
	
	public PassLevel(int mode, int level, int penalty,int maxLevel)
	{
		super(10);
		String color0[] = {"#FF4343","#FFD728","#2BCA2F","#3F94FF"};
		String color1[] = {"#FFC2C2","#FFED9F","#99C99A","#95C4FF"};
	
		setAlignment(Pos.TOP_CENTER);
		setPadding(new Insets(0, 0, 0, 0));
		setPrefWidth(540);
		setStyle("-fx-background-color: "+color1[mode]+"; -fx-border-color: "+color0[mode]+";-fx-border-width: 4px;");

		congrats = new ImageView(new Image(ClassLoader.getSystemResource("assets/congrats.png").toString()));
		
		clearLevelLabel = new Label("LEVEL "+level+" CLEAR");
		clearLevelLabel.setPrefWidth(540);
		clearLevelLabel.setPrefHeight(80);
		clearLevelLabel.setAlignment(Pos.CENTER);
		clearLevelLabel.setStyle("-fx-font-size: 32px; -fx-font-family:\"Arial Black\";-fx-background-color: "+color0[mode]+";");

		penaltyLabel = new Label("Penalty: " + penalty);
		penaltyLabel.setPrefWidth(540);
		penaltyLabel.setPrefHeight(80);
		penaltyLabel.setAlignment(Pos.CENTER);
		penaltyLabel.setStyle("-fx-font-size: 32px; -fx-font-fill: black; -fx-font-family:\"Arial Black\";");
		
		restartButton = new GameMenuButton(120,120,"reset.png");
		toNextLevelButton = new GameMenuButton(120,120,"next.png");
		toLevelSelectionButton = new BackButton(100,120,40+mode);
		
		getChildren().addAll(clearLevelLabel,congrats,penaltyLabel);
		
		unlockLabel = new VBox(10);
		unlockLabel.setAlignment(Pos.CENTER);
		
		hBox.setAlignment(Pos.CENTER);
		if(level !=maxLevel)
		{
			clearLevelLabel.setText("LEVEL " + level + " CLEAR");
			Label unlockLevelLabel = new Label("Unlocked level "+(level+1));
			Label unlockPictureLabel = new Label("Unlocked new picture in gallery");
			unlockLevelLabel.setStyle("-fx-font-size: 24px; -fx-font-fill: black; -fx-font-family:\"Arial Black\";");
			unlockPictureLabel.setStyle("-fx-font-size: 24px; -fx-font-fill: black; -fx-font-family:\"Arial Black\";");
			hBox.getChildren().addAll(restartButton, toNextLevelButton);
			unlockLabel.getChildren().addAll(unlockLevelLabel,unlockPictureLabel);
			getChildren().addAll(hBox,toLevelSelectionButton);
		}
		else
		{
			clearLevelLabel.setText("YOU CLEAR ALL "+maxLevel+" LEVEL!!!");
			Label unlockPictureLabel = new Label("Unlocked the LAST picture in gallery");
			unlockPictureLabel.setStyle("-fx-font-size: 24px; -fx-font-fill: black; -fx-font-family:\"Arial Black\";");
			hBox.getChildren().add(restartButton);
			unlockLabel.getChildren().addAll(unlockPictureLabel);
			getChildren().addAll(hBox,toLevelSelectionButton);
		}
	}
	
	public GameMenuButton getToNextLevelButton()
	{
		return toNextLevelButton;
	}
	
	public GameMenuButton getRestartButton() {
		return restartButton;
	}
	
	public void setPenalty(int penalty)
	{
		penaltyLabel.setText("Penalty: " + penalty);
	}
}