package application.PassLevel;

import application.Button.ToMainMenuButton;
import application.PlayerData.PlayerInfo;
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

public abstract class PassLevel extends VBox
{
	protected Button restartButton;
	protected Button toNextLevelButton;
	protected Label penaltyLabel;
	protected Label clearLevelLabel;
	protected Label unlockLabel;
	protected ToMainMenuButton toMainMenuButton = new ToMainMenuButton();
	protected VBox vBox = new VBox(10);
	protected HBox hBox = new HBox(10);
	
	public PassLevel(int level, int penalty,int maxLevel)
	{
		super(10);
		setAlignment(Pos.CENTER);
		vBox.setAlignment(Pos.CENTER);
		vBox.setPrefHeight(360);
		vBox.setPrefWidth(640);
		vBox.setPadding(new Insets(10,100,10,100));

		penaltyLabel = new Label("Penalty: " + penalty);
		
		hBox.setAlignment(Pos.CENTER);
		
		restartButton = new Button("Restart");
		toNextLevelButton = new Button("Next Level");
		
		if(level !=maxLevel)
		{
			clearLevelLabel = new Label("LEVEL " + level + " CLEAR");
			unlockLabel = new Label("Unlocked new picture in gallery and level " + (level + 1));
			hBox.getChildren().addAll(toMainMenuButton, restartButton, toNextLevelButton);
			vBox.getChildren().addAll(clearLevelLabel, penaltyLabel, hBox);
		}
		else
		{
			clearLevelLabel = new Label("YOU CLEAR ALL "+maxLevel+" LEVEL!!!");
			unlockLabel = new Label("Unlocked the LAST picture in gallery");
			hBox.getChildren().addAll(toMainMenuButton, restartButton);
			vBox.getChildren().addAll(clearLevelLabel, penaltyLabel, hBox);
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
	
	public Button getRestartButton() {
		return restartButton;
	}
	
	public void setPenalty(int penalty)
	{
		penaltyLabel.setText("Penalty: " + penalty);
	}
}
