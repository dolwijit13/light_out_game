package application;

import application.Button.BackButton;
import application.Button.PictureWithTextButton;
import application.Mode.ModeSelection;
import application.PlayerData.SavingSelection;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.VBox;

public class MainMenu extends VBox
{
	public MainMenu()
	{
		super(100);
		setBackground(new Background(new BackgroundImage(
				new Image(ClassLoader.getSystemResource("assets/MainMenu.png").toString()), null, null, null,
				null)));
		
		//System.out.println(PlayerInfo.getSelectedPlayerInfo().name);
		
		setAlignment(Pos.TOP_RIGHT);
		setPadding(new Insets(100, 90, 5, 860));
		Button logo = new Button("AU");
		
		PictureWithTextButton play = new PictureWithTextButton(84, 268, 3, "PLAY");
		PictureWithTextButton gallery = new PictureWithTextButton(84, 268, 5, "GALLERY");
		BackButton backAndSave = new BackButton(100, 120, 2);
		
		VBox outVBox = new VBox(50);
		outVBox.setAlignment(Pos.TOP_CENTER);
		outVBox.setPadding(new Insets(50, 0, 50, 0));
		outVBox.getChildren().addAll(logo,play,gallery);
		//outVBox.setStyle("-fx-border-color: #828282;");
		outVBox.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5); -fx-background-radius: 10;");
		
		getChildren().addAll(outVBox,backAndSave);
		
		play.setOnMouseClicked(new EventHandler<MouseEvent>()
		{
			@Override
			public void handle(MouseEvent event)
			{
				ModeSelection modeSelection = new ModeSelection();
				Main.changeScene(modeSelection);
			}

		});
		
		gallery.setOnMouseClicked(new EventHandler<MouseEvent>()
		{
			@Override
			public void handle(MouseEvent event)
			{
				Gallery gallery = new Gallery();
				Main.changeScene(gallery);
			}

		});

		backAndSave.setOnMouseClicked(new EventHandler<MouseEvent>()
		{
			@Override
			public void handle(MouseEvent event)
			{
				SavingSelection savingSelection = new SavingSelection();
				Main.changeScene(savingSelection);
			}
		});
	}
}
