package application;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public abstract class GameMenu extends VBox
{
	Button logo = new Button("BUMP");
	Button score = new Button("Score");
	ToMainMenuButton toMainMenuButton = new ToMainMenuButton();
	
	public GameMenu(int gap)
	{
		super(gap);
		setAlignment(Pos.CENTER);
		setPrefWidth(540);
	}
}
