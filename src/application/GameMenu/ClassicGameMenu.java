package application.GameMenu;

import application.Button.BackButton;
import application.Button.GameMenuButton;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.HBox;

public class ClassicGameMenu extends GameMenu
{

	protected GameMenuButton help1Button;
	protected GameMenuButton help2Button;
	protected GameMenuButton help3Button;
	protected boolean canHelp2;

	public ClassicGameMenu(int level)
	{
		super(level,37,120,120);
		HBox helper = new HBox(10);
		helper.setAlignment(Pos.CENTER);
		
		help1Button = new GameMenuButton(120, 120, "help1.png");
		help1Button.setDisable(true);
		help1Button.setTooltip(new Tooltip("Immediately pass the level\n(250 Penalty)"));
		
		help2Button = new GameMenuButton(120, 144, "help2.png");
		help2Button.setTooltip(new Tooltip("Show next should press cell\n(50 Penalty)"));
		
		help3Button = new GameMenuButton(120, 132, "help3.png");
		help3Button.setTooltip(new Tooltip("Show the pattern\n(15 Penalty)"));
		
		helper.getChildren().addAll(help1Button, help2Button, help3Button);
		
		addBackButton();
		
		getChildren().addAll(levelLabel, penaltyLabel, helper, resetAndUndoHBox, backHBox);
	}

	protected void addBackButton()
	{
		backButton = new BackButton(100, 120, 40);
		backButton.setTooltip(new Tooltip("Back to Level Selection\n(Unsaved progress will be lost)"));
		backHBox.setAlignment(Pos.CENTER_RIGHT);
		backHBox.getChildren().add(backButton);
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
	
	public void setHelp3Disable()
	{
		help3Button.setDisable(true);
	}
	
	public void setCanHelp2(boolean canHelp2)
	{
		this.canHelp2=canHelp2;
		help2Button.setDisable(!canHelp2);
	}

	@Override
	protected void setAllStyle()
	{
		setStyle("-fx-background-color: #FFC2C2; -fx-border-color: #FF4343;-fx-border-width: 4px;");
		levelLabel.setStyle("-fx-font-size: 32px; -fx-font-family:\"Arial Black\";-fx-background-color: #FF4343;");
	}
}
