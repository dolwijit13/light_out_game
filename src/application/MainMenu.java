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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MainMenu extends VBox
{
	public MainMenu()
	{
		super(20);
		
		setBackground(new Background(new BackgroundImage(
				new Image(ClassLoader.getSystemResource("assets/MainMenu.png").toString()), null, null, null,
				null)));
		
		//System.out.println(PlayerInfo.getSelectedPlayerInfo().name);
		
		setAlignment(Pos.TOP_CENTER);
		setPadding(new Insets(100, 90, 5, 870));
		ImageView logo = new ImageView(new Image(ClassLoader.getSystemResource("assets/logo.png").toString()));
		logo.setFitHeight(126);
		logo.setFitWidth(406);
		
		PictureWithTextButton play = new PictureWithTextButton(84, 268, 3, "PLAY",40);
		PictureWithTextButton gallery = new PictureWithTextButton(84, 268, 5, "GALLERY",40);
		BackButton backAndSave = new BackButton(100, 120, new SavingSelection());
		HBox backAndSaveHBox = new HBox();
		backAndSaveHBox.setAlignment(Pos.CENTER_RIGHT);
		backAndSaveHBox.getChildren().add(backAndSave);
		
		VBox outVBox = new VBox(50);
		outVBox.setAlignment(Pos.TOP_CENTER);
		outVBox.setPadding(new Insets(50, 50, 50, 50));
		outVBox.getChildren().addAll(play,gallery);
		//outVBox.setStyle("-fx-border-color: #828282;");
		outVBox.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5); -fx-background-radius: 10;");
		
		getChildren().addAll(logo,outVBox,backAndSaveHBox);
		
		play.setOnMouseClicked(new EventHandler<MouseEvent>()
		{
			@Override
			public void handle(MouseEvent event)
			{
				PictureWithTextButton.playSoundEffect();
				ModeSelection modeSelection = new ModeSelection();
				Main.changeScene(modeSelection);
			}

		});
		
		gallery.setOnMouseClicked(new EventHandler<MouseEvent>()
		{
			@Override
			public void handle(MouseEvent event)
			{
				PictureWithTextButton.playSoundEffect();
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
