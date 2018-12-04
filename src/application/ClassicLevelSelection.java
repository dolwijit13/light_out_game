package application;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class ClassicLevelSelection extends LevelSelection
{
	public ClassicLevelSelection()
	{
		super(4,5,PlayerInfo.getClassicLastPassedLevel()+1);
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
