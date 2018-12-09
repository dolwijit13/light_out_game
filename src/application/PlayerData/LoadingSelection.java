package application.PlayerData;

import application.Main;
import application.MainMenu;
import application.StartMenu;
import application.Button.BackButton;
import application.Button.OKButton;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class LoadingSelection extends VBox
{
	protected class PlayerButton extends VBox
	{
		protected PlayerInfo playerInfo;
		protected boolean isLoading;
		protected int n;
		protected String name;
		protected int classicLastPassedLevel; //// number of passed level in classic mode
		protected int timerPassedLevel; // number of passed level in timer mode
		protected int drawPassedLevel; // number of passed level in draw mode
		protected int triColorPassedLevel; // number of passed level in tricolor mode

		
		public PlayerButton(PlayerInfo playerInfo,Boolean isLoading)
		{
			super();
			
			setPadding(new Insets(0, 20, 0, 20));
			
			this.playerInfo = playerInfo;
			this.isLoading = isLoading;
			this.n = playerInfo.n;
			this.name = playerInfo.name;
			this.classicLastPassedLevel = playerInfo.classicPassedLevel;
			this.timerPassedLevel = playerInfo.timerPassedLevel;
			this.drawPassedLevel = playerInfo.drawPassedLevel;
			this.triColorPassedLevel = playerInfo.triColorPassedLevel;

			Label nameLabel = new Label(name);
			nameLabel.setPrefHeight(44);
			nameLabel.setStyle("-fx-font-size: 32px; -fx-font-family:\"Arial Black\";");
			
			Label classicLabel;
			if(classicLastPassedLevel==0)
			{
				classicLabel = new Label("Classic : -");
			}
			else
			{
				classicLabel = new Label("Classic : " + classicLastPassedLevel);
			}
			classicLabel.setPrefWidth(500);
			classicLabel.setStyle("-fx-font-size: 24px; -fx-font-family:\"Arial Black\";");

			Label timerLabel;
			if(timerPassedLevel==0)
			{
				timerLabel = new Label("Timer : -");
			}
			else
			{
				timerLabel = new Label("Timer : " + timerPassedLevel);
			}
			timerLabel.setPrefWidth(500);
			timerLabel.setStyle("-fx-font-size: 24px; -fx-font-family:\"Arial Black\";");

			Label drawLabel;
			if(drawPassedLevel==0)
			{
				drawLabel = new Label("Draw : -");
			}
			else
			{
				drawLabel = new Label("Draw : " + drawPassedLevel);
			}
			drawLabel.setPrefWidth(500);
			drawLabel.setStyle("-fx-font-size: 24px; -fx-font-family:\"Arial Black\";");

			Label triColorLabel = new Label("TriColor : " + triColorPassedLevel);
			if(triColorPassedLevel==0)
			{
				triColorLabel = new Label("TriColor : -");
			}
			else
			{
				triColorLabel = new Label("TriColor : " + triColorPassedLevel);
			}
			triColorLabel.setPrefWidth(500);
			triColorLabel.setStyle("-fx-font-size: 24px; -fx-font-family:\"Arial Black\";");

			HBox classicTimerHBox = new HBox(4);
			classicTimerHBox.getChildren().addAll(classicLabel, timerLabel);
			classicTimerHBox.setPrefHeight(28);

			HBox drawTriColorHBox = new HBox(4);
			drawTriColorHBox.getChildren().addAll(drawLabel, triColorLabel);
			drawTriColorHBox.setPrefHeight(28);

			this.getChildren().addAll(nameLabel, classicTimerHBox, drawTriColorHBox);
			//this.setStyle("-fx-background-color: #ff123456");
			setPrefHeight(112);
			setPrefWidth(1008);
			this.setStyle("-fx-border-color: #606060; -fx-border-width: 4px;");
			
			this.setOnMouseClicked(new EventHandler<MouseEvent>()
			{
				@Override
				public void handle(MouseEvent event)
				{
					PlayerButton source = (PlayerButton)event.getSource();
					PlayerButton selectedPlayer = LoadingSelection.getSelectedPlayer();
					if(source.name.length()<4 && isLoading)
					{
						LoadingSelection.setSelectedPlayerBorder();
						setOKDisable(true);
						LoadingSelection.setSelectedPlayer(null);
						return;
					}
					if(selectedPlayer == null)
					{
						setOKDisable(false);
						LoadingSelection.setSelectedPlayer(source);
						return;
					}
					LoadingSelection.setSelectedPlayerBorder();
					if(!(selectedPlayer.name.equals(source.name) && selectedPlayer.n == source.n))
					{
						setOKDisable(false);
						LoadingSelection.setSelectedPlayer(source);
					}
					else
					{
						setOKDisable(true);
						LoadingSelection.setSelectedPlayer(null);
					}
				}
			});
		}
	}

	protected PlayerButton player1;
	protected PlayerButton player2;
	protected PlayerButton player3;
	protected PlayerButton player4;
	protected static PlayerButton selectedPlayer;
	protected OKButton okButton;
	protected BackButton returnButton;
	protected VBox upVBox;

	public LoadingSelection(boolean isLoading)
	{
		super();
		selectedPlayer=null;
		setStyle("-fx-background-color: #E6DF44");
		
		VBox playerSelectionVBox = new VBox(5);
		playerSelectionVBox.setPrefHeight(505);
		playerSelectionVBox.setPrefWidth(1024);
		playerSelectionVBox.setPadding(new Insets(5, 150, 5, 150));
		playerSelectionVBox.setStyle("-fx-background-color: rgba(255, 255, 255, 0.3); -fx-background-radius: 10;");
		
		PlayerInfo player1Info = new PlayerInfo(1);
		PlayerInfo player2Info = new PlayerInfo(2);
		PlayerInfo player3Info = new PlayerInfo(3);
		PlayerInfo player4Info = new PlayerInfo(4);

		this.player1 = new PlayerButton(player1Info,isLoading);
		this.player2 = new PlayerButton(player2Info,isLoading);
		this.player3 = new PlayerButton(player3Info,isLoading);
		this.player4 = new PlayerButton(player4Info,isLoading);

		playerSelectionVBox.getChildren().addAll(player1, player2, player3, player4);

		upVBox = new VBox();
		upVBox.setAlignment(Pos.CENTER);
		upVBox.setPrefHeight(123);
		
		Label upLabel = new Label("Select the game slot");
		upLabel.setStyle("-fx-font-size: 48px; -fx-font-family:\"Arial Black\";");
		upLabel.setTextFill(Color.web("#FEF3E2"));
		upVBox.getChildren().add(upLabel);
		upVBox.setStyle("-fx-background-color: #F0810F");
		
		HBox downHBox = new HBox(100);
		okButton = new OKButton(70, 84);
		returnButton = new BackButton(70, 84, new StartMenu());
		
		downHBox.getChildren().addAll(returnButton,okButton);
		downHBox.setStyle("-fx-background-color: transparent");
		downHBox.setPrefHeight(92);
		downHBox.setAlignment(Pos.CENTER);
		
		okButton.setDisable(true);
		okButton.setOnMouseClicked(new EventHandler<MouseEvent>()
		{
			@Override
			public void handle(MouseEvent event)
			{
				OKButton.playSoundEffect();
				PlayerInfo.setSelectedPlayerInfo(selectedPlayer.playerInfo);
				MainMenu mainMenu = new MainMenu();
				Main.changeScene(mainMenu);
			}
		});
		
		this.getChildren().addAll(upVBox,playerSelectionVBox,downHBox);
	}
	
	public static void setSelectedPlayerBorder()
	{
		if(selectedPlayer == null)
			return;
		selectedPlayer.setStyle("-fx-border-color: #606060; -fx-border-width: 4px;");
	}
	
	public static void setSelectedPlayer(PlayerButton o)
	{
		selectedPlayer=o;
		if(o!=null)
		{
			o.setStyle("-fx-border-color: #000000; -fx-border-width: 4px;");
		}
	}
	
	public void setOKDisable(Boolean disable)
	{
		okButton.setDisable(disable);
	}
	
	public static PlayerButton getSelectedPlayer()
	{
		return selectedPlayer;
	}

}
