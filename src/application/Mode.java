package application;

import javafx.geometry.Insets;
import javafx.scene.layout.HBox;

public abstract class Mode extends HBox
{
	Board board;
	GameMenu gameMenu;
	
	public Mode()
	{
		super(10);
		setPadding(new Insets(10, 10, 10, 10));
	}
}
