package application.GameMenu;

import application.Button.BackButton;
import application.Button.GameMenuButton;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class TimerGameMenu extends GameMenu
{
	private GameMenuButton newPuzzleButton;
	private GameMenuButton pauseButton;
	private Label passedLevelCount;
	private Canvas canvas;
	private int timeLeft;
	private GraphicsContext gc;
	
	public TimerGameMenu(int penalty,int level)
	{
		super(level,30,80,80);
		super.penalty = penalty;
		canvas = new Canvas(70, 50);
		gc = canvas.getGraphicsContext2D();
		
		newPuzzleButton = new GameMenuButton(80,80,"new.png");
		newPuzzleButton.setTooltip(new Tooltip("Immediately shuffle new board\n(200 Penalty)"));
		pauseButton = new GameMenuButton(80,80,"pause.png");
		pauseButton.setTooltip(new Tooltip("Pause the game"));
		
		Label timeLeftLabel = new Label("TIME LEFT");
		timeLeftLabel.setStyle("-fx-font-size: 24px; -fx-font-family:\"Arial Black\";");
		Label passedLevelLabel = new Label("COMPLETED");
		passedLevelLabel.setStyle("-fx-font-size: 24px; -fx-font-family:\"Arial Black\";");
		passedLevelCount = new Label("0");
		passedLevelCount.setStyle("-fx-font-size: 24px; -fx-font-family:\"Arial Black\";");
		
		
		VBox timeLeftVBox = new VBox(10);
		timeLeftVBox.setPadding(new Insets(13,0,0,0));
		timeLeftVBox.setAlignment(Pos.CENTER);
		timeLeftVBox.getChildren().addAll(timeLeftLabel,canvas);
		
		VBox passedLabelVBox = new VBox(10);
		passedLabelVBox.setAlignment(Pos.CENTER);
		passedLabelVBox.getChildren().addAll(passedLevelLabel,passedLevelCount);
		
		HBox labelHBox = new HBox(50);
		labelHBox.setAlignment(Pos.CENTER);
		labelHBox.getChildren().addAll(timeLeftVBox,passedLabelVBox);
		
		
		
		HBox newAndPauseHBox = new HBox(30);
		newAndPauseHBox.setAlignment(Pos.CENTER);
		newAndPauseHBox.getChildren().addAll(newPuzzleButton,pauseButton);
		
		backButton = new BackButton(100, 120, 41);
		backHBox.setAlignment(Pos.CENTER_RIGHT);
		backHBox.getChildren().add(backButton);
		
		getChildren().addAll(levelLabel, penaltyLabel,labelHBox, newAndPauseHBox, resetAndUndoHBox, backHBox);
	}
	
	public Button getNewPuzzleButton() {
		return newPuzzleButton;
	}
	
	public void setTimeLeft(int timeLeft) {
		this.timeLeft = timeLeft;
	}
	
	public void setPassedLevelLabel(int passedLevel) {
		passedLevelCount.setText(""+passedLevel);
	}
	
	public Button getPauseButton() {
		return pauseButton;
	}
	
	public GraphicsContext getGc() {
		return gc;
	}
	public void drawCurrentTimeString(GraphicsContext gc){
		gc.setFill(Color.web("#323232"));
		gc.setFont(new Font("Arial Black",24));
		gc.clearRect(0, 0, this.canvas.getWidth(), this.canvas.getHeight());
		gc.fillText("" + this.timeLeft, 15, 27);
	}

	@Override
	protected void setAllStyle()
	{
		setStyle("-fx-background-color: #FFED9F; -fx-border-color: #FFD728;-fx-border-width: 4px;");
		levelLabel.setStyle("-fx-font-size: 32px; -fx-font-family:\"Arial Black\";-fx-background-color: #FFD728;");
	}
}