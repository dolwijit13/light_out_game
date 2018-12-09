package application.Button;

import application.Main;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class PictureWithTextButton extends StackPane implements Clickable
{
	private ImageView state1;
	private ImageView state2;
	private Text text;
	
	public PictureWithTextButton(int h, int w, int number, String textString,int fontSize)
	{
		state1 = new ImageView(new Image(ClassLoader.getSystemResource("assets/button/"+number+"1.png").toString()));
		state1.setFitWidth(w);
		state1.setFitHeight(h);
		
		state2 = new ImageView(new Image(ClassLoader.getSystemResource("assets/button/"+number+"2.png").toString()));
		state2.setFitWidth(w);
		state2.setFitHeight(h);
		
		text = new Text(textString);
		text.setFont(Font.font("Lucida Console", fontSize));
		text.setFill(Color.BLACK);
		
		setPrefHeight(h);
		setPrefWidth(w);

		getChildren().addAll(state1,state2, text);
		
		state2.setVisible(false);
		
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
				PictureWithTextButton.playSoundEffect();
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
				state1.setVisible(false);
				state2.setVisible(true);
				text.setFill(Color.WHITE);
			}
		});

		setOnMouseExited(new EventHandler<MouseEvent>()
		{
			@Override
			public void handle(MouseEvent t)
			{
				state1.setVisible(true);
				state2.setVisible(false);
				text.setFill(Color.BLACK);
			}
		});
	}
}
