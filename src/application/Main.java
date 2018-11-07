package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class Main extends Application
{
	public static Pane pane = new Pane();
	private static Stage stage;

	@Override
	public void start(Stage primaryStage)
	{
		ClassicMode.readLevel();
		try
		{
			StartMenu startMenu = new StartMenu();
			stage = primaryStage;
			changeScene(startMenu);
			stage.show();

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static void changeScene(Pane pane)
	{
		Scene scene = new Scene(pane, 1280, 720);
		stage.setScene(scene);
	}

	public static void exit()
	{
		stage.close();
	}

	public static void main(String[] args)
	{
		launch(args);
	}
}
