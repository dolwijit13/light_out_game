package application.LevelSelection;

import application.Main;
import application.LevelSelection.LevelSelection.LevelButton;
import application.Mode.DrawMode;
import application.Mode.TriColorMode;
import application.PlayerData.PlayerInfo;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class DrawLevelSelection extends LevelSelection
{
	public DrawLevelSelection()
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
				DrawMode drawMode = new DrawMode(level);
				Main.changeScene(drawMode);
			}
		});
	}
}
