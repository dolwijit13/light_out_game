package application.Button;

import application.Main;
import application.StartMenu;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class ToStartMenuButton extends HBox
{
	Button toStartMenuButton;
	public ToStartMenuButton()
	{
		toStartMenuButton = new Button("Start Menu");
		setAlignment(Pos.CENTER);
		getChildren().add(toStartMenuButton);
		setButton();
	}
	
	public ToStartMenuButton(String s)
	{
		toStartMenuButton = new Button(s);
		setAlignment(Pos.CENTER);
		getChildren().add(toStartMenuButton);
		setButton();
	}
	
	public ToStartMenuButton(String s,int h,int w)
	{
		toStartMenuButton = new Button(s);
		setAlignment(Pos.CENTER);
		toStartMenuButton.setPrefHeight(h);
		toStartMenuButton.setPrefWidth(w);
		getChildren().add(toStartMenuButton);
		setButton();
	}
	
	public Button getButton()
	{
		return toStartMenuButton;
	}
	
	private void setButton()
	{
		toStartMenuButton.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				StartMenu startMenu = new StartMenu();
				Main.changeScene(startMenu);
			}
		});
		
		toStartMenuButton.setOnMouseEntered(new EventHandler<MouseEvent>
	    () {

	        @Override
	        public void handle(MouseEvent t) {
	           toStartMenuButton.setStyle("-fx-background-color:#dae7f3;");
	        }
	    });

	    toStartMenuButton.setOnMouseExited(new EventHandler<MouseEvent>
	    () {

	        @Override
	        public void handle(MouseEvent t) {
	           toStartMenuButton.setStyle("-fx-background-color:transparent;");
	        }
	    });
	}
}
