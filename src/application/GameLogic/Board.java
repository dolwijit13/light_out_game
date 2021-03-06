package application.GameLogic;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;

public class Board extends GridPane {
	private Light[][] lights;
	private int n = 0;
	private int curLevel;
	private int maxState;
	private boolean canHelp2 = true;

	public Board(int n, int maxState, int level, int mode) {
		this.n = n;
		lights = new Light[n][n];
		this.curLevel = level;
		this.maxState = maxState;
		setAlignment(Pos.CENTER);
		setVgap(5);
		setHgap(5);
		setPadding(new Insets(5, 5, 5, 5));
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				lights[i][j] = new Light("" + (i * n + j), 705 / n - 5, 705 / n - 5, maxState, mode, level);
				lights[i][j].setId("" + (i * n + j));
				this.add(lights[i][j], j, i);
			}
		}
	}

	public void changeColor(int x, int y, Boolean first) {
		Light b = lights[x][y];
		b.changeColor();
		if (first) {
			b.addShouldPress();
			if (x > 0) {
				changeColor(x - 1, y, false);
			}
			if (x < (n - 1)) {
				changeColor(x + 1, y, false);
			}
			if (y > 0) {
				changeColor(x, y - 1, false);
			}
			if (y < (n - 1)) {
				changeColor(x, y + 1, false);
			}
			if (b.getBorderStyle() != "") {
				b.setBorder("");
				b.setMaxSize();
				canHelp2 = true;
			}
		}
	}

	public int getN() {
		return this.n;
	}

	public int getCurLevel() {
		return this.curLevel;
	}

	public Light getLight(int i, int j) {
		return lights[i][j];
	}

	public boolean isCanHelp1() {
		int cnt = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (lights[i][j].getCurrentState() != 0) {
					cnt++;
					if (cnt > 1) {
						return false;
					}
				}
			}
		}
		return true;
	}

	public void setCanHelp2(boolean canHelp2) {
		this.canHelp2 = canHelp2;
	}

	public boolean isCanHelp2() {
		return canHelp2;
	}

	public int getMaxState() {
		return maxState;
	}
}
