package application;

import com.sun.webkit.ThemeClient;

import javafx.scene.media.AudioClip;

public class Audio
{
	public static AudioClip click;
	
	public Audio(String path)
	{
		if(path=="click")
		{
			click.play();
		}
	}
	
	public static void load()
	{
		click=new AudioClip(ClassLoader.getSystemResource("assets/sounds/click.wav").toString());
	}

	public static void play(String path)
	{
		Thread thread = new Thread(() -> {
			new Audio(path);
		});
		thread.start();
	}
}
