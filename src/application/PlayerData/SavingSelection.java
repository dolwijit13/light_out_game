package application.PlayerData;

import java.util.Optional;

import application.Main;
import application.MainMenu;
import application.StartMenu;
import application.Button.BackButton;
import application.Button.OKButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;

public class SavingSelection extends LoadingSelection {

	public SavingSelection() {
		super(false);
		setStyle("-fx-background-color: #A2C523");
		upVBox.setStyle("-fx-background-color: #2E4600");

		okButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				OKButton.playSoundEffect();
				PlayerButton selectedPlayer = LoadingSelection.selectedPlayer;
				int n = selectedPlayer.n;
				if (!selectedPlayer.name.equals(" ")) {
					Alert alert = new Alert(AlertType.CONFIRMATION);
					alert.setTitle("Overwriting");
					alert.setHeaderText("Are you sure?");
					alert.setContentText("Slot " + n + " already saved\nAre you sure to save this slot?");
					Optional<ButtonType> result = alert.showAndWait();
					if (!result.isPresent()) {
						MainMenu mainMenu = new MainMenu();
						Main.changeScene(mainMenu);
					} else if (result.get() == ButtonType.OK) {
						PlayerInfo.saveToN(n);
						StartMenu startMenu = new StartMenu();
						Main.changeScene(startMenu);
					} else if (result.get() == ButtonType.CANCEL) {
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

		returnButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				BackButton.playSoundEffect();
				MainMenu mainMenu = new MainMenu();
				Main.changeScene(mainMenu);
			}
		});
	}
}
