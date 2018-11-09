package application;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PassLevel extends VBox
{
	public PassLevel(int level, int penalty) {
		super(10);
		setPrefHeight(360);
		setPrefWidth(640);
		Label clearLevel = new Label("LEVEL " + level + " CLEAR");
		Label penaltyLabel = new Label("Penalty: " + penalty);
		HBox hBox = new HBox(10);
		ToMainMenuButton toMainMenuButton = new ToMainMenuButton();
		Button restartButton = new Button("Restart");
		Button toNextLevelButton = new Button("Next Level");
		hBox.getChildren().addAll(toMainMenuButton,restartButton,toNextLevelButton);
		getChildren().addAll(clearLevel,penaltyLabel,hBox);
		if(true) {
			Label unlockLabel = new Label("Unlocked new picture in gallery and level " + (level+1));
			getChildren().add(unlockLabel);
		}
	}
}
