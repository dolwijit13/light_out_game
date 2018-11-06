package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;

public class Board extends GridPane
{
	private light[][] lights;
	private int n;

	public Board(int n)
	{
		this.n = n;
		lights = new light[n][n];
		for (int i = 0; i < n; i++)
		{
			for (int j = 0; j < n; j++)
			{
				lights[i][j] = new light("" + (i * n + j));
				lights[i][j].setOnAction(myHandler);
				lights[i][j].setId("" + (i * n + j));
				this.add(lights[i][j], j, i);
			}
		}
		setAlignment(Pos.CENTER);
		setVgap(10);
		setHgap(10);
		setPadding(new Insets(5, 5, 5, 5));
	}

	private final EventHandler<ActionEvent> myHandler = new EventHandler<ActionEvent>()
	{
		@Override
		public void handle(final ActionEvent event)
		{
			light b = (light) event.getSource();
			int ID = Integer.parseInt(b.getId());
			int x = ID / n, y = ID % n;
			//System.out.println("a " +ID+" "+x+" "+y);
			changeColor(x, y, true);
		}
	};

	public void changeColor(int x, int y, Boolean first)
	{
		//System.out.println("b "+(x*5+y)+" "+x+" "+y+" "+first);
		light b = lights[x][y];
		b.changeColor();
		if (first)
		{
			if (x > 0)
			{
				changeColor(x - 1, y, false);
			}
			if (x < (n-1))
			{
				changeColor(x + 1, y, false);
			}
			if (y > 0)
			{
				changeColor(x, y - 1, false);
			}
			if (y < (n-1))
			{
				changeColor(x, y + 1, false);
			}
		}
	}
}
