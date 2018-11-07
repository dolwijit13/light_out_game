package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class GameMenu extends VBox
{
	public GameMenu(int gap)
	{
		super(gap);
		setAlignment(Pos.CENTER);
		setPrefWidth(540);
		Button logo = new Button("BUMP");
		Button score = new Button("Score");
		HBox helper = new HBox(10);
		helper.setAlignment(Pos.CENTER);
		Button help1 = new Button("Help 1");
		Button help2 = new Button("Help 2");
		helper.getChildren().addAll(help1, help2);
		HBox hBox = new HBox();
		hBox.setAlignment(Pos.CENTER_RIGHT);
		Button toMainMenu = new Button("Main Menu");
		hBox.getChildren().add(toMainMenu);
		getChildren().addAll(logo, score, helper, hBox);

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
