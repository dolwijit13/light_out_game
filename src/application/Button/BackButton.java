package application.Button;

import application.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class BackButton extends Button implements Clickable {
	// 0 -> StartMenu
	// 1 -> MainMenu
	private Pane pane;

	public BackButton(int h, int w, Pane pane) {
		super("");
		ImageView backImageView = new ImageView(new Image(ClassLoader.getSystemResource("assets/back.png").toString()));
		backImageView.setFitWidth(w);
		backImageView.setFitHeight(h);
		setGraphic(backImageView);

		if (pane == null) // special case
		{
			setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					Main.exit();
				}
			});
		} else {
			this.pane = pane;
			setOnClick();
		}
		setOnMouseEnteredAndExited();
	}

	@Override
	public void setOnClick() {
		setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				BackButton.playSoundEffect();
				Main.changeScene(pane);
			}
		});
	}

	public static void playSoundEffect() // In case of Override
	{
		Main.playSoundEffect("click.wav");
	}

	@Override
	public void setOnMouseEnteredAndExited() {
		setStyle(onMouseExitedStyle);
		setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				setStyle(onMouseEnteredStyle);
			}
		});

		setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				setStyle(onMouseExitedStyle);
			}
		});
	}
}
