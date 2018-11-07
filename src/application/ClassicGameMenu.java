package application;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class ClassicGameMenu extends GameMenu
{
	public ClassicGameMenu(int gap)
	{
		super(gap);
		HBox helper = new HBox(10);
		helper.setAlignment(Pos.CENTER);
		Button help1 = new Button("Help 1");
		Button help2 = new Button("Help 2");
		helper.getChildren().addAll(help1, help2);
		getChildren().addAll(logo, score, helper, toMainMenuHbox);
	}
}
