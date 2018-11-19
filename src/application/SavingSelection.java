package application;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class SavingSelection extends LoadingSelection
{
	public SavingSelection()
	{
		super();
		OKButton.setOnMouseClicked(new EventHandler<MouseEvent>()
		{
			@Override
			public void handle(MouseEvent event)
			{
				int n=((PlayerButton)LoadingSelection.selectedPlayer).n;
				PlayerInfo.saveToN(n);
				StartMenu startMenu = new StartMenu();
				Main.changeScene(startMenu);
			}
		});
	}
}
