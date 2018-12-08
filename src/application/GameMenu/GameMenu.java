package application.GameMenu;

import application.Button.BackButton;
import application.Button.GameMenuButton;
import application.Button.ToMainMenuButton;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public abstract class GameMenu extends VBox
{
	protected Label levelLabel = new Label("LEVEL : 0");
	protected Label penaltyLabel = new Label("PENALTY : 0");
	protected int penalty = 0;
	protected GameMenuButton resetButton = new GameMenuButton(120,120,"reset.png");
	protected GameMenuButton undoButton = new GameMenuButton(120,120,"undo.png");
	protected BackButton backButton;
	protected HBox resetAndUndoHBox;
	protected HBox backHBox;

	public GameMenu(int level)
	{
		super(50);
		levelLabel.setText("LEVEL : " + level);
		setAlignment(Pos.TOP_CENTER);
		setPadding(new Insets(0, 0, 0, 0));
		setPrefWidth(540);

		levelLabel.setPrefWidth(540);
		levelLabel.setPrefHeight(80);
		levelLabel.setAlignment(Pos.CENTER);
		levelLabel.setTextFill(Color.web("#000000"));

		penaltyLabel.setPrefWidth(540);
		penaltyLabel.setPrefHeight(80);
		penaltyLabel.setAlignment(Pos.CENTER);
		penaltyLabel.setTextFill(Color.web("#000000"));
		
		resetButton.setTooltip(new Tooltip("reset the board"));
		undoButton.setTooltip(new Tooltip("undo the board\n(Maximum stack 5 times)"));
		resetAndUndoHBox = new HBox(20);
		resetAndUndoHBox.setAlignment(Pos.CENTER);
		resetAndUndoHBox.getChildren().addAll(resetButton,undoButton);
		
		backHBox = new HBox();
		backHBox.setPadding(new Insets(20,0,0,0));
		
		
		penaltyLabel.setStyle("-fx-font-size: 28px; -fx-font-family:\"Arial Black\";");
		setAllStyle();

	}

	public int addPenalty(int add)
	{
		penalty += add;
		penaltyLabel.setText("PENALTY : " + penalty);
		return penalty;
	}

	public int getPenalty()
	{
		return penalty;
	}

	public Button getResetButton()
	{
		return resetButton;
	}

	public Button getUndoButton()
	{
		return undoButton;
	}

	protected abstract void setAllStyle();
}
