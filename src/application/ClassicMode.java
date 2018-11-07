package application;

public class ClassicMode extends Mode
{
	public ClassicMode(int level)
	{
		super(10);
		level--;
		int n = 5 + level / 5;
		Board board = new Board(n, 2);
		GameMenu gameMenu = new ClassicGameMenu(10);
		getChildren().addAll(board, gameMenu);
	}
}
