package application;

import java.util.prefs.Preferences;

public class PlayerInfo
{
	private static Preferences prefs;
	protected static PlayerInfo selectedPlayerInfo;

	protected int n;
	protected String name;

	protected int classicLastPassedLevel; //// number of passed level in classic mode
	protected byte[] classicPenalty; // penalty of each classic mode level (1-20)

	protected int timerPassedLevel; // number of passed level in timer mode
	protected int timerPenalty; // penalty of timer mode

	protected int drawPassedLevel; // number of passed level in draw mode
	protected byte[] drawPenalty; // penalty of each draw mode level (1-??)

	protected int triColorPassedLevel; // number of passed level in tricolor mode
	protected byte[] triColorPenalty; // penalty of each tricolor mode level (1-??)

	public PlayerInfo(int n, String name, int classicLastPassedLevel, byte[] classicPenalty,
			int timerPassedLevel, int timerPenalty, int drawPassedLevel, byte[] drawPenalty,
			int triColorPassedLevel, byte[] triColorPenalty)
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
		this.classicLastPassedLevel = prefs.getInt(n+"classicLastPassedLevel", 0);
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
		prefs.putInt(n+"classicLastPassedLevel", selectedPlayerInfo.classicLastPassedLevel);
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

	public static void setClassicLastPassedLevel(int classicLastPassedLevel)
	{
		selectedPlayerInfo.classicLastPassedLevel = classicLastPassedLevel;
	}

	public static void setClassicPenalty(int level,int newPenalty)
	{
		int oldPenalty = selectedPlayerInfo.classicPenalty[(level-1)*2]*256+selectedPlayerInfo.classicPenalty[(level-1)*2+1];
		if(oldPenalty > newPenalty)
		{
			byte a = (byte) (newPenalty/256);
			byte b = (byte) (newPenalty%256);
			selectedPlayerInfo.classicPenalty[(level-1)*2]=a;
			selectedPlayerInfo.classicPenalty[(level-1)*2+1]=b;
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
		int oldPenalty = selectedPlayerInfo.drawPenalty[(level-1)*2]*256+selectedPlayerInfo.drawPenalty[(level-1)*2+1];
		if(oldPenalty > newPenalty)
		{
			byte a = (byte) (newPenalty/256);
			byte b = (byte) (newPenalty%256);
			selectedPlayerInfo.drawPenalty[(level-1)*2]=a;
			selectedPlayerInfo.drawPenalty[(level-1)*2+1]=b;
		}
	}

	public static void setTriColorPassedLevel(int triColorPassedLevel)
	{
		selectedPlayerInfo.triColorPassedLevel = triColorPassedLevel;
	}

	public static void setTriColorPenalty(int level,int newPenalty)
	{
		int oldPenalty = selectedPlayerInfo.triColorPenalty[(level-1)*2]*256+selectedPlayerInfo.triColorPenalty[(level-1)*2+1];
		if(oldPenalty > newPenalty)
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

	public String getName()
	{
		return selectedPlayerInfo.name;
	}

	public static int getClassicLastPassedLevel()
	{
		return selectedPlayerInfo.classicLastPassedLevel;
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
	
}
