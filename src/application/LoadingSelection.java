package application;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class LoadingSelection extends VBox
{
	protected class PlayerButton extends VBox
	{
		protected PlayerInfo playerInfo;
		protected String name;
		protected int classicLastPassedLevel; //// number of passed level in classic mode
		protected int timerPassedLevel; // number of passed level in timer mode
		protected int drawPassedLevel; // number of passed level in draw mode
		protected int triColorPassedLevel; // number of passed level in tricolor mode

		public PlayerButton(PlayerInfo playerInfo)
		{
			super();
			this.playerInfo = playerInfo;
			this.name = playerInfo.name;
			this.classicLastPassedLevel = playerInfo.classicLastPassedLevel;
			this.timerPassedLevel = playerInfo.timerPassedLevel;
			this.drawPassedLevel = playerInfo.drawPassedLevel;
			this.triColorPassedLevel = playerInfo.triColorPassedLevel;

			Label nameLabel = new Label(name);
			nameLabel.setPrefHeight(44);
			nameLabel.setStyle("-fx-font-size: 32px; -fx-font-family:\"Arial Black\";");

			Label classicLabel = new Label("Classic : " + classicLastPassedLevel);
			classicLabel.setPrefWidth(500);
			classicLabel.setStyle("-fx-font-size: 24px; -fx-font-family:\"Arial Black\";");

			Label timerLabel = new Label("Timer : " + timerPassedLevel);
			timerLabel.setPrefWidth(500);
			timerLabel.setStyle("-fx-font-size: 24px; -fx-font-family:\"Arial Black\";");

			Label drawLabel = new Label("Draw : " + drawPassedLevel);
			drawLabel.setPrefWidth(500);
			drawLabel.setStyle("-fx-font-size: 24px; -fx-font-family:\"Arial Black\";");

			Label triColorLabel = new Label("TriColor : " + triColorPassedLevel);
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
					if(selectedPlayer == null)
					{
						LoadingSelection.setSelectedPlayer(source);
						return;
					}
					LoadingSelection.setSelectedPlayerBorder();
					if(!selectedPlayer.name.equals(source.name))
					{
						LoadingSelection.setSelectedPlayer(source);
					}
					else
					{
						LoadingSelection.setSelectedPlayer(null);
					}
				}
			});
		}
	}

	private PlayerButton player1;
	private PlayerButton player2;
	private PlayerButton player3;
	private PlayerButton player4;
	private static PlayerButton selectedPlayer;

	public LoadingSelection()
	{
		super();
		selectedPlayer=null;
		
		HBox playerSelectionHBox = new HBox(5);
		playerSelectionHBox.setPrefHeight(505);
		playerSelectionHBox.setPrefWidth(1280);
		playerSelectionHBox.setStyle("-fx-background-color: #9D6CC8");

		HBox playerSelectionTmpHBox = new HBox();
		playerSelectionTmpHBox.setPrefHeight(505);
		playerSelectionTmpHBox.setPrefWidth(128);
		playerSelectionTmpHBox.setStyle("-fx-background-color: #7B2FBD");

		HBox playerSelectionTmpHBox2 = new HBox();
		playerSelectionTmpHBox2.setPrefHeight(505);
		playerSelectionTmpHBox2.setPrefWidth(128);
		playerSelectionTmpHBox2.setStyle("-fx-background-color: #7B2FBD");

		VBox playerSelectionVBox = new VBox(5);
		playerSelectionVBox.setPrefHeight(505);
		playerSelectionVBox.setPrefWidth(1024);
		playerSelectionVBox.setPadding(new Insets(5, 5, 5, 5));
		
		PlayerInfo player1Info = new PlayerInfo(1);
		PlayerInfo player2Info = new PlayerInfo(2);
		PlayerInfo player3Info = new PlayerInfo(3);
		PlayerInfo player4Info = new PlayerInfo(4);

		this.player1 = new PlayerButton(player1Info);
		this.player2 = new PlayerButton(player2Info);
		this.player3 = new PlayerButton(player3Info);
		this.player4 = new PlayerButton(player4Info);

		playerSelectionVBox.getChildren().addAll(player1, player2, player3, player4);

		playerSelectionHBox.getChildren().addAll(playerSelectionTmpHBox, playerSelectionVBox, playerSelectionTmpHBox2);

		
		VBox upVBox = new VBox();
		upVBox.setAlignment(Pos.CENTER);
		upVBox.setPrefHeight(123);
		
		Label upLabel = new Label("Select the game slot");
		upLabel.setStyle("-fx-font-size: 48px; -fx-font-family:\"Arial Black\";");
		upVBox.getChildren().add(upLabel);
		upVBox.setStyle("-fx-background-color: #0D00FF");
		
		HBox downHBox = new HBox(20);
		ToStartMenuButton returnButton = new ToStartMenuButton();
		returnButton.setText("RETURN");
		returnButton.setPrefHeight(52);
		returnButton.setPrefWidth(585);
		Button OKButton = new Button("OK");
		OKButton.setPrefHeight(52);
		OKButton.setPrefWidth(585);
		
		downHBox.getChildren().addAll(returnButton,OKButton);
		downHBox.setStyle("-fx-background-color: #0D00FF");
		downHBox.setPrefHeight(92);
		downHBox.setAlignment(Pos.CENTER);
		
		this.getChildren().addAll(upVBox,playerSelectionHBox,downHBox);
		
		OKButton.setOnMouseClicked(new EventHandler<MouseEvent>()
		{
			@Override
			public void handle(MouseEvent event)
			{
				PlayerInfo.setSelectedPlayerInfo(selectedPlayer.playerInfo);
				MainMenu mainMenu = new MainMenu();
				Main.changeScene(mainMenu);
			}
		});
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
	
	public static PlayerButton getSelectedPlayer()
	{
		return selectedPlayer;
	}

}
