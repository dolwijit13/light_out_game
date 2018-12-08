package application.GameMenu;

import java.util.ArrayList;

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
	Button help3Button;
	
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
		super(level);
		MiniBoard miniBoard = new MiniBoard(n, initalBoard);

		HBox helper = new HBox(10);
		helper.setAlignment(Pos.CENTER);
		help2Button = new Button("Help2");
		help3Button = new Button("Help 3");
		helper.getChildren().addAll(help2Button, help3Button);
		getChildren().addAll(levelLabel, miniBoard, penaltyLabel, helper, resetButton, undoButton, toMainMenuButton);
	}

	public Button getHelp2Button()
	{
		return help2Button;
	}

	public Button getHelp3Button()
	{
		return help3Button;
	}
	
	
}
