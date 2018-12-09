package application.PlayerData;

import java.util.prefs.Preferences;

public class PlayerInfo
{
	private static Preferences prefs;
	protected static PlayerInfo selectedPlayerInfo;

	protected int n;
	protected String name;

	protected int classicPassedLevel; //// number of passed level in classic mode
	protected byte[] classicPenalty; // penalty of each classic mode level (1-20)

	protected int timerPassedLevel; // number of passed level in timer mode
	protected int timerPenalty; // penalty of timer mode

	protected int drawPassedLevel; // number of passed level in draw mode
	protected byte[] drawPenalty; // penalty of each draw mode level (1-??)

	protected int triColorPassedLevel; // number of passed level in tricolor mode
	protected byte[] triColorPenalty; // penalty of each tricolor mode level (1-??)
	
	public PlayerInfo(String name) throws BadNameException
	{
		if(name.length()<3 || name.length()>8) {
			throw new BadNameException("A name must be in length 3 to 8");
		}
		for(int i=0;i<name.length();i++)
		{
			char c=name.charAt(i);
			if(!(('a'<=c && c<='z') || ('A'<=c && c<='Z') || ('0'<=c && c<='9')))
			{
				throw new BadNameException("A name must contain only A-Z, a-z, 0-9");
			}
		}
		this.n = 0;
		this.name = name;
		this.classicPassedLevel = 0;
		this.classicPenalty = new byte[41];
		this.timerPassedLevel = 0;
		this.timerPenalty = 0;
		this.drawPassedLevel = 0;
		this.drawPenalty = new byte[41];
		this.triColorPassedLevel = 0;
		this.triColorPenalty = new byte[41];
		prefs = Preferences.userNodeForPackage(this.getClass());
	}

	public PlayerInfo(int n)
	{
		prefs=Preferences.userRoot().node(this.getClass().getName());
		
		this.n = n;
		this.name = prefs.get(n+"name", " ");
		this.classicPassedLevel = prefs.getInt(n+"classicLastPassedLevel", 0);
		this.classicPenalty = prefs.getByteArray(n+"classicPenalty", null);
		this.timerPassedLevel = prefs.getInt(n+"timerPassedLevel", 0);
		this.timerPenalty = prefs.getInt(n+"timerPenalty", 0);
		this.drawPassedLevel = prefs.getInt(n+"drawPassedLevel", 0);
		this.drawPenalty = prefs.getByteArray(n+"drawPenalty", null);
		this.triColorPassedLevel = prefs.getInt(n+"triColorPassedLevel", 0);
		this.triColorPenalty = prefs.getByteArray(n+"triColorPenalty", null);
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
		prefs.put(n+"name",selectedPlayerInfo.name);
		prefs.putInt(n+"classicLastPassedLevel", selectedPlayerInfo.classicPassedLevel);
		prefs.putByteArray(n+"classicPenalty", selectedPlayerInfo.classicPenalty);
		prefs.putInt(n+"timerPassedLevel", selectedPlayerInfo.timerPassedLevel);
		prefs.putInt(n+"timerPenalty", selectedPlayerInfo.timerPenalty);
		prefs.putInt(n+"drawPassedLevel", selectedPlayerInfo.drawPassedLevel);
		prefs.putByteArray(n+"drawPenalty", selectedPlayerInfo.drawPenalty);
		prefs.putInt(n+"triColorPassedLevel", selectedPlayerInfo.triColorPassedLevel);
		prefs.putByteArray(n+"triColorPenalty", selectedPlayerInfo.triColorPenalty);
	}

	public static void setN(int n)
	{
		selectedPlayerInfo.n = n;
	}

	public static void setName(String name)
	{
		selectedPlayerInfo.name = name;
	}

	public static void setClassicPassedLevel(int newLevel)
	{
		int lastLevel=getClassicPassedLevel();
		if(newLevel > lastLevel)
		{
			selectedPlayerInfo.classicPassedLevel = newLevel;
		}
	}

	public static void setClassicPenalty(int level,int newPenalty)
	{
		int oldPenalty = selectedPlayerInfo.classicPenalty[(level-1)*2]*256+selectedPlayerInfo.classicPenalty[(level-1)*2+1];
		if(oldPenalty > newPenalty || oldPenalty==0)
		{
			byte a = (byte) (newPenalty/256);
			byte b = (byte) (newPenalty%256);
			selectedPlayerInfo.classicPenalty[(level-1)*2]=a;
			selectedPlayerInfo.classicPenalty[(level-1)*2+1]=b;
		}
	}

	public static void setTimerPassedLevel(int newLevel)
	{
		int lastLevel=getTimerPassedLevel();
		if(newLevel > lastLevel)
		{
			selectedPlayerInfo.timerPassedLevel = newLevel;
		}
	}

	public static void setTimerPenalty(int newPenalty)
	{
		int lastPenalty=getTimerPenalty();
		if(newPenalty > lastPenalty)
		{
			selectedPlayerInfo.timerPenalty = newPenalty;
		}
	}

	public static void setDrawPassedLevel(int drawPassedLevel)
	{
		selectedPlayerInfo.drawPassedLevel = drawPassedLevel;
	}

	public static void setDrawPenalty(int level,int newPenalty)
	{
		int oldPenalty = selectedPlayerInfo.drawPenalty[(level-1)*2]*256+selectedPlayerInfo.drawPenalty[(level-1)*2+1];
		if(oldPenalty > newPenalty || oldPenalty==0)
		{
			byte a = (byte) (newPenalty/256);
			byte b = (byte) (newPenalty%256);
			selectedPlayerInfo.drawPenalty[(level-1)*2]=a;
			selectedPlayerInfo.drawPenalty[(level-1)*2+1]=b;
		}
	}

	public static void setTriColorPassedLevel(int newLevel)
	{
		int lastLevel=getTriColorPassedLevel();
		if(newLevel > lastLevel)
		{
			selectedPlayerInfo.triColorPassedLevel = newLevel;
		}
	}

	public static void setTriColorPenalty(int level,int newPenalty)
	{
		int oldPenalty = selectedPlayerInfo.triColorPenalty[(level-1)*2]*256+selectedPlayerInfo.triColorPenalty[(level-1)*2+1];
		if(oldPenalty > newPenalty || oldPenalty==0)
		{
			byte a = (byte) (newPenalty/256);
			byte b = (byte) (newPenalty%256);
			selectedPlayerInfo.triColorPenalty[(level-1)*2]=a;
			selectedPlayerInfo.triColorPenalty[(level-1)*2+1]=b;
		}
	}

	public static int getN()
	{
		return selectedPlayerInfo.n;
	}

	public static String getName()
	{
		return selectedPlayerInfo.name;
	}

	public static int getClassicPassedLevel()
	{
		return selectedPlayerInfo.classicPassedLevel;
	}

	public static byte[] getClassicPenalty()
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

	public static byte[] getDrawPenalty()
	{
		return selectedPlayerInfo.drawPenalty;
	}

	public static int getTriColorPassedLevel()
	{
		return selectedPlayerInfo.triColorPassedLevel;
	}

	public static byte[] getTriColorPenalty()
	{
		return selectedPlayerInfo.triColorPenalty;
	}
	
	public int getAllClassicPenalty()
	{
		int ans=0;
		for(int i=0;i<40;i++)
		{
			if(i%2==0)
			{
				ans+= classicPenalty[i]*256;
			}
			else
			{
				ans+= classicPenalty[i];
			}
		}
		return ans;
	}
	
	public int getAllDrawPenalty()
	{
		int ans=0;
		for(int i=0;i<20;i++)
		{
			if(i%2==0)
			{
				ans+= drawPenalty[i]*256;
			}
			else
			{
				ans+= drawPenalty[i];
			}
		}
		return ans;
	}
	
	public int getAllTriColorPenalty()
	{
		int ans=0;
		for(int i=0;i<20;i++)
		{
			if(i%2==0)
			{
				ans+= triColorPenalty[i]*256;
			}
			else
			{
				ans+= triColorPenalty[i];
			}
		}
		return ans;
	}
	
}
