package application.Button;

public interface Clickable
{
	String onMouseEnteredStyle = "-fx-background-color:#dae7f3;";
	String onMouseExitedStyle = "-fx-background-color:transparent;";
	
	void setOnClick();
	void setOnMouseEnteredAndExited();
}
