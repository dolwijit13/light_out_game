package application.Button;

import application.Main;
import application.StartMenu;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class ToStartMenuButton extends Button
{
	public ToStartMenuButton()
	{
		super("Start Menu");
		setAlignment(Pos.CENTER);

		this.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				StartMenu startMenu = new StartMenu();
				Main.changeScene(startMenu);
			}
		});
	}
	
	public ToStartMenuButton(String s)
	{
		super(s);
		setAlignment(Pos.CENTER);

		this.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				StartMenu startMenu = new StartMenu();
				Main.changeScene(startMenu);
			}
		});
	}
}
