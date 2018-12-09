package application.LevelSelection;

import application.Main;
import application.Mode.ClassicMode;
import application.PlayerData.PlayerInfo;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
				Main.playSoundEffect("click.wav");
				int level = levelButton.getLevel();
				ClassicMode classicMode = new ClassicMode(level);
				Main.changeScene(classicMode);
			}
		});
	}

	@Override
	protected void setLabelStyle(Label selectLabel)
	{
		selectLabel.setStyle("-fx-font-size: 48px; -fx-font-family:\"Arial Black\";-fx-fill: #555;-fx-background-color: #FF4343;");
		setStyle("-fx-background-color: #FFC2C2;");
	}

	@Override
	protected void setButtonStyle(Button levelButton,int mode)
	{
		if(mode==0)
			levelButton.setStyle("-fx-font-size: 36px; -fx-font-family:\"Arial Black\";-fx-fill: #555;-fx-background-color: #FF5959;");
		else
			levelButton.setStyle("-fx-font-size: 36px; -fx-font-family:\"Arial Black\";-fx-fill: #555;-fx-background-color: #FF5959;-fx-border-color: #000000;-fx-border-width: 4px;");
	}
}
