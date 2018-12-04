package application.LevelSelection;

import application.Main;
import application.Mode.ClassicMode;
import application.PlayerData.PlayerInfo;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class ClassicLevelSelection extends LevelSelection
{
	public ClassicLevelSelection()
	{
		super(4,5,PlayerInfo.getClassicPassedLevel()+1);
	}
	
	public void setLevelButton(LevelButton levelButton)
	{
		levelButton.setOnMouseClicked(new EventHandler<MouseEvent>()
		{
			@Override
			public void handle(MouseEvent event)
			{
				int level = levelButton.getLevel();
				ClassicMode classicMode = new ClassicMode(level);
				Main.changeScene(classicMode);
			}
		});
	}
}
