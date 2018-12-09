package application.PassLevel;

import application.PlayerData.PlayerInfo;

public class TriColorPassLevel extends PassLevel {
	public TriColorPassLevel(int level, int penalty) {
		super(3, level, penalty, 10);
		if (PlayerInfo.getTriColorPassedLevel() < level) {
			getChildren().add(unlockVBox);
		}
		getChildren().add(backHBox);
	}

}
