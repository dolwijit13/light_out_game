package application.Button;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class PictureWithTextButton extends StackPane
{
	public PictureWithTextButton(int h, int w, int number, String textString)
	{
		ImageView state1 = new ImageView(new Image(ClassLoader.getSystemResource("assets/button/"+number+"1.png").toString()));
		state1.setFitWidth(w);
		state1.setFitHeight(h);
		
		ImageView state2 = new ImageView(new Image(ClassLoader.getSystemResource("assets/button/"+number+"2.png").toString()));
		state2.setFitWidth(w);
		state2.setFitHeight(h);
		
		Text text = new Text(textString);
		text.setFont(Font.font("Lucida Console", 40));
		text.setFill(Color.BLACK);

		setStyle("-fx-background-color: transparent");
		getChildren().addAll(state1,state2, text);
		
		state2.setVisible(false);
		
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
