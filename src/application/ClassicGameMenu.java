package application;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class ClassicGameMenu extends GameMenu
{
	private static int penalty;

	public ClassicGameMenu(int gap)
	{
		super(gap);
		penalty = 0;
		HBox helper = new HBox(10);
		helper.setAlignment(Pos.CENTER);
		Button help1 = new Button("Help 1");
		Button help2 = new Button("Help 2");
		help1.setOnMouseClicked(mouseClick);
		helper.getChildren().addAll(help1, help2);
		getChildren().addAll(logo, penaltyText, penaltyLabel, helper, toMainMenuButton);
	
		
	}

	public static int addPenalty(int add)
	{
		penalty+=add;
		penaltyLabel.setText(""+penalty);
		return penalty;
	}
	
	private final EventHandler<MouseEvent> mouseClick = new EventHandler<MouseEvent>()
	{
		@Override
		public void handle(MouseEvent event)
		{
			addPenalty(5);
		}
	};
}
