package application.Mode;

import java.util.Deque;
import java.util.LinkedList;

import application.Main;
import application.Button.OKButton;
import application.GameLogic.Board;
import application.GameLogic.Light;
import application.GameMenu.ClassicGameMenu;
import application.GameMenu.DrawGameMenu;
import application.GameMenu.GameMenu;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public abstract class Mode extends StackPane {
	protected int mode;
	protected Board board;
	protected GameMenu gameMenu;
	protected HBox modeHBox;
	private Deque<Integer> undoDeq;
	protected final EventHandler<MouseEvent> mouseClick = new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent event) {
			Main.playSoundEffect("light.wav");
			MouseButton button = event.getButton();
			int n = board.getN();
			if (button == MouseButton.PRIMARY) {
				Light b = (Light) event.getSource();
				int ID = Integer.parseInt(b.getId());
				int x = ID / n, y = ID % n;
				board.changeColor(x, y, true);
				undoDeq.addLast(ID);
				while (undoDeq.size() > 5)
					undoDeq.removeFirst();
				gameMenu.addPenalty(5);
				if (isWinLevel() && mode != 1) {
					setPenalty();
					showPassLevel();
				} else if (isWinLevel() && mode == 1) {
					timerNextLevel();
				}
				if (gameMenu instanceof ClassicGameMenu) {
					((ClassicGameMenu) gameMenu).getHelp1Button().setDisable(!board.isCanHelp1());
					((ClassicGameMenu) gameMenu).getHelp2Button().setDisable(!board.isCanHelp2());
				}
				if (gameMenu instanceof DrawGameMenu) {
					((DrawGameMenu) gameMenu).getHelp2Button().setDisable(!board.isCanHelp2());
				}
				Button undoButton = gameMenu.getUndoButton();
				undoButton.setDisable(false);
			}
		}
	};

	public Mode() {
		setAlignment(Pos.CENTER);
		modeHBox = new HBox(10);
		modeHBox.setPadding(new Insets(10, 10, 10, 10));
		undoDeq = new LinkedList<Integer>();
		getChildren().addAll(modeHBox);
	}

	protected abstract void showPassLevel();

	protected abstract void toNextLevel();

	protected abstract void resetBoard();

	protected abstract void setPenalty();

	protected void timerNextLevel() {
	}

	protected void setToNextLevelButton(Button toNextLevelButton) {
		toNextLevelButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				toNextLevel();
			}
		});
	}

	protected void setRestartButton(Button restartButton) {
		restartButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				resetBoard();
			}
		});
	}

	protected void setUndoButton(Button undoButton) {
		undoButton.setDisable(true);
		undoButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				undoBoard();
			}
		});
	}

	protected boolean isWinLevel() {
		int n = board.getN();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (board.getLight(i, j).getCurrentState() != 0) {
					return false;
				}
			}
		}
		return true;
	}

	private void undoBoard() {
		if (undoDeq.isEmpty())
			return;
		int lastId = undoDeq.getLast();
		undoDeq.removeLast();
		int n = board.getN();
		board.changeColor(lastId / n, lastId % n, true);
		if (this instanceof TriColorMode) {
			board.changeColor(lastId / n, lastId % n, true);
		}
		gameMenu.addPenalty(-5);
		if (undoDeq.isEmpty()) {
			Button undoButton = gameMenu.getUndoButton();
			undoButton.setDisable(true);
		}
	}

	protected void showHelp3(int maxState, int size) {
		setActive(false);
		VBox help3VBox = new VBox();
		help3VBox.setPrefSize(900, 700);
		help3VBox.setAlignment(Pos.CENTER);
		OKButton okButton = new OKButton(100, 120);
		okButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				OKButton.playSoundEffect();
				getChildren().remove(help3VBox);
				setActive(true);
			}
		});
		ImageView help3 = new ImageView(
				new Image(ClassLoader.getSystemResource("assets/help3/" + maxState + "" + size + ".png").toString()));
		help3VBox.getChildren().addAll(help3, okButton);
		getChildren().add(help3VBox);
	}

	private void setActive(boolean active) {
		gameMenu.setDisable(!active);
		board.setDisable(!active);
	}

	protected void disableBoard() {
		for (int i = 0; i < board.getN(); i++) {
			for (int j = 0; j < board.getN(); j++) {
				board.getLight(i, j).setOnMouseClicked(null);
			}
		}
	}
}
