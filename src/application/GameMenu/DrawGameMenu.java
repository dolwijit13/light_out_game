package application.GameMenu;

import java.util.ArrayList;

import application.Button.BackButton;
import application.Button.GameMenuButton;
import application.GameLogic.Light;
import application.LevelSelection.DrawLevelSelection;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;

public class DrawGameMenu extends GameMenu {
	private GameMenuButton help2Button;

	private class MiniBoard extends GridPane {
		private Light[][] lights;

		public MiniBoard(int n, ArrayList<Integer> initalBoard) {
			lights = new Light[n][n];
			setAlignment(Pos.CENTER);
			setVgap(2);
			setHgap(2);
			setPadding(new Insets(5, 5, 5, 5));
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					lights[i][j] = new Light("" + (i * n + j), 205 / n - 5, 205 / n - 5, 2, 2, 0);
					lights[i][j].setId("" + (i * n + j));
					this.add(lights[i][j], j, i);
				}
			}
			for (int i = 0; i < initalBoard.size(); i++) {
				int tmp = initalBoard.get(i);
				lights[tmp / n][tmp % n].changeColor();
			}
		}
	}

	public DrawGameMenu(int n, ArrayList<Integer> initalBoard, int level) {
		super(level, 10, 100, 100);

		help2Button = new GameMenuButton(100, 120, "help2.png");
		help2Button.setTooltip(new Tooltip("Show next should press cell\n(50 Penalty)"));

		backButton = new BackButton(100, 120, new DrawLevelSelection());
		backButton.setTooltip(new Tooltip("Back to Level Selection\n(Unsaved progress will be lost)"));
		backHBox.setAlignment(Pos.CENTER_RIGHT);
		backHBox.getChildren().add(backButton);

		MiniBoard miniBoard = new MiniBoard(n, initalBoard);

		getChildren().addAll(levelLabel, miniBoard, penaltyLabel, help2Button, resetAndUndoHBox, backHBox);
	}

	public Button getHelp2Button() {
		return help2Button;
	}

	@Override
	protected void setAllStyle() {
		setStyle("-fx-background-color: #99C99A; -fx-border-color: #2BCA2F;-fx-border-width: 4px;");
		levelLabel.setStyle("-fx-font-size: 32px; -fx-font-family:\"Arial Black\";-fx-background-color: #2BCA2F;");
	}
}
