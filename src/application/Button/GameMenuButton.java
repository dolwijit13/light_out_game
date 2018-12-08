package application.Button;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class GameMenuButton extends Button
{
	public GameMenuButton(int h, int w, String path)
	{
		ImageView imageView = new ImageView(new Image(ClassLoader.getSystemResource("assets/"+path).toString()));
		imageView.setFitWidth(w);
		imageView.setFitHeight(h);
	
		setPrefHeight(h);
		setPrefWidth(w);
		
		setGraphic(imageView);
		setStyle("-fx-background-color: transparent");
		getChildren().addAll(imageView);
		
		setOnMouseEntered(new EventHandler<MouseEvent>()
		{
			@Override
			public void handle(MouseEvent t)
			{
				setStyle("-fx-background-color:#dae7f3;");
			}
		});

		setOnMouseExited(new EventHandler<MouseEvent>()
		{
			@Override
			public void handle(MouseEvent t)
			{
				setStyle("-fx-background-color:transparent;");
			}
		});
	}
}
