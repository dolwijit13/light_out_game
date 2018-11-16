package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javafx.scene.control.Button;

public class BoardSolver {
	private InputStream inversedMFile;
	private Board board;
	private Light[][] inversedM;
	private int n;
	private ArrayList<Integer> shouldPress;
	private ArrayList<Light> boardVector;

	public BoardSolver(Board board) throws FileNotFoundException {
		inversedMFile = BoardSolver.class.getClassLoader().getResourceAsStream("BoardSolver_inversedM.txt");
		this.board = board;
		n = board.getN();
		read_inversedM();
		shouldPress = new ArrayList<Integer>();
		boardVector = new ArrayList<Light>();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				boardVector.add(board.getLight(i, j));
			}
		}
		find_ShouldPress();
	}

	private void read_inversedM() {
		inversedM = new Light[n * n][n * n];
		// Scanner sc = new Scanner(new File(inversedFilePath));

		try 
		{
			while ((inversedMFile.read() - '0') != n) 
			{
				inversedM[0][0] = new Light();
			}
			for (int i = 0; i < n*n; i++) 
			{
				for (int j = 0; j < n*n; j++) 
				{
					int tmp = inversedMFile.read();
					while(!('0'<=tmp && tmp<='9'))
					{
						tmp=inversedMFile.read();
					}
					tmp-='0';
					inversedM[i][j] = new Light(tmp, 2);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void find_ShouldPress() {
		for (int i = 0; i < n * n; i++) {
			Light ans = new Light();
			for (int j = 0; j < n * n; j++) {
				ans = ans.plus(inversedM[i][j].multiply(boardVector.get(j)));
			}
			if (ans.getCurrentState() == 1)
				shouldPress.add(i);
		}
	}

	public void printInversedM() {
		for (int i = 0; i < n * n; i++) {
			for (int j = 0; j < n * n; j++) {
				System.out.print(inversedM[i][j].getCurrentState() + " ");
			}
			System.out.println();
		}
	}

	public void showShouldPress() {
		Random rand = new Random();
		int sz = shouldPress.size();
		int idx = shouldPress.get(rand.nextInt(sz));
		int i = idx / n, j = idx % n;
		Button button = board.getLight(i, j);
		String style = button.getStyle();
		button.setStyle(style + "-fx-border-color: #ff0000; -fx-border-width: 3px;");
	}
}
