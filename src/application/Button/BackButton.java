package application.Button;

import application.Main;
import application.MainMenu;
import application.StartMenu;
import application.LevelSelection.ClassicLevelSelection;
import application.LevelSelection.DrawLevelSelection;
import application.LevelSelection.TriColorLevelSelection;
import application.Mode.ModeSelection;
import application.PlayerData.SavingSelection;
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
		else if(mode == 2)
		{
			setOnAction(new EventHandler<ActionEvent>()
			{
				@Override
				public void handle(ActionEvent event)
				{
					SavingSelection savingSelection = new SavingSelection();
					Main.changeScene(savingSelection);
				}
			});
		}
		else if(mode == 3)
		{
			setOnAction(new EventHandler<ActionEvent>()
			{
				@Override
				public void handle(ActionEvent event)
				{
					ModeSelection modeSelection = new ModeSelection();
					Main.changeScene(modeSelection);
				}
			});
		}
		else if(mode == 40)
		{
			setOnAction(new EventHandler<ActionEvent>()
			{
				@Override
				public void handle(ActionEvent event)
				{
					ClassicLevelSelection classicLevelSelection = new ClassicLevelSelection();
					Main.changeScene(classicLevelSelection);
				}
			});
		}
		else if(mode == 41)
		{
			setOnAction(new EventHandler<ActionEvent>()
			{
				@Override
				public void handle(ActionEvent event)
				{
					ModeSelection modeSelection = new ModeSelection();
					Main.changeScene(modeSelection);
				}
			});
		}
		else if(mode == 42)
		{
			setOnAction(new EventHandler<ActionEvent>()
			{
				@Override
				public void handle(ActionEvent event)
				{
					DrawLevelSelection drawLevelSelection = new DrawLevelSelection();
					Main.changeScene(drawLevelSelection);
				}
			});
		}
		else if(mode == 43)
		{
			setOnAction(new EventHandler<ActionEvent>()
			{
				@Override
				public void handle(ActionEvent event)
				{
					TriColorLevelSelection triColorLevelSelection = new TriColorLevelSelection();
					Main.changeScene(triColorLevelSelection);
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
