package application.GameMenu;

import application.Button.BackButton;
import application.Button.GameMenuButton;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public abstract class GameMenu extends VBox {
	protected Label levelLabel;
	protected int penalty;
	protected Label penaltyLabel;
	protected GameMenuButton resetButton;
	protected GameMenuButton undoButton;
	protected BackButton backButton;
	protected HBox resetAndUndoHBox;
	protected HBox backHBox;

	public GameMenu(int level, int gap, int hButton, int wButton) {
		super(gap);
		setAlignment(Pos.TOP_CENTER);
		setPrefWidth(540);

		levelLabel = new Label("LEVEL : " + level);
		levelLabel.setPrefWidth(540);
		levelLabel.setPrefHeight(80);
		levelLabel.setAlignment(Pos.CENTER);
		levelLabel.setTextFill(Color.web("#000000"));

		penalty = 0;

		penaltyLabel = new Label("PENALTY : 0");
		penaltyLabel.setPrefWidth(540);
		penaltyLabel.setPrefHeight(80);
		penaltyLabel.setAlignment(Pos.CENTER);
		penaltyLabel.setTextFill(Color.web("#000000"));
		penaltyLabel.setStyle("-fx-font-size: 28px; -fx-font-family:\"Arial Black\";");

		resetButton = new GameMenuButton(hButton, wButton, "reset.png");
		resetButton.setTooltip(new Tooltip("Reset the board"));
		undoButton = new GameMenuButton(hButton, wButton, "undo.png");
		undoButton.setTooltip(new Tooltip("Undo the board\n(Maximum stack 5 times)"));
		resetAndUndoHBox = new HBox(20);
		resetAndUndoHBox.setAlignment(Pos.CENTER);
		resetAndUndoHBox.getChildren().addAll(resetButton, undoButton);

		backHBox = new HBox();
		backHBox.setPadding(new Insets(20, 0, 0, 0));

		setAllStyle();
	}

	public int addPenalty(int add) {
		penalty += add;
		penaltyLabel.setText("PENALTY : " + penalty);
		return penalty;
	}

	public int getPenalty() {
		return penalty;
	}

	public GameMenuButton getResetButton() {
		return resetButton;
	}

	public GameMenuButton getUndoButton() {
		return undoButton;
	}

	protected abstract void setAllStyle();
}
