package application;

import application.Button.BackButton;
import application.Button.GameMenuButton;
import application.PlayerData.PlayerInfo;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

public class ImageViewer extends VBox {
	public ImageViewer(int level) {
		super();
		setStyle("-fx-background-color: #886ABB;");
		setAlignment(Pos.TOP_CENTER);

		Label sceneTitle = new Label("LEVEL " + level);
		sceneTitle.setPrefWidth(1280);
		sceneTitle.setPrefHeight(50);
		sceneTitle.setAlignment(Pos.CENTER);
		sceneTitle.setStyle(
				"-fx-font-size: 40px; -fx-text-fill: white; -fx-font-family:\"Arial Black\"; -fx-background-color: #4200B6;");

		ImageView imageView = new ImageView(
				new Image(ClassLoader.getSystemResource("classic/" + level + "/full.png").toString()));
		imageView.setFitHeight(550);
		imageView.setFitWidth(550);
		VBox image = new VBox();
		image.setPadding(new Insets(10, 0, 10, 0));
		image.setAlignment(Pos.CENTER);
		image.setStyle("-fx-background-color: rgba(255, 255, 255, 0.5);");
		image.getChildren().add(imageView);

		GameMenuButton previousImageButton = new GameMenuButton(80, 80, "prev.png");
		previousImageButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				Main.playSoundEffect("click.wav");
				ImageViewer imageViewer = new ImageViewer(level - 1);
				Main.changeScene(imageViewer);
			}
		});

		GameMenuButton nextImageButton = new GameMenuButton(80, 80, "next.png");
		previousImageButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				Main.playSoundEffect("click.wav");
				ImageViewer imageViewer = new ImageViewer(level - 1);
				Main.changeScene(imageViewer);
			}
		});

		BackButton backButton = new BackButton(80, 96, new Gallery());

		HBox controlBox = new HBox(15);
		controlBox.setAlignment(Pos.CENTER);
		controlBox.getChildren().addAll(previousImageButton, backButton, nextImageButton);

		int unlockedImages = PlayerInfo.getClassicPassedLevel();
		if (level - 1 == 0) {
			previousImageButton.setVisible(false);
		}
		if (level + 1 > unlockedImages) {
			nextImageButton.setVisible(false);
		}

		getChildren().addAll(sceneTitle, image, controlBox);
	}
}
