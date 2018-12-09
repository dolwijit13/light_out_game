package application.LevelSelection;

import application.Button.BackButton;
import application.Mode.ModeSelection;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public abstract class LevelSelection extends VBox {
	protected class LevelButton extends Button {
		private int level;

		public LevelButton(int level) {
			super("" + level);
			this.level = level;
			setPrefHeight(120);
			setPrefWidth(240);
			setLevelButton(this);
			setButtonStyle(this, 0);

			setOnMouseEntered(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent t) {
					setButtonStyle((Button) t.getSource(), 1);
				}
			});

			setOnMouseExited(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent t) {
					setButtonStyle((Button) t.getSource(), 0);
				}
			});
		}

		public int getLevel() {
			return level;
		}
	}

	public LevelSelection(int n, int m, int maxLevelCanPlay) {
		super(10);
		setAlignment(Pos.TOP_CENTER);
		Label selectLabel = new Label("SELECT LEVEL");
		setLabelStyle(selectLabel);
		selectLabel.setPrefHeight(120);
		selectLabel.setPrefWidth(1280);
		selectLabel.setAlignment(Pos.CENTER);
		selectLabel.setTextFill(Color.web("#000000"));
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(15);
		grid.setHgap(15);

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				int curLevel = (i * m + j + 1);
				LevelButton levelButton = new LevelButton(curLevel);
				grid.add(levelButton, j, i);
				if (curLevel > maxLevelCanPlay) {
					levelButton.setDisable(true);
				}
			}
		}

		BackButton backButton = new BackButton(100, 120, new ModeSelection());
		getChildren().addAll(selectLabel, grid, backButton);
	}

	protected abstract void setLevelButton(LevelButton levelButton);

	protected abstract void setLabelStyle(Label selectLabel);

	protected abstract void setButtonStyle(Button levelButton, int mode);

}
