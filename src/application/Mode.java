package application;

import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public abstract class Mode extends StackPane
{
	Board board;
	GameMenu gameMenu;
	HBox hBox;

	public Mode()
	{
		hBox = new HBox(10);
		hBox.setPadding(new Insets(10, 10, 10, 10));
		getChildren().addAll(hBox);
	}
}
