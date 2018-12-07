package application.PlayerData;

import application.Main;
import application.MainMenu;
import application.Button.BackButton;
import application.Button.OKButton;
import application.Button.ToStartMenuButton;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class CreateNewPlayer extends VBox
{
	public CreateNewPlayer()
	{
		super(50);
		setStyle("-fx-background-color: #7DA3A1");
		
		VBox upVBox = new VBox();
		Label upLabel = new Label("Create New Player");
		upLabel.setStyle("-fx-font-size: 64px; -fx-font-family:\"Arial Black\";");
		upVBox.setAlignment(Pos.CENTER);
		upLabel.setTextFill(Color.web("#B7B8B6"));
		upLabel.setPrefHeight(120);
		upVBox.getChildren().add(upLabel);
		upVBox.setStyle("-fx-background-color: #324851");
		
		VBox nameVBox = new VBox(20);
		Label nameLabel = new Label("What's youre name?");
		nameLabel.setStyle("-fx-font-size: 48px; -fx-font-family:\"Arial Black\";");
		nameLabel.setTextFill(Color.web("#FFFFFF"));
		TextField nameField = new TextField();
		nameField.setPrefHeight(50);
		nameField.setFont(Font.font("Verdana", FontWeight.BOLD, 50));
		nameVBox.setPadding(new Insets(80,350,80,350));
		nameVBox.setAlignment(Pos.CENTER);
		nameVBox.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5); -fx-background-radius: 10;");
		nameVBox.getChildren().addAll(nameLabel,nameField);
		
		OKButton okButton = new OKButton(100, 120);
		okButton.setDisable(true);
		BackButton toStartMenuButton = new BackButton(100, 120, 0);
		
		HBox buttonHbox = new HBox(20);
		buttonHbox.setAlignment(Pos.CENTER);
		buttonHbox.getChildren().addAll(toStartMenuButton,okButton);
		this.getChildren().addAll(upVBox,nameVBox,buttonHbox);
		
		nameField.textProperty().addListener((observable, oldValue, newValue) -> 
		{
		    okButton.setDisable(isBadName(newValue));
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
	
	private boolean isBadName(String name)
	{
		if(name.length()<3) return true;
		if(name.length()>8) return true;
		for(int i=0;i<name.length();i++)
		{
			char c=name.charAt(i);
			if(!(('a'<=c && c<='z') || ('A'<=c && c<='Z') || ('0'<=c && c<='9')))
			{
				return true;
			}
		}
		return false;
	}
}
