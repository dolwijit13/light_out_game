package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
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

	public PlayerInfo(int n)
	{
		this.n = n;
		URL url = PlayerInfo.class.getClassLoader().getResource("PlayerInfo.txt");
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

	public int getN()
	{
		return n;
	}

	public String getName()
	{
		return name;
	}

	public int getClassicLastPassedLevel()
	{
		return classicLastPassedLevel;
	}

	public ArrayList<Integer> getClassicPenalty()
	{
		return classicPenalty;
	}

	public int getTimerPassedLevel()
	{
		return timerPassedLevel;
	}

	public int getTimerPenalty()
	{
		return timerPenalty;
	}

	public int getDrawPassedLevel()
	{
		return drawPassedLevel;
	}

	public ArrayList<Integer> getDrawPenalty()
	{
		return drawPenalty;
	}

	public int getTriColorPassedLevel()
	{
		return triColorPassedLevel;
	}

	public ArrayList<Integer> getTriColorPenalty()
	{
		return triColorPenalty;
	}
	
	
}
