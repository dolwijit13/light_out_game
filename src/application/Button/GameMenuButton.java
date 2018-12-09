package application.Button;

import application.Main;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class GameMenuButton extends Button implements Clickable
{
	public GameMenuButton(int h, int w, String path)
	{
		ImageView imageView = new ImageView(new Image(ClassLoader.getSystemResource("assets/"+path).toString()));
		imageView.setFitWidth(w);
		imageView.setFitHeight(h);
	
		setPrefHeight(h);
		setPrefWidth(w);
		
		setGraphic(imageView);
		getChildren().addAll(imageView);
		
		setOnClick();
		setOnMouseEnteredAndExited();
	}

	@Override
	public void setOnClick()
	{
		setOnMouseClicked(new EventHandler<MouseEvent>()
		{
			@Override
			public void handle(MouseEvent event)
			{
				GameMenuButton.playSoundEffect();
			}
		});
	}
	
	public static void playSoundEffect() //In case of Override
	{
		Main.playSoundEffect("click.wav");
	}

	@Override
	public void setOnMouseEnteredAndExited()
	{
		setStyle(onMouseExitedStyle);
		setOnMouseEntered(new EventHandler<MouseEvent>()
		{
			@Override
			public void handle(MouseEvent t)
			{
				setStyle(onMouseEnteredStyle);
			}
		});

		setOnMouseExited(new EventHandler<MouseEvent>()
		{
			@Override
			public void handle(MouseEvent t)
			{
				setStyle(onMouseExitedStyle);
			}
		});
	}
}
