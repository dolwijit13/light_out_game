package application.PlayerData;

import java.util.ArrayList;

import application.Main;
import application.StartMenu;
import application.Button.BackButton;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class LeaderBoard extends VBox
{
	private class ModeButton extends Button
	{
		public ModeButton(int mode)
		{
			super();
			if (mode == 0)
			{
				this.setText("Classic");
			}
			else if (mode == 1)
			{
				this.setText("Timer");
			}
			else if (mode == 2)
			{
				this.setText("Draw");
			}
			else if (mode == 3)
			{
				this.setText("TriColor");
			}

			setPrefHeight(70);
			setPrefWidth(320);
			setColor(this,mode);
		}
	}

	private class PlayerLabel extends HBox
	{
		protected int mode;
		protected int cleared;
		protected int penalty;
		public PlayerLabel(int n,int mode)
		{
			super(50);
			this.mode=mode;

			PlayerInfo playerInfo = new PlayerInfo(n);
			setPadding(new Insets(5, 5, 5, 50));
			setPrefHeight(127 - 3);
			setPrefWidth(1280 - 6);
			setAlignment(Pos.CENTER_LEFT);
			
			if (playerInfo.name.length() < 3)
			{
				cleared = -1;
				penalty = 999999;
				return;
			}
			setStyle("-fx-border-color: #828282; -fx-border-width: 3px;");
			
			if(mode==0)
			{
				cleared = playerInfo.classicPassedLevel;
				penalty = playerInfo.getAllClassicPenalty();
			}
			else if(mode==1)
			{
				cleared = playerInfo.timerPassedLevel;
				penalty = playerInfo.timerPenalty;
			}
			else if(mode==2)
			{
				cleared = playerInfo.drawPassedLevel;
				penalty = playerInfo.getAllDrawPenalty();
			}
			else if(mode==3)
			{
				cleared = playerInfo.triColorPassedLevel;
				penalty = playerInfo.getAllTriColorPenalty();
			}
			

			String name = playerInfo.name;
			String clearedString = "Cleared : " + cleared;
			String penaltyString = "Penalty : " + penalty;

			Label nLabel = new Label(name);
			nLabel.setStyle("-fx-font-size: 48px; -fx-font-family:\"Arial Black\"; ");
			nLabel.setPrefWidth(382);
			
			Label clearedLabel = new Label(clearedString);
			clearedLabel.setStyle("-fx-font-size: 36px; -fx-font-family:\"Arial Black\"; ");
			clearedLabel.setPrefWidth(240);
			
			Label penaltyLabel = new Label(penaltyString);
			penaltyLabel.setStyle("-fx-font-size: 36px; -fx-font-family:\"Arial Black\"; ");

			this.getChildren().addAll(nLabel, clearedLabel,penaltyLabel);
		}
		
		public boolean isLessThan(PlayerLabel o)
		{
			if(this.mode != o.mode)
				return false;
			if(this.cleared != o.cleared)
			{
				return this.cleared < o.cleared;
			}
			return this.penalty > o.penalty;
		}
	}
	
	private int mode;

	public LeaderBoard(int mode)
	{
		super(0);
		this.mode = mode;
		setPrefHeight(720);
		setPrefWidth(1280);
		setAlignment(Pos.TOP_RIGHT);

		Label leaderBoardLebel = new Label("LEADER BOARD");
		leaderBoardLebel.setPrefHeight(82);
		leaderBoardLebel.setPrefWidth(1280);
		leaderBoardLebel.setAlignment(Pos.CENTER);
		leaderBoardLebel.setTextFill(Color.web("#FFFFFF"));
		leaderBoardLebel.setStyle(
				"-fx-font-size: 48px; -fx-font-family:\"Arial Black\"; -fx-background-color: #2A3132; -fx-background-radius: 0;");

		ModeButton classicButton = new ModeButton(0);
		ModeButton timerButton = new ModeButton(1);
		ModeButton drawButton = new ModeButton(2);
		ModeButton triColorButton = new ModeButton(3);
		HBox modeButtonHBox = new HBox(0);
		modeButtonHBox.getChildren().addAll(classicButton, timerButton, drawButton, triColorButton);

		VBox playerLabelVBox = new VBox(3);
		playerLabelVBox.setPadding(new Insets(3, 3, 0, 3));
		ArrayList<PlayerLabel>playerLabels = new ArrayList<PlayerLabel>();
		for (int i = 0; i < 4; i++)
		{
			playerLabels.add(new PlayerLabel(i, mode));
		}
		for(int i=0;i<4;i++)
		{
			for(int j=0;j<4-1;j++)
			{
				PlayerLabel tmp1 = playerLabels.get(j);
				PlayerLabel tmp2 = playerLabels.get(j+1);
				if(tmp1.isLessThan(tmp2))
				{
					playerLabels.set(j, tmp2);
					playerLabels.set(j+1, tmp1);
				}
			}
		}
		
		for(int i=0;i<4;i++)
		{
			playerLabelVBox.getChildren().add(playerLabels.get(i));
		}

		//ToStartMenuButton toStartMenuButton = new ToStartMenuButton();
		//toStartMenuButton.setPrefHeight(55);
		//toStartMenuButton.setAlignment(Pos.CENTER_RIGHT);
		BackButton toStartMenuButton = new BackButton(100, 120, new StartMenu());
		this.getChildren().addAll(leaderBoardLebel, modeButtonHBox, playerLabelVBox, toStartMenuButton);
	
		classicButton.setOnMouseClicked(new EventHandler<MouseEvent>()
		{
			@Override
			public void handle(MouseEvent event)
			{
				LeaderBoard leaderBoard = new LeaderBoard(0);
				Main.changeScene(leaderBoard);
			}
		});
		
		timerButton.setOnMouseClicked(new EventHandler<MouseEvent>()
		{
			@Override
			public void handle(MouseEvent event)
			{
				LeaderBoard leaderBoard = new LeaderBoard(1);
				Main.changeScene(leaderBoard);
			}
		});
		
		drawButton.setOnMouseClicked(new EventHandler<MouseEvent>()
		{
			@Override
			public void handle(MouseEvent event)
			{
				LeaderBoard leaderBoard = new LeaderBoard(2);
				Main.changeScene(leaderBoard);
			}
		});
		
		triColorButton.setOnMouseClicked(new EventHandler<MouseEvent>()
		{
			@Override
			public void handle(MouseEvent event)
			{
				LeaderBoard leaderBoard = new LeaderBoard(3);
				Main.changeScene(leaderBoard);
			}
		});
	}
	
	public void setColor(ModeButton modeButton,int buttonMode)
	{
		//0 are more colorful
		String color0[] = {"#FF4343","#FFD728","#2BCA2F","#3F94FF"};
		String color1[] = {"#FFC2C2","#FFED9F","#99C99A","#95C4FF"};
		
		String oldStyle = "-fx-font-size: 24px; -fx-font-family:\"Arial Black\"; -fx-background-radius: 0; ";
		
		if(this.mode==buttonMode)
		{
			modeButton.setStyle(oldStyle+"-fx-background-color: " + color0[buttonMode]);
			return;
		}
		modeButton.setStyle(oldStyle+"-fx-background-color: " + color1[buttonMode]);
	}
}
