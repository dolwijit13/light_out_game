package application.PassLevel;

import application.PlayerData.PlayerInfo;

public class DrawPassLevel extends PassLevel {
	public DrawPassLevel(int level, int penalty) {
		super(2, level, penalty, 10);
		if (PlayerInfo.getDrawPassedLevel() < level) {
			getChildren().add(unlockVBox);
		}
		getChildren().add(backHBox);
	}

}
