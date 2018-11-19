package application;

import java.util.Optional;

import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
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
				PlayerButton selectedPlayer = LoadingSelection.selectedPlayer;
				int n=selectedPlayer.n;
				if(!selectedPlayer.name.equals(""))
				{
					Alert alert = new Alert(AlertType.CONFIRMATION);
					alert.setTitle("Overwriting");
					alert.setHeaderText("Are you sure?");
					alert.setContentText(
							"Slot " + n + " already saved\nAre you sure to save this slot?");
					Optional<ButtonType> result = alert.showAndWait();
					if(!result.isPresent())
					{
						MainMenu mainMenu = new MainMenu();
						Main.changeScene(mainMenu);
					}
					else if(result.get() == ButtonType.OK)
					{
						PlayerInfo.saveToN(n);
						StartMenu startMenu = new StartMenu();
						Main.changeScene(startMenu);
					}
					else if(result.get() == ButtonType.CANCEL)
					{
						MainMenu mainMenu = new MainMenu();
						Main.changeScene(mainMenu);
					}
					return;
				}
				PlayerInfo.saveToN(n);
				StartMenu startMenu = new StartMenu();
				Main.changeScene(startMenu);
			}
		});
	}
}