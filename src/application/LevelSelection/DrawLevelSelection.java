package application.LevelSelection;

import application.Main;
import application.LevelSelection.LevelSelection.LevelButton;
import application.Mode.DrawMode;
import application.Mode.TriColorMode;
import application.PlayerData.PlayerInfo;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class DrawLevelSelection extends LevelSelection
{
	public DrawLevelSelection()
	{
		super(2,5,PlayerInfo.getDrawPassedLevel()+1);
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

	@Override
	protected void setLabelStyle(Label selectLabel)
	{
		selectLabel.setStyle("-fx-font-size: 48px; -fx-font-family:\"Arial Black\";-fx-fill: #555;-fx-background-color: #2BCA2F;");
		setStyle("-fx-background-color: #99C99A;");
	}

	@Override
	protected void setButtonStyle(Button levelButton, int mode)
	{
		if(mode==0)
			levelButton.setStyle("-fx-font-size: 36px; -fx-font-family:\"Arial Black\";-fx-fill: #555;-fx-background-color: #7BCA7D;");
		else
			levelButton.setStyle("-fx-font-size: 36px; -fx-font-family:\"Arial Black\";-fx-fill: #555;-fx-background-color: #7BCA7D;-fx-border-color: #000000;-fx-border-width: 4px;");
	}
}
