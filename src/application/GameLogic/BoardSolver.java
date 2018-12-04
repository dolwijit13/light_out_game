package application.GameLogic;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class BoardSolver
{
	private InputStream inversedMFile;
	private Board board;
	private Light[][] inversedM;
	private int n;
	private ArrayList<Integer> shouldPress;
	private ArrayList<Light> boardVector;
	private int maxState;

	public BoardSolver(Board board) throws FileNotFoundException
	{
		inversedMFile = BoardSolver.class.getClassLoader().getResourceAsStream("BoardSolver_inversedM.txt");
		this.board = board;
		this.maxState = board.getMaxState();
		n = board.getN();
		read_inversedM();
		shouldPress = new ArrayList<Integer>();
		boardVector = new ArrayList<Light>();
		for (int i = 0; i < n; i++)
		{
			for (int j = 0; j < n; j++)
			{
				boardVector.add(board.getLight(i, j));
			}
		}
		find_ShouldPress();
		//printInversedM();
	}

	private void read_inversedM()
	{
		inversedM = new Light[n * n][n * n];

		try
		{
			int cnt = 0;
			while ((inversedMFile.read() - '0') != n)
			{
				cnt++;
			}
			for (int i = 0; i < n * n; i++)
			{
				for (int j = 0; j < n * n; j++)
				{
					int tmp = inversedMFile.read();
					while (!('0' <= tmp && tmp <= '9'))
					{
						tmp = inversedMFile.read();
					}
					tmp -= '0';
					inversedM[i][j] = new Light(tmp, 2);
				}
			}
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void find_ShouldPress()
	{
		for (int i = 0; i < n * n; i++)
		{
			Light ans = new Light(0,2);
			for (int j = 0; j < n * n; j++)
			{
				ans = ans.plus(inversedM[i][j].multiply(boardVector.get(j)));
			}
			if (ans.getCurrentState() !=0)
				shouldPress.add(i);
		}
	}

	public void printInversedM()
	{
		for (int i = 0; i < n * n; i++)
		{
			for (int j = 0; j < n * n; j++)
			{
				if(j%n==0 && j!=0)
				{
					System.out.print("\t");
				}
				System.out.print(inversedM[i][j].getCurrentState() + " ");
			}
			System.out.println();
		}
	}

	public void showShouldPress()
	{
		/*
		Random rand = new Random();
		int sz = shouldPress.size();
		int idx = shouldPress.get(rand.nextInt(sz));
		int i = idx / n, j = idx % n;
		Light light = board.getLight(i, j);
		String style = light.getStyle();
		light.setMinSize();
		light.setStyle(style + "-fx-border-color: #ff0000; -fx-border-width: 3px;");
		*/
		//*
		for(int i=0 ; i<shouldPress.size();i++)
		{
			int idx=shouldPress.get(i);
			Light light = board.getLight(idx/n, idx%n);
			String style = light.getStyle();
			light.setMinSize();
			light.setStyle(style + "-fx-border-color: #4f00ff; -fx-border-width: 3px;");
		}
		
		//*/
	}
}
