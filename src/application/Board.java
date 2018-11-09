package application;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class Board extends GridPane
{
	private Light[][] lights;
	private int n=0;
	private int curLevel;

	public Board(int n, int maxState, int level)
	{
		this.n = n;
		lights = new Light[n][n];
		this.curLevel = level;
		for (int i = 0; i < n; i++)
		{
			for (int j = 0; j < n; j++)
			{
				lights[i][j] = new Light("" + (i * n + j), 705 / n - 5, 705 / n - 5, maxState);
				//lights[i][j].setOnMouseClicked(mouseClick);
				lights[i][j].setId("" + (i * n + j));
				this.add(lights[i][j], j, i);
			}
		}
		setAlignment(Pos.CENTER);
		setVgap(5);
		setHgap(5);
		setPadding(new Insets(5, 5, 5, 5));
	}

	public void changeColor(int x, int y, Boolean first)
	{
		// System.out.println("b "+(x*5+y)+" "+x+" "+y+" "+first);
		Light b = lights[x][y];
		b.changeColor();
		if (first)
		{
			if (x > 0)
			{
				changeColor(x - 1, y, false);
			}
			if (x < (n - 1))
			{
				changeColor(x + 1, y, false);
			}
			if (y > 0)
			{
				changeColor(x, y - 1, false);
			}
			if (y < (n - 1))
			{
				changeColor(x, y + 1, false);
			}
		}
	}
	
	public int getN()
	{
		return this.n;
	}
	
	public int getCurLevel()
	{
		return this.curLevel;
	}
	
	public Light getLight(int i,int j)
	{
		return lights[i][j];
	}
}
