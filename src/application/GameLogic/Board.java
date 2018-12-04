package application.GameLogic;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;

public class Board extends GridPane
{
	private Light[][] lights;
	private int n=0;
	private int curLevel;
	private int maxState;

	public Board(int n, int maxState, int level, int mode)
	{
		this.n = n;
		lights = new Light[n][n];
		this.curLevel = level;
		this.maxState = maxState;
		setAlignment(Pos.CENTER);
		setVgap(5);
		setHgap(5);
		setPadding(new Insets(5, 5, 5, 5));
		for (int i = 0; i < n; i++)
		{
			for (int j = 0; j < n; j++)
			{
				lights[i][j] = new Light("" + (i * n + j), 705 / n - 5, 705 / n - 5, maxState,mode,level);
				//lights[i][j].setOnMouseClicked(mouseClick);
				lights[i][j].setId("" + (i * n + j));
				this.add(lights[i][j], j, i);
			}
		}
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
			if(b.getStyle() != "")
			{
				b.setStyle("");
				b.setMaxSize();
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
	
	public boolean canHelp1() {
		int cnt = 0;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(lights[i][j].getCurrentState() != 0) {
					cnt++;
					if(cnt > 1){
						return false;
					}
				}
			}
		}
		return true;
	}
	
	public int getMaxState()
	{
		return maxState;
	}
}
