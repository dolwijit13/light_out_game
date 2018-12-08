package application;

import java.io.FileNotFoundException;

import application.Mode.ClassicMode;
import application.Mode.DrawMode;
import application.Mode.TriColorMode;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class Main extends Application
{
	public static Pane pane;
	private static Stage stage;

	@Override
	public void start(Stage primaryStage)
	{
		ClassicMode.readLevel();
		DrawMode.readLevel();
		TriColorMode.readLevel();
		Audio.load();

		try
		{
			StartMenu startMenu = new StartMenu();
			stage = primaryStage;
			changeScene(startMenu);
			stage.setResizable(false);
			stage.show();

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static void changeScene(Pane pane)
	{
		Main.pane = pane;
		Scene scene = new Scene(pane, 1280, 720);
		stage.setScene(scene);
	}

	public static void exit()
	{
		stage.close();
	}

	public static void main(String[] args) throws FileNotFoundException
	{
		launch(args);
	}
}
