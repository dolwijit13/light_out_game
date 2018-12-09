package application.PassLevel;

import application.Button.BackButton;
import application.Button.GameMenuButton;
import application.LevelSelection.ClassicLevelSelection;
import application.LevelSelection.DrawLevelSelection;
import application.LevelSelection.TriColorLevelSelection;
import application.Mode.ModeSelection;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public abstract class PassLevel extends VBox
{
	protected GameMenuButton restartButton;
	protected GameMenuButton toNextLevelButton;
	
	protected HBox toLevelSelectionHBox;

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
		setMaxHeight(700);
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
		
		restartButton = new GameMenuButton(100,100,"reset.png");
		toNextLevelButton = new GameMenuButton(100,100,"next.png");
		
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
			if(mode != 0) {
				unlockLabel.getChildren().remove(unlockPictureLabel);
			}
			getChildren().addAll(hBox);
		}
		else
		{
			clearLevelLabel.setText("YOU CLEAR ALL "+maxLevel+" LEVEL!!!");
			Label unlockPictureLabel = new Label("Unlocked the LAST picture in gallery");
			unlockPictureLabel.setStyle("-fx-font-size: 24px; -fx-font-fill: black; -fx-font-family:\"Arial Black\";");
			hBox.getChildren().add(restartButton);
			unlockLabel.getChildren().addAll(unlockPictureLabel);
			getChildren().addAll(hBox);
		}
		
		BackButton toLevelSelectionButton =null;
		if(mode==0) toLevelSelectionButton = new BackButton(100,120,new ClassicLevelSelection(1));
		if(mode==1) toLevelSelectionButton = new BackButton(100,120,new ModeSelection());
		if(mode==2) toLevelSelectionButton = new BackButton(100,120,new DrawLevelSelection(1));
		if(mode==3) toLevelSelectionButton = new BackButton(100,120,new TriColorLevelSelection(1));
		
		toLevelSelectionHBox = new HBox();
		toLevelSelectionHBox.setAlignment(Pos.BOTTOM_RIGHT);
		toLevelSelectionHBox.getChildren().add(toLevelSelectionButton);
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