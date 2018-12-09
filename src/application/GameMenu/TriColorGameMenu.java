package application.GameMenu;

import application.Button.BackButton;
import application.LevelSelection.TriColorLevelSelection;
import javafx.geometry.Pos;
import javafx.scene.control.Tooltip;

public class TriColorGameMenu extends ClassicGameMenu {
	public TriColorGameMenu(int level) {
		super(level);
	}

	@Override
	protected void addBackButton() {
		backButton = new BackButton(100, 120, new TriColorLevelSelection());
		backButton.setTooltip(new Tooltip("Back to Level Selection\n(Unsaved progress will be lost)"));
		backHBox.setAlignment(Pos.CENTER_RIGHT);
		backHBox.getChildren().add(backButton);
	}

	@Override
	protected void setAllStyle() {
		setStyle("-fx-background-color: #95C4FF; -fx-border-color: #3F94FF;-fx-border-width: 4px;");
		levelLabel.setStyle("-fx-font-size: 32px; -fx-font-family:\"Arial Black\";-fx-background-color: #3F94FF;");
	}
}
