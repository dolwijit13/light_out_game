package application;

import java.io.FileNotFoundException;

import application.LevelSelection.ClassicLevelSelection;
import application.LevelSelection.DrawLevelSelection;
import application.LevelSelection.TriColorLevelSelection;
import application.Mode.ClassicMode;
import application.Mode.DrawMode;
import application.Mode.ModeSelection;
import application.Mode.TimerMode;
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
	private static Thread bgmThread;
	private static String oldSound = "";

	@Override
	public void start(Stage primaryStage)
	{
		ClassicMode.readLevel();
		DrawMode.readLevel();
		TriColorMode.readLevel();

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
		if (pane instanceof ModeSelection)
			setBGM("menu.mp3",1);
		else if (pane instanceof ClassicLevelSelection)
			setBGM("classicMode.mp3",0.5);
		else if (pane instanceof TimerMode)
			setBGM("timerMode.mp3",0.3);
		else if (pane instanceof DrawLevelSelection)
			setBGM("drawMode.mp3",0.8);
		else if (pane instanceof TriColorLevelSelection)
			setBGM("triColorMode.mp3",0.5); 
		Scene scene = new Scene(pane, 1280, 720);
		stage.setScene(scene);
	}

	public static void exit()
	{
		stage.close();
	}

	public static void main(String[] args) throws FileNotFoundException
	{
		setBGM("menu.mp3",1);
		launch(args);
	}

	public static void playSoundEffect(String sound)
	{
		Thread soundEffectThread = new Thread(() -> {
			try
			{
				Media soundEffect = new Media(ClassLoader.getSystemResource("assets/sounds/" + sound).toString());
				soundEffectMediaPlayer = new MediaPlayer(soundEffect);
				soundEffectMediaPlayer.setCycleCount(1);
				soundEffectMediaPlayer.play();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		});
		soundEffectThread.start();
	}

	public static void setBGM(String sound, double volumn)
	{
		if (sound == oldSound)
			return;
		if (bgmThread != null)
		{
			bgmThread.interrupt();
			bgmMediaPlayer.stop();
		}
		oldSound = sound;
		bgmThread = new Thread(() -> {
			try
			{
				Media bgm = new Media(ClassLoader.getSystemResource("assets/sounds/" + sound).toString());
				bgmMediaPlayer = new MediaPlayer(bgm);
				bgmMediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
				bgmMediaPlayer.setVolume(volumn);
				bgmMediaPlayer.play();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		});
		bgmThread.start();
	}
}
