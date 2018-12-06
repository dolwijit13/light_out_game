package application.PlayerData;

import application.Main;
import application.MainMenu;
import application.Button.ToStartMenuButton;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class CreateNewPlayer extends VBox
{
	public CreateNewPlayer()
	{
		super(5);
		HBox hBox = new HBox(5);
		Label nameLabel = new Label("Name : ");
		TextField nameField = new TextField();
		hBox.getChildren().addAll(nameLabel,nameField);
		Button okButton = new Button("OK");
		okButton.setDisable(true);
		ToStartMenuButton toStartMenuButton = new ToStartMenuButton("Back");
		
		HBox buttonHbox = new HBox(5);
		buttonHbox.getChildren().addAll(toStartMenuButton,okButton);
		this.getChildren().addAll(hBox,buttonHbox);
		
		nameField.textProperty().addListener((observable, oldValue, newValue) -> 
		{
		    if(newValue.length()>2)
		    {
		    	okButton.setDisable(false);
		    }
		    else
		    {
		    	okButton.setDisable(true);
		    }
		});
		
		okButton.setOnMouseClicked(new EventHandler<MouseEvent>()
		{
			@Override
			public void handle(MouseEvent event)
			{
				PlayerInfo playerInfo = new PlayerInfo(nameField.getText());
				PlayerInfo.setSelectedPlayerInfo(playerInfo);
				MainMenu mainMenu = new MainMenu();
				Main.changeScene(mainMenu);
			}
		});
	}
}
