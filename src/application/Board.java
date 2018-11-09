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
	private int n;
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
				lights[i][j].setOnMouseClicked(mouseClick);
				lights[i][j].setId("" + (i * n + j));
				this.add(lights[i][j], j, i);
			}
		}
		setAlignment(Pos.CENTER);
		setVgap(5);
		setHgap(5);
		setPadding(new Insets(5, 5, 5, 5));
	}

	private final EventHandler<MouseEvent> mouseClick = new EventHandler<MouseEvent>()
	{
		@Override
		public void handle(MouseEvent event)
		{
			MouseButton button = event.getButton();
			if (button == MouseButton.PRIMARY)
			{
				Light b = (Light) event.getSource();
				int ID = Integer.parseInt(b.getId());
				int x = ID / n, y = ID % n; // System.out.println("a " +ID+" "+x+" "+y);
				changeColor(x, y, true);
			}
			else if (button == MouseButton.SECONDARY)
			{
				Light b = (Light) event.getSource();
				int ID = Integer.parseInt(b.getId());
				int x = ID / n, y = ID % n; // System.out.println("a " +ID+" "+x+" "+y);
				changeColor(x, y, false);
			}
			ClassicGameMenu.addPenalty(5);
			if (isWinLevel())
				toNextLevel(curLevel);
		
		}
	};

	private boolean isWinLevel()
	{
		for (int i = 0; i < n; i++)
		{
			for (int j = 0; j < n; j++)
			{
				if (lights[i][j].getCurrentState() != 0)
				{
					return false;
				}
			}
		}
		return true;
	}

	private void toNextLevel(int curLevel)
	{
		ClassicMode nextLevel = new ClassicMode(curLevel + 1);
		Main.changeScene(nextLevel);
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
}
