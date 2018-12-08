package application.PassLevel;

public class TimerPassLevel extends PassLevel {

	public TimerPassLevel(int penalty, int passedLevel) {
		super(1,passedLevel,penalty,passedLevel);
		clearLevelLabel.setText("CLEARED " + passedLevel + " LEVEL(S)");
		getChildren().add(toLevelSelectionHBox);
	}
}
