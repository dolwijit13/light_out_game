package application;

import javafx.geometry.Insets;
import javafx.scene.layout.HBox;

public class ClassicMode extends HBox
{
	public ClassicMode(int gap,int level)
	{
		super(gap);
		setPadding(new Insets(10, 10, 10, 10));
		level--;
		int n=5+level/5;
		Board board = new Board(n);
		GameMenu gameMenu = new GameMenu(10);
		getChildren().addAll(board,gameMenu);
	}
}
