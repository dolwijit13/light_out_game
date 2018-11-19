package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
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
	
	public PlayerInfo(String name)
	{
		this.n = 0;
		this.name = name;
		this.classicLastPassedLevel = 0;
		this.classicPenalty = new ArrayList<Integer>();
		this.timerPassedLevel = 0;
		this.timerPenalty = 0;
		this.drawPassedLevel = 0;
		this.drawPenalty = new ArrayList<Integer>();
		this.triColorPassedLevel = 0;
		this.triColorPenalty = new ArrayList<Integer>();
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
	
	public static void saveToN(int n)
	{
		InputStream playerFile = PlayerInfo.class.getClassLoader().getResourceAsStream("PlayerInfo.txt");
		try
		{
			BufferedReader read = new BufferedReader(new InputStreamReader(playerFile));
			ArrayList<String>write = new ArrayList<String>();

			// find n'th player info
			while (true)
			{
				String s;
				s = read.readLine();
				write.add(s);
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
			
			write.add(selectedPlayerInfo.name);
			read.readLine();
			
			write.add(""+selectedPlayerInfo.classicLastPassedLevel);
			read.readLine();
			String tmp="";
			for (int i = 0; i < selectedPlayerInfo.classicLastPassedLevel; i++)
			{
				tmp+=""+selectedPlayerInfo.classicPenalty.get(i)+' ';
			}
			write.add(tmp);
			read.readLine();
			
			write.add(""+selectedPlayerInfo.timerPassedLevel);
			read.readLine();
			write.add(""+selectedPlayerInfo.timerPenalty);
			read.readLine();

			write.add(""+selectedPlayerInfo.drawPassedLevel);
			read.readLine();
			tmp="";
			for (int i = 0; i < selectedPlayerInfo.drawPassedLevel; i++)
			{
				tmp+=""+selectedPlayerInfo.drawPenalty.get(i)+' ';
			}
			write.add(tmp);
			read.readLine();
			
			write.add(""+selectedPlayerInfo.triColorPassedLevel);
			read.readLine();
			tmp="";
			for (int i = 0; i < selectedPlayerInfo.triColorPassedLevel; i++)
			{
				tmp+=""+selectedPlayerInfo.drawPenalty.get(i)+' ';
			}
			write.add(tmp);
			read.readLine();
			
			while (read.ready())
			{
				String s;
				s = read.readLine();
				write.add(s);
			}
			
			URL url = PlayerInfo.class.getClassLoader().getResource("PlayerInfo.txt");
			BufferedWriter b_writer;
			try
			{
				b_writer = new BufferedWriter (new FileWriter (new File (url.toURI())));
				for(int i=0;i<write.size();i++)
				{
					b_writer.write(write.get(i));
					b_writer.write("\r\n");
				}
				b_writer.close();
			}
			catch (URISyntaxException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void setN(int n)
	{
		selectedPlayerInfo.n = n;
	}

	public static void setName(String name)
	{
		selectedPlayerInfo.name = name;
	}

	public static void setClassicLastPassedLevel(int classicLastPassedLevel)
	{
		selectedPlayerInfo.classicLastPassedLevel = classicLastPassedLevel;
	}

	public static void setClassicPenalty(int level,int newPenalty)
	{
		if(selectedPlayerInfo.classicPenalty.size() < level)
		{
			selectedPlayerInfo.classicPenalty.add(newPenalty);
		}
		else if(selectedPlayerInfo.classicPenalty.get(level-1) > newPenalty)
		{
			selectedPlayerInfo.classicPenalty.set(level-1, newPenalty);
		}
	}

	public static void setTimerPassedLevel(int timerPassedLevel)
	{
		selectedPlayerInfo.timerPassedLevel = timerPassedLevel;
	}

	public static void setTimerPenalty(int timerPenalty)
	{
		selectedPlayerInfo.timerPenalty = timerPenalty;
	}

	public static void setDrawPassedLevel(int drawPassedLevel)
	{
		selectedPlayerInfo.drawPassedLevel = drawPassedLevel;
	}

	public static void setDrawPenalty(int level,int newPenalty)
	{
		if(selectedPlayerInfo.drawPenalty.size() < level)
		{
			selectedPlayerInfo.drawPenalty.add(newPenalty);
		}
		else if(selectedPlayerInfo.drawPenalty.get(level-1) > newPenalty)
		{
			selectedPlayerInfo.drawPenalty.set(level-1, newPenalty);
		}
	}

	public static void setTriColorPassedLevel(int triColorPassedLevel)
	{
		selectedPlayerInfo.triColorPassedLevel = triColorPassedLevel;
	}

	public static void setTriColorPenalty(int level,int newPenalty)
	{
		if(selectedPlayerInfo.triColorPenalty.size() < level)
		{
			selectedPlayerInfo.triColorPenalty.add(newPenalty);
		}
		else if(selectedPlayerInfo.triColorPenalty.get(level-1) > newPenalty)
		{
			selectedPlayerInfo.triColorPenalty.set(level-1, newPenalty);
		}
	}

	public static int getN()
	{
		return selectedPlayerInfo.n;
	}

	public String getName()
	{
		return selectedPlayerInfo.name;
	}

	public static int getClassicLastPassedLevel()
	{
		return selectedPlayerInfo.classicLastPassedLevel;
	}

	public static ArrayList<Integer> getClassicPenalty()
	{
		return selectedPlayerInfo.classicPenalty;
	}

	public static int getTimerPassedLevel()
	{
		return selectedPlayerInfo.timerPassedLevel;
	}

	public static int getTimerPenalty()
	{
		return selectedPlayerInfo.timerPenalty;
	}

	public static int getDrawPassedLevel()
	{
		return selectedPlayerInfo.drawPassedLevel;
	}

	public static ArrayList<Integer> getDrawPenalty()
	{
		return selectedPlayerInfo.drawPenalty;
	}

	public static int getTriColorPassedLevel()
	{
		return selectedPlayerInfo.triColorPassedLevel;
	}

	public static ArrayList<Integer> getTriColorPenalty()
	{
		return selectedPlayerInfo.triColorPenalty;
	}
	
}
