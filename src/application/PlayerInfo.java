package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class PlayerInfo
{
	protected static PlayerInfo selectedPlayerInfo;

	protected int n;
	protected String name;

	protected int classicLastPassedLevel; //// number of passed level in classic mode
	protected ArrayList<Integer> classicPenalty; // penalty of each classic mode level (1-20)

	protected int timerPassedLevel; // number of passed level in timer mode
	protected int timerPenalty; // penalty of timer mode

	protected int drawPassedLevel; // number of passed level in draw mode
	protected ArrayList<Integer> drawPenalty; // penalty of each draw mode level (1-??)

	protected int triColorPassedLevel; // number of passed level in tricolor mode
	protected ArrayList<Integer> triColorPenalty; // penalty of each tricolor mode level (1-??)

	public PlayerInfo(int n, String name, int classicLastPassedLevel, ArrayList<Integer> classicPenalty,
			int timerPassedLevel, int timerPenalty, int drawPassedLevel, ArrayList<Integer> drawPenalty,
			int triColorPassedLevel, ArrayList<Integer> triColorPenalty)
	{
		this.n = n;
		this.name = name;
		this.classicLastPassedLevel = classicLastPassedLevel;
		this.classicPenalty = classicPenalty;
		this.timerPassedLevel = timerPassedLevel;
		this.timerPenalty = timerPenalty;
		this.drawPassedLevel = drawPassedLevel;
		this.drawPenalty = drawPenalty;
		this.triColorPassedLevel = triColorPassedLevel;
		this.triColorPenalty = triColorPenalty;
	}

	public PlayerInfo(int n)
	{
		this.n = n;
		InputStream playerFile = PlayerInfo.class.getClassLoader().getResourceAsStream("PlayerInfo.txt");
		try
		{
			BufferedReader read = new BufferedReader(new InputStreamReader(playerFile));
			String s;

			// find n'th player info
			while (true)
			{
				s = read.readLine();
				if (s.length() == 0)
					continue;
				if (s.charAt(0) == '*')
				{
					if (s.charAt(1) - '0' == n)
					{
						break;
					}
				}
			}

			String s2[];

			this.name = read.readLine();

			this.classicLastPassedLevel = Integer.parseInt(read.readLine());
			this.classicPenalty = new ArrayList<Integer>();
			s2 = read.readLine().split(" ");
			for (int i = 0; i < classicLastPassedLevel; i++)
			{
				this.classicPenalty.add(Integer.parseInt(s2[i]));
			}

			this.timerPassedLevel = Integer.parseInt(read.readLine());
			this.timerPenalty = Integer.parseInt(read.readLine());

			this.drawPassedLevel = Integer.parseInt(read.readLine());
			this.drawPenalty = new ArrayList<Integer>();
			s2 = read.readLine().split(" ");
			for (int i = 0; i < drawPassedLevel; i++)
			{
				this.drawPenalty.add(Integer.parseInt(s2[i]));
			}

			this.triColorPassedLevel = Integer.parseInt(read.readLine());
			this.triColorPenalty = new ArrayList<Integer>();
			s2 = read.readLine().split(" ");
			for (int i = 0; i < triColorPassedLevel; i++)
			{
				this.triColorPenalty.add(Integer.parseInt(s2[i]));
			}
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void setSelectedPlayerInfo(PlayerInfo o)
	{
		selectedPlayerInfo = o;
	}

	public static PlayerInfo getSelectedPlayerInfo()
	{
		return selectedPlayerInfo;
	}
}
