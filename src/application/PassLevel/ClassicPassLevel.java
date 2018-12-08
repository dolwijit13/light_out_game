package application.PassLevel;

import application.PlayerData.PlayerInfo;

public class ClassicPassLevel extends PassLevel
{
	public ClassicPassLevel(int level, int penalty)
	{
		super(0,level,penalty,20);

		if (PlayerInfo.getClassicPassedLevel() < level)
		{
			getChildren().add(unlockLabel);
		}
	}
}
