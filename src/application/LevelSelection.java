package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public abstract class LevelSelection extends VBox
{
	protected class LevelButton extends Button
	{
		private int level;
		public LevelButton(int level)
		{
			super(""+level);
			this.level = level;
			setPrefHeight(120);
			setPrefWidth(240);
			setLevelButton(this);
		}
		
		public int getLevel()
		{
			return level;
		}
	}
	
	public LevelSelection(int n,int m,int maxLevelCanPlay)
	{
		super(10);
		setAlignment(Pos.CENTER);
		setPadding(new Insets(10, 10, 10, 10));
		Text sceneTitle = new Text("SELECT LEVEL");
		sceneTitle.setStyle("-fx-font-size: 32px; -fx-font-family:\"Arial Black\";-fx-fill: #555;");
		GridPane grid = new GridPane();
		grid.setVgap(15);
		grid.setHgap(15);
	
		for (int i = 0; i < n; i++)
		{
			for (int j = 0; j < m; j++)
			{
				int curLevel = (i * m + j + 1);
				LevelButton levelButton = new LevelButton(curLevel);
				grid.add(levelButton, j, i);
				if(curLevel > maxLevelCanPlay)
				{
					levelButton.setDisable(true);
				}
			}
		}

		ToMainMenuButton toMainMenuButton = new ToMainMenuButton();
		getChildren().addAll(sceneTitle, grid, toMainMenuButton);
	}
	
	public abstract void setLevelButton(LevelButton levelButton);
}
