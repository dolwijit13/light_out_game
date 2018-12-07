package application.Button;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class OKButton extends Button
{
	public OKButton(int h, int w)
	{
		ImageView okImageView = new ImageView(new Image(ClassLoader.getSystemResource("assets/ok.png").toString()));
		okImageView.setFitWidth(w);
		okImageView.setFitHeight(h);

		setGraphic(okImageView);
		setStyle("-fx-background-color: transparent");

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
