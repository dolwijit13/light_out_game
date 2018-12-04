package application.PassLevel;

import application.PlayerData.PlayerInfo;
import javafx.scene.control.Label;

public class ClassicPassLevel extends PassLevel
{
	public ClassicPassLevel(int level, int penalty)
	{
		super(level,penalty);
		
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
		}
		
		PlayerInfo.setClassicPenalty(level, penalty);
	}
}
