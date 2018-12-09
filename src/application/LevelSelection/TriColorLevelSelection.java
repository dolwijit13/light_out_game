package application.LevelSelection;

import application.Main;
import application.Mode.ClassicMode;
import application.Mode.TriColorMode;
import application.PlayerData.PlayerInfo;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
				Main.playSoundEffect("click.wav");
				int level = levelButton.getLevel();
				TriColorMode triColorMode = new TriColorMode(level);
				Main.changeScene(triColorMode);
			}
		});
	}

	@Override
	protected void setLabelStyle(Label selectLabel)
	{
		selectLabel.setStyle("-fx-font-size: 48px; -fx-font-family:\"Arial Black\";-fx-fill: #555;-fx-background-color: #3F94FF;");
		setStyle("-fx-background-color: #95C4FF;");
	}

	@Override
	protected void setButtonStyle(Button levelButton, int mode)
	{
		if(mode==0)
			levelButton.setStyle("-fx-font-size: 36px; -fx-font-family:\"Arial Black\";-fx-fill: #555;-fx-background-color: #6BACFF;");
		else
			levelButton.setStyle("-fx-font-size: 36px; -fx-font-family:\"Arial Black\";-fx-fill: #555;-fx-background-color: #6BACFF;-fx-border-color: #000000;-fx-border-width: 4px;");
	}
	
}
