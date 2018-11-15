package application;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;

public class ClassicGameMenu extends GameMenu
{

	Button help1Button;
	Button help2Button;
	Button help3Button;

	public ClassicGameMenu()
	{
		HBox helper = new HBox(10);
		helper.setAlignment(Pos.CENTER);
		help1Button = new Button("Help 1");
		help1Button.setDisable(true);
		help1Button.setTooltip(new Tooltip("Help1"));
		help2Button = new Button("Help 2");
		help3Button = new Button("Help 3");
		helper.getChildren().addAll(help1Button, help2Button, help3Button);
		getChildren().addAll(logo, penaltyText, penaltyLabel, helper, resetButton, undoButton, toMainMenuButton);
	}

	public Button getHelp1Button()
	{
		return help1Button;
	}

	public Button getHelp2Button()
	{
		return help2Button;
	}
}
