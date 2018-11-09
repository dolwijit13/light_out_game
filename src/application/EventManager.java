package application;

import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;

public class EventManager
{
	private Board board;

	public EventManager(Board board)
	{
		this.board = board;
	}

	public void setUpLightEvent(Board board)
	{
		board.setOnMouseClicked(new EventHandler<MouseEvent>()
		{
			@Override
			public void handle(MouseEvent event)
			{
				MouseButton button = event.getButton();
				int n = board.getN();
				
				if (button == MouseButton.PRIMARY)
				{
					Light b = (Light) event.getSource();
					int ID = Integer.parseInt(b.getId());
					int x = ID / n, y = ID % n; // System.out.println("a " +ID+" "+x+" "+y);
					board.changeColor(x, y, true);
				}
				else if (button == MouseButton.SECONDARY)
				{
					Light b = (Light) event.getSource();
					int ID = Integer.parseInt(b.getId());
					int x = ID / n, y = ID % n; // System.out.println("a " +ID+" "+x+" "+y);
					board.changeColor(x, y, false);
				}
				ClassicGameMenu.addPenalty(5);
				if (board.isWinLevel())
					board.toNextLevel(board.getCurLevel());
			}
			
		});
	}

}
