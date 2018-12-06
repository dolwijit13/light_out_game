package application.Mode;

import application.Main;
import application.LevelSelection.ClassicLevelSelection;
import application.LevelSelection.DrawLevelSelection;
import application.LevelSelection.TriColorLevelSelection;
import application.PlayerData.PlayerInfo;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ModeSelection extends VBox
{
	
	protected class ModeButton extends Button
	{
		public ModeButton(String modeString)
		{
			super(modeString);
		}
	}

	public ModeSelection()
	{
		super(10);
		setAlignment(Pos.CENTER);
		setPadding(new Insets(10, 10, 10, 10));
		
		Text sceneTitle = new Text("SELECT MODE");
		sceneTitle.setStyle("-fx-font-size: 32px; -fx-font-family:\"Arial Black\";-fx-fill: #555;");
		
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setVgap(15);
		grid.setHgap(15);
		
		ModeButton classicButton = new ModeButton("Classic Mode");
		ModeButton timerButton = new ModeButton("Timer Mode");
		ModeButton drawButton = new ModeButton("Draw Mode");
		ModeButton triColorButton = new ModeButton("Tri Color Mode");
		grid.add(classicButton, 1, 1);
		grid.add(timerButton, 2, 1);
		grid.add(drawButton, 1, 2);
		grid.add(triColorButton, 2, 2);
		
		if(PlayerInfo.getClassicPassedLevel() <5)
		{
			timerButton.setDisable(true);
		}
		if(PlayerInfo.getClassicPassedLevel() <10)
		{
			drawButton.setDisable(true);
		}
		if(PlayerInfo.getClassicPassedLevel() <15)
		{
			triColorButton.setDisable(true);
		}
		
		this.getChildren().addAll(sceneTitle,grid);
		
		classicButton.setOnMouseClicked(new EventHandler<MouseEvent>()
		{
			@Override
			public void handle(MouseEvent event)
			{
				ClassicLevelSelection classicLevelSelection = new ClassicLevelSelection();
				Main.changeScene(classicLevelSelection);
			}

		});
		
		timerButton.setOnMouseClicked(new EventHandler<MouseEvent>()
		{
			@Override
			public void handle(MouseEvent event)
			{
				TimerMode timerMode = new TimerMode(1,60,0,0,null);
				Main.changeScene(timerMode);
			}

		});
		
		drawButton.setOnMouseClicked(new EventHandler<MouseEvent>()
		{
			@Override
			public void handle(MouseEvent event)
			{
				DrawLevelSelection drawLevelSelection = new DrawLevelSelection();
				Main.changeScene(drawLevelSelection);
			}

		});
		
		triColorButton.setOnMouseClicked(new EventHandler<MouseEvent>()
		{
			@Override
			public void handle(MouseEvent event)
			{
				TriColorLevelSelection triColorLevelSelection = new TriColorLevelSelection();
				Main.changeScene(triColorLevelSelection);
			}

		});
	}
	
}