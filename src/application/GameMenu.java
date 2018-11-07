package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public abstract class GameMenu extends VBox
{
	Button logo = new Button("BUMP");
	Button score = new Button("Score");
	HBox toMainMenuHbox = new HBox();
	
	public GameMenu(int gap)
	{
		super(gap);
		setAlignment(Pos.CENTER);
		setPrefWidth(540);
		toMainMenuHbox.setAlignment(Pos.CENTER_RIGHT);
		Button toMainMenu = new Button("Main Menu");
		toMainMenuHbox.getChildren().add(toMainMenu);

		toMainMenu.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				MainMenu mainMenu = new MainMenu();
				Main.changeScene(mainMenu);
			}
		});
	}
}
