package application;

import application.PlayerData.CreateNewPlayer;
import application.PlayerData.LeaderBoard;
import application.PlayerData.LoadingSelection;
import javafx.animation.Animation;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class StartMenu extends HBox
{
	public StartMenu()
	{
		super(500);
		setPadding(new Insets(0,0,0,90));
		setAlignment(Pos.TOP_LEFT);
		
		VBox logoVBox = new VBox(0);
		logoVBox.setPadding(new Insets(200, 0, 0, 0));
		Button logo = new Button("AU");
		logo.setPrefHeight(150);
		logo.setPrefWidth(300);
		logoVBox.getChildren().add(logo);
		//logoVBox.setStyle("-fx-border-color: #828282;");
		
		VBox menuVBox = new VBox(0);
		VBox insideMenuVBox = new VBox(30);
		menuVBox.setPadding(new Insets(200, 0, 0, 0));
		insideMenuVBox.setPadding(new Insets(30, 100, 30, 100));
		Button newGame = new Button("New Game");
		Button loadGame = new Button("Load Game");
		Button leaderboard = new Button("Leaderboard");
		Button exit = new Button("Exit");
		menuVBox.getChildren().add(insideMenuVBox);
		insideMenuVBox.getChildren().addAll(newGame,loadGame,leaderboard,exit);
		insideMenuVBox.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5); -fx-background-radius: 10;");
		

		setBackground(new Background(new BackgroundImage(new Image(ClassLoader.getSystemResource("assets/startmenubackground.png").toString()), null, null, null, null)));
		getChildren().addAll(logoVBox,menuVBox);

		newGame.setOnMouseClicked(new EventHandler<MouseEvent>()
		{
			@Override
			public void handle(MouseEvent event)
			{
				CreateNewPlayer creatNewPlayer = new CreateNewPlayer();
				Main.changeScene(creatNewPlayer);
			}

		});
		
		loadGame.setOnMouseClicked(new EventHandler<MouseEvent>()
		{
			@Override
			public void handle(MouseEvent event)
			{
				LoadingSelection loadingSelection = new LoadingSelection(true);
				Main.changeScene(loadingSelection);
			}

		});
		
		leaderboard.setOnMouseClicked(new EventHandler<MouseEvent>()
		{
			@Override
			public void handle(MouseEvent event)
			{
				LeaderBoard leaderBoard = new LeaderBoard(0);
				Main.changeScene(leaderBoard);
			}

		});

		exit.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				Main.exit();
			}
		});
	}
}


