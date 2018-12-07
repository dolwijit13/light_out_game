package application.Button;

import application.Main;
import application.MainMenu;
import application.StartMenu;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class BackButton extends Button
{
	// 0 -> StartMenu
	// 1 -> MainMenu
	private int mode;

	public BackButton(int h, int w, int mode)
	{
		super("");
		ImageView backImageView = new ImageView(new Image(ClassLoader.getSystemResource("assets/back.png").toString()));
		backImageView.setFitWidth(w);
		backImageView.setFitHeight(h);
		setGraphic(backImageView);
		setStyle("-fx-background-color: transparent");

		if(mode == 0)
		{
			setOnAction(new EventHandler<ActionEvent>()
			{
				@Override
				public void handle(ActionEvent event)
				{
					StartMenu startMenu = new StartMenu();
					Main.changeScene(startMenu);
				}
			});
		}
		else if(mode == 1)
		{
			setOnAction(new EventHandler<ActionEvent>()
			{
				@Override
				public void handle(ActionEvent event)
				{
					MainMenu mainMenu = new MainMenu();
					Main.changeScene(mainMenu);
				}
			});
		}

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
