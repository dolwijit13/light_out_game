package application.GameMenu;

import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class TimerGameMenu extends GameMenu
{
	private Button newPuzzleButton;
	private Label timeLeftLabel;
	private Label passedLevelLabel;
	private Canvas canvas;
	private int timeLeft;
	private GraphicsContext gc;
	
	public TimerGameMenu(int penalty)
	{
		super.penalty = penalty;
		canvas = new Canvas(70, 50);
		gc = canvas.getGraphicsContext2D();
		newPuzzleButton = new Button("New");
		Text timeLeftText = new Text("Time left");
		timeLeftLabel = new Label("test");
		Text passedLevelText = new Text("Completed");
		passedLevelLabel = new Label("0");
		GridPane gridPane = new GridPane();
		gridPane.add(timeLeftText, 0, 0);
		gridPane.add(canvas, 0, 1);
		gridPane.add(passedLevelText, 1, 0);
		gridPane.add(passedLevelLabel, 1, 1);
		gridPane.add(penaltyText, 2, 0);
		gridPane.add(penaltyLabel, 2, 1);
		gridPane.setHgap(40);
		gridPane.setVgap(10);
		gridPane.setAlignment(Pos.CENTER);
		getChildren().addAll(logo, gridPane, newPuzzleButton, resetButton, undoButton, toMainMenuButton);
	}
	
	public Button getNewPuzzleButton() {
		return newPuzzleButton;
	}
	
	public void setTimeLeft(int timeLeft) {
		this.timeLeft = timeLeft;
	}
	
	public void setPassedLevelLabel(int passedLevel) {
		passedLevelLabel.setText(""+passedLevel);
	}
	
	public GraphicsContext getGc() {
		return gc;
	}
	public void drawCurrentTimeString(GraphicsContext gc){
		gc.setFill(Color.BLACK);
		gc.setFont(new Font(40));
		gc.clearRect(0, 0, this.canvas.getWidth(), this.canvas.getHeight());
		gc.fillText("" + this.timeLeft, 0, this.canvas.getWidth() / 2);
	}
}