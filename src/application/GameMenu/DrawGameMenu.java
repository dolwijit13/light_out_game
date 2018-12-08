package application.GameMenu;

import java.util.ArrayList;

import application.Button.BackButton;
import application.Button.GameMenuButton;
import application.GameLogic.Light;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class DrawGameMenu extends GameMenu
{
	Button help2Button;
	
	private class MiniBoard extends GridPane
	{
		private Light[][] lights;
		private int n=0;

		public MiniBoard(int n,ArrayList<Integer>initalBoard)
		{
			this.n = n;
			lights = new Light[n][n];
			setAlignment(Pos.CENTER);
			setVgap(2);
			setHgap(2);
			setPadding(new Insets(5, 5, 5, 5));
			for (int i = 0; i < n; i++)
			{
				for (int j = 0; j < n; j++)
				{
					lights[i][j] = new Light("" + (i * n + j), 205 / n - 5, 205 / n - 5, 2,2,0);
					//lights[i][j].setOnMouseClicked(mouseClick);
					lights[i][j].setId("" + (i * n + j));
					this.add(lights[i][j], j, i);
				}
			}
			
			for(int i=0;i<initalBoard.size();i++)
			{
				int tmp = initalBoard.get(i);
				lights[tmp/n][tmp%n].changeColor();
			}
		}
	}

	public DrawGameMenu(int n,ArrayList<Integer>initalBoard,int level)
	{
		super(level,10,100,100);
		MiniBoard miniBoard = new MiniBoard(n, initalBoard);

		HBox helper = new HBox(10);
		helper.setAlignment(Pos.CENTER);
		help2Button = new GameMenuButton(100,120,"help2.png");
		help2Button.setTooltip(new Tooltip("Show next should press cell\n(50 Penalty)"));
		helper.getChildren().addAll(help2Button);
		
		backButton = new BackButton(100, 120, 42);
		backButton.setTooltip(new Tooltip("Back to Level Selection\n(Unsaved progress will be lost)"));
		backHBox.setAlignment(Pos.CENTER_RIGHT);
		backHBox.getChildren().add(backButton);
		
		getChildren().addAll(levelLabel, miniBoard, penaltyLabel, helper, resetAndUndoHBox, backHBox);
	}

	public Button getHelp2Button()
	{
		return help2Button;
	}

	@Override
	protected void setAllStyle()
	{
		setStyle("-fx-background-color: #99C99A; -fx-border-color: #2BCA2F;-fx-border-width: 4px;");
		levelLabel.setStyle("-fx-font-size: 32px; -fx-font-family:\"Arial Black\";-fx-background-color: #2BCA2F;");
	}
}
