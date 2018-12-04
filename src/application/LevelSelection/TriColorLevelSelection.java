package application.LevelSelection;

import application.Main;
import application.Mode.ClassicMode;
import application.Mode.TriColorMode;
import application.PlayerData.PlayerInfo;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class TriColorLevelSelection extends LevelSelection
{
	public TriColorLevelSelection()
	{
		super(2,5,PlayerInfo.getTriColorPassedLevel()+1);
	}

	@Override
	public void setLevelButton(LevelButton levelButton)
	{
		levelButton.setOnMouseClicked(new EventHandler<MouseEvent>()
		{
			@Override
			public void handle(MouseEvent event)
			{
				int level = levelButton.getLevel();
				TriColorMode triColorMode = new TriColorMode(level);
				Main.changeScene(triColorMode);
			}
		});
	}
	
}
