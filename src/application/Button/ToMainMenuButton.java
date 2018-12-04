package application.Button;

import application.Main;
import application.MainMenu;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class ToMainMenuButton extends HBox
{
	public ToMainMenuButton()
	{
		setAlignment(Pos.CENTER_RIGHT);
		Button toMainMenu = new Button("Main Menu");
		getChildren().add(toMainMenu);

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
