package application.Mode;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import application.Main;
import application.Button.GameMenuButton;
import application.GameLogic.Board;
import application.GameLogic.BoardSolver;
import application.GameMenu.ClassicGameMenu;
import application.PassLevel.ClassicPassLevel;
import application.PlayerData.PlayerInfo;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class ClassicMode extends Mode {

	private static InputStream levelFile = ClassicMode.class.getClassLoader().getResourceAsStream("ClassicLevel.txt");
	private static String[] levels;
	private int level;

	public ClassicMode(int level) {
		mode = 0;
		this.level = level;
		
		int n = 4 + (level-1) / 5;
		board = new Board(n, 2, level, 0);
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				board.getLight(i, j).setOnMouseClicked(mouseClick);
			}
		}
		String[] start = levels[level - 1].split(" ");
		for (int i = 0; i < start.length; i++) {
			int temp = Integer.parseInt(start[i]);
			board.changeColor(temp / n, temp % n, true);
		}
		gameMenu = new ClassicGameMenu(level);
		setResetButton(gameMenu.getResetButton());
		setUndoButton(gameMenu.getUndoButton());
		setHelp1Button(((ClassicGameMenu) gameMenu).getHelp1Button());
		setHelp2Button(((ClassicGameMenu) gameMenu).getHelp2Button());
		setHelp3Button(((ClassicGameMenu) gameMenu).getHelp3Button());
		if (level < 6) {
			((ClassicGameMenu) gameMenu).setHelp3Disable();
		}

		modeHBox.getChildren().addAll(board, gameMenu);
	}

	public static void readLevel() {
		try {
			String levelString = "";
			char c = '1';
			while (c != '*') {
				c = (char) levelFile.read();
				if (c == '*')
					break;
				levelString += c;
			}
			levels = levelString.split("-");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void toNextLevel() {
		ClassicMode nextLevel = new ClassicMode(board.getCurLevel() + 1);
		Main.changeScene(nextLevel);
	}

	private void setResetButton(Button resetButton) {
		resetButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				GameMenuButton.playSoundEffect();
				resetBoard();
			}
		});
	}

	private void setHelp1Button(Button help1Button) {
		help1Button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				gameMenu.addPenalty(250);
				int n = board.getN();
				for (int i = 0; i < n; i++) {
					for (int j = 0; j < n; j++) {
						if (board.getLight(i, j).getCurrentState() != 0) {
							board.getLight(i, j).changeColor();
						}
					}
				}
				setPenalty();
				showPassLevel();
			}
		});
	}

	private void setHelp2Button(Button help2Button) {
		help2Button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				gameMenu.addPenalty(50);
				BoardSolver boardSolver;
				board.setCanHelp2(false);
				help2Button.setDisable(true);
				try {
					boardSolver = new BoardSolver(board);
					boardSolver.showShouldPress();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void setHelp3Button(Button help3Button) {
		help3Button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				gameMenu.addPenalty(15);
				showHelp3(2, 4 + (level - 1) / 5);
			}
		});
	}

	@Override
	protected void resetBoard() {
		Main.changeScene(new ClassicMode(board.getCurLevel()));
	}

	@Override
	protected void setPenalty() {
		PlayerInfo.setClassicPassedLevel(level);
		PlayerInfo.setClassicPenalty(level, gameMenu.getPenalty());
	}

	@Override
	public void showPassLevel() {
		ClassicPassLevel passLevel = new ClassicPassLevel(board.getCurLevel(), gameMenu.getPenalty());
		setToNextLevelButton(passLevel.getToNextLevelButton());
		setRestartButton(passLevel.getRestartButton());
		Main.playSoundEffect("congrats.mp3");
		disableBoard();
		passLevel.setPenalty(gameMenu.getPenalty());
		modeHBox.getChildren().remove(gameMenu);
		modeHBox.getChildren().add(passLevel);
	}
}
