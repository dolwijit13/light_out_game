package application.GameMenu;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class TimerGameMenu extends GameMenu
{
	public TimerGameMenu(int penalty)
	{
		super.penalty = penalty;
		Text timeLeftText = new Text("Time left");
		Label timeLeftLabel = new Label("test");
		Text passedLevelText = new Text("Completed");
		Label passedLevelLabel = new Label();
		GridPane gridPane = new GridPane();
		gridPane.add(timeLeftText, 0, 0);
		gridPane.add(timeLeftLabel, 0, 1);
		gridPane.add(passedLevelText, 1, 0);
		gridPane.add(passedLevelLabel, 1, 1);
		gridPane.add(penaltyText, 2, 0);
		gridPane.add(penaltyLabel, 2, 1);
		gridPane.setHgap(40);
		gridPane.setVgap(10);
		gridPane.setAlignment(Pos.CENTER);
		getChildren().addAll(logo, gridPane, resetButton, undoButton, toMainMenuButton);
	}
}