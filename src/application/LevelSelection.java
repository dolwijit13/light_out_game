package application;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class LevelSelection extends VBox
{
	public LevelSelection()
	{
		super(10);
		setAlignment(Pos.CENTER);
		setPadding(new Insets(10, 10, 10, 10));
		Text sceneTitle = new Text("SELECT LEVEL");
		sceneTitle.setStyle("-fx-font-size: 32px; -fx-font-family:\"Arial Black\";-fx-fill: #555;");
		GridPane grid = new GridPane();
		grid.setVgap(15);
		grid.setHgap(15);
		
		int maxLevelCanPlay = PlayerInfo.getClassicLastPassedLevel()+1; 
		for (int i = 0; i < 4; i++)
		{
			for (int j = 0; j < 5; j++)
			{
				int curLevel = (i * 5 + j + 1);
				Button level = new Button("" + curLevel);
				grid.add(level, j, i);
				level.setPrefHeight(120);
				level.setPrefWidth(240);
				//level.setStyle("-fx-background-color: #51F827;");
				level.setOnMouseClicked(mouseClick);
				level.setId("" + curLevel);
				if(curLevel > maxLevelCanPlay)
				{
					level.setDisable(true);
				}
			}
		}

		ToMainMenuButton toMainMenuButton = new ToMainMenuButton();
		getChildren().addAll(sceneTitle, grid, toMainMenuButton);
	}

	private final EventHandler<MouseEvent> mouseClick = new EventHandler<MouseEvent>()
	{
		@Override
		public void handle(MouseEvent event)
		{
			Button pressed = (Button) event.getSource();
			int level = Integer.parseInt(pressed.getId());
			ClassicMode classicMode = new ClassicMode(level);
			Main.changeScene(classicMode);
		}
	};
}
