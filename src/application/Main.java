package application;

import java.io.FileNotFoundException;

import application.Mode.ClassicMode;
import application.Mode.DrawMode;
import application.Mode.TriColorMode;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Main extends Application
{
	public static Pane pane;
	private static Stage stage;
	private static MediaPlayer bgmMediaPlayer;
	private static MediaPlayer soundEffectMediaPlayer;

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
	
	public static void playSoundEffect(String sound) {
		Thread soundEffectThread = new Thread(() -> {
			try {
				Media soundEffect = new Media(ClassLoader.getSystemResource("assets/sounds/"+sound+".wav").toString());
				soundEffectMediaPlayer = new MediaPlayer(soundEffect);
				soundEffectMediaPlayer.setCycleCount(1);
				soundEffectMediaPlayer.play();
			} catch(Exception e){
				e.printStackTrace();
			}
		});
		soundEffectThread.start();
	}
	
	public static void playBGM(String sound) {
		Thread bgmThread = new Thread(() -> {
			try {
				Media bgm = new Media(ClassLoader.getSystemResource("assets/sounds/"+sound+".wav").toString());
				bgmMediaPlayer = new MediaPlayer(bgm);
				bgmMediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
				bgmMediaPlayer.play();
			} catch(Exception e){
				e.printStackTrace();
			}
		});
		bgmThread.start();
	}
}
