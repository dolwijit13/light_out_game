package application.Button;

import application.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class OKButton extends Button implements Clickable
{
	public OKButton(int h, int w)
	{
		ImageView okImageView = new ImageView(new Image(ClassLoader.getSystemResource("assets/ok.png").toString()));
		okImageView.setFitWidth(w);
		okImageView.setFitHeight(h);

		setGraphic(okImageView);
		
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
				OKButton.playSoundEffect();
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
