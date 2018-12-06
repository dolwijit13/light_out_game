package application.PassLevel;

public class TimerPassLevel extends PassLevel {

	public TimerPassLevel(int penalty, int passedLevel) {
		super(passedLevel,penalty,passedLevel);
		clearLevelLabel.setText("CLEARED " + passedLevel + " LEVEL(S)");
	}
}
