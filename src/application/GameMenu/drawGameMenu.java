package application.GameMenu;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;

public class drawGameMenu extends GameMenu
{
	Button help1Button;
	Button help2Button;
	Button help3Button;

	public drawGameMenu()
	{
		Button dummy = new Button("dummy");

		HBox helper = new HBox(10);
		helper.setAlignment(Pos.CENTER);
		help1Button = new Button("Help 1");
		help1Button.setDisable(true);
		help1Button.setTooltip(new Tooltip("Help1"));
		help2Button = new Button("Help2");
		help3Button = new Button("Help 3");
		helper.getChildren().addAll(help1Button, help2Button, help3Button);
		getChildren().addAll(logo, dummy, penaltyText, penaltyLabel, helper, resetButton, undoButton, toMainMenuButton);
	}

	public Button getHelp1Button()
	{
		return help1Button;
	}

	public Button getHelp2Button()
	{
		return help2Button;
	}

	public Button getHelp3Button()
	{
		return help3Button;
	}
	
	
}
