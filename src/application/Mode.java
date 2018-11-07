package application;

import javafx.geometry.Insets;
import javafx.scene.layout.HBox;

public abstract class Mode extends HBox
{
	Board board;
	GameMenu gameMenu;
	
	public Mode(int gap)
	{
		super(gap);
		setPadding(new Insets(10, 10, 10, 10));
	}
}
