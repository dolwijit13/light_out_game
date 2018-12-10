package application;

import application.Button.BackButton;
import application.Button.PictureWithTextButton;
import application.PlayerData.CreateNewPlayer;
import application.PlayerData.LeaderBoard;
import application.PlayerData.LoadingSelection;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class StartMenu extends HBox {
	public StartMenu() {
		super(340);
		setPadding(new Insets(0, 0, 0, 40));
		setAlignment(Pos.TOP_LEFT);

		VBox logoVBox = new VBox(0);
		logoVBox.setPadding(new Insets(120, 0, 0, 0));
		VBox insideLogoVBox = new VBox();
		insideLogoVBox.setPadding(new Insets(30, 30, 30, 30));
		ImageView logo = new ImageView(new Image(ClassLoader.getSystemResource("assets/logo.png").toString()));
		logo.setFitHeight(126);
		logo.setFitWidth(406);
		insideLogoVBox.getChildren().add(logo);
		insideLogoVBox.setStyle("-fx-background-color: rgba(255, 255, 255, 0.5); -fx-background-radius: 10;");
		logoVBox.getChildren().add(insideLogoVBox);

		VBox rightVBox = new VBox(10);
		rightVBox.setAlignment(Pos.BOTTOM_RIGHT);
		rightVBox.setPadding(new Insets(0, 50, 20, 0));
		VBox menuVBox = new VBox(0);
		VBox insideMenuVBox = new VBox(30);
		menuVBox.setPadding(new Insets(200, 0, 0, 0));
		insideMenuVBox.setPadding(new Insets(30, 50, 30, 50));

		PictureWithTextButton newGameButton = new PictureWithTextButton(84, 268, 1, "NEW GAME", 40);
		PictureWithTextButton loadGameButton = new PictureWithTextButton(84, 268, 2, "LOAD GAME", 40);
		PictureWithTextButton leaderboardButton = new PictureWithTextButton(84, 300, 4, "LEADERBOARD", 40);
		BackButton exitButton = new BackButton(150, 180, null);
		menuVBox.getChildren().add(insideMenuVBox);
		insideMenuVBox.getChildren().addAll(newGameButton, loadGameButton, leaderboardButton);
		insideMenuVBox.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5); -fx-background-radius: 10;");
		rightVBox.getChildren().addAll(menuVBox, exitButton);

		setBackground(new Background(new BackgroundImage(
				new Image(ClassLoader.getSystemResource("assets/startmenubackground.png").toString()), null, null, null,
				null)));
		getChildren().addAll(logoVBox, rightVBox);

		newGameButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				Main.playSoundEffect("click.wav");
				CreateNewPlayer creatNewPlayer = new CreateNewPlayer();
				Main.changeScene(creatNewPlayer);
			}

		});

		loadGameButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				Main.playSoundEffect("click.wav");
				LoadingSelection loadingSelection = new LoadingSelection(true);
				Main.changeScene(loadingSelection);
			}

		});

		leaderboardButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				Main.playSoundEffect("click.wav");
				LeaderBoard leaderBoard = new LeaderBoard(0);
				Main.changeScene(leaderBoard);
			}

		});
	}
}
