package application.GameMenu;

import application.Button.ToMainMenuButton;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public abstract class GameMenu extends VBox
{
	protected Button logo = new Button("BUMP");
	protected Text penaltyText = new Text("Penalty");
	protected Label penaltyLabel = new Label("0");
	protected ToMainMenuButton toMainMenuButton = new ToMainMenuButton();
	protected int penalty = 0;
	protected Button resetButton = new Button("Reset");
	protected Button undoButton = new Button("Undo");

	public GameMenu()
	{
		super(10);
		setAlignment(Pos.CENTER);
		setPrefWidth(540);
		setBorder(new Border(
				new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		setBackground(new Background(new BackgroundFill(Color.IVORY, null, null)));
		
	}

	public int addPenalty(int add)
	{
		penalty += add;
		penaltyLabel.setText("" + penalty);
		return penalty;
	}

	public int getPenalty()
	{
		return penalty;
	}

	public Button getResetButton()
	{
		return resetButton;
	}
	
	public Button getUndoButton()
	{
		return undoButton;
	}
}
