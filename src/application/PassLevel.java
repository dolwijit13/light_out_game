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
	
	public PassLevel(int level, int penalty)
	{
		super(10);
		setAlignment(Pos.CENTER);
		VBox vBox = new VBox(10);
		vBox.setAlignment(Pos.CENTER);
		vBox.setPrefHeight(360);
		vBox.setPrefWidth(640);
		vBox.setPadding(new Insets(10,100,10,100));

		Label clearLevel = new Label("LEVEL " + level + " CLEAR");
		Label penaltyLabel = new Label("Penalty: " + penalty);

		HBox hBox = new HBox(10);
		hBox.setAlignment(Pos.CENTER);

		ToMainMenuButton toMainMenuButton = new ToMainMenuButton();
		
		restartButton = new Button("Restart");
		toNextLevelButton = new Button("Next Level");
		hBox.getChildren().addAll(toMainMenuButton, restartButton, toNextLevelButton);
		vBox.getChildren().addAll(clearLevel, penaltyLabel, hBox);

		if (true)
		{
			Label unlockLabel = new Label("Unlocked new picture in gallery and level " + (level + 1));
			vBox.getChildren().add(unlockLabel);
		}

		vBox.setBorder(new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID,
		  CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		vBox.setBackground(new Background(new BackgroundFill(Color.PAPAYAWHIP, null, null)));
		getChildren().add(vBox);
	}
	
	public Button getToNextLevelButton()
	{
		return toNextLevelButton;
	}
}
