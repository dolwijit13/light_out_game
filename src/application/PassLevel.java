package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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

public class PassLevel extends VBox
{
	Button restartButton;
	Button toNextLevelButton;
	Label penaltyLabel;
	
	public PassLevel(int level, int penalty)
	{
		super(10);
		setAlignment(Pos.CENTER);
		VBox vBox = new VBox(10);
		vBox.setAlignment(Pos.CENTER);
		vBox.setPrefHeight(360);
		vBox.setPrefWidth(640);
		vBox.setPadding(new Insets(10,100,10,100));

		Label clearLevelLabel;
		penaltyLabel = new Label("Penalty: " + penalty);
		Label unlockLabel;

		HBox hBox = new HBox(10);
		hBox.setAlignment(Pos.CENTER);

		ToMainMenuButton toMainMenuButton = new ToMainMenuButton();
		
		restartButton = new Button("Restart");
		toNextLevelButton = new Button("Next Level");
		
		
		if(level !=20)
		{
			clearLevelLabel = new Label("LEVEL " + level + " CLEAR");
			unlockLabel = new Label("Unlocked new picture in gallery and level " + (level + 1));
			hBox.getChildren().addAll(toMainMenuButton, restartButton, toNextLevelButton);
			vBox.getChildren().addAll(clearLevelLabel, penaltyLabel, hBox);
		}
		else
		{
			clearLevelLabel = new Label("YOU CLEAR ALL 20 LEVEL!!!");
			unlockLabel = new Label("Unlocked the LAST picture in gallery");
			hBox.getChildren().addAll(toMainMenuButton, restartButton);
			vBox.getChildren().addAll(clearLevelLabel, penaltyLabel, hBox);
		}
		

		if (PlayerInfo.getClassicLastPassedLevel() < level)
		{
			vBox.getChildren().add(unlockLabel);
			PlayerInfo.setClassicLastPassedLevel(level);
		}
		
		PlayerInfo.setClassicPenalty(level, penalty);

		vBox.setBorder(new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID,
		  CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		vBox.setBackground(new Background(new BackgroundFill(Color.PAPAYAWHIP, null, null)));
		getChildren().add(vBox);
	}
	
	public Button getToNextLevelButton()
	{
		return toNextLevelButton;
	}
	
	public Button getRestartButton() {
		return restartButton;
	}
	
	public void setPenalty(int penalty)
	{
		penaltyLabel.setText("Penalty: " + penalty);
	}
}
