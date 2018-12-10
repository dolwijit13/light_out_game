package application;

import application.Button.BackButton;
import application.PlayerData.PlayerInfo;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class Gallery extends VBox {
	public Gallery() {
		super();
		setStyle("-fx-background-color: #886ABB;");
		setAlignment(Pos.TOP_CENTER);

		Label sceneTitle = new Label("GALLERY");
		sceneTitle.setPrefWidth(1280);
		sceneTitle.setPrefHeight(70);
		sceneTitle.setAlignment(Pos.CENTER);
		sceneTitle.setStyle(
				"-fx-font-size: 48px; -fx-text-fill: white; -fx-font-family:\"Arial Black\"; -fx-background-color: #4200B6;");

		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setPadding(new Insets(10, 0, 10, 0));
		grid.setVgap(15);
		grid.setHgap(30);
		grid.setStyle("-fx-background-color: rgba(255, 255, 255, 0.5);");

		int unlockedImages = PlayerInfo.getClassicPassedLevel();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 5; j++) {
				int curLevel = (i * 5 + j + 1);
				ImageView imageView = new ImageView();
				imageView.setFitHeight(120);
				imageView.setFitWidth(120);
				imageView.setId("" + curLevel);
				imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						Main.playSoundEffect("click.wav");
						ImageView pressed = (ImageView) event.getSource();
						int level = Integer.parseInt(pressed.getId());
						ImageViewer imageViewer = new ImageViewer(level);
						Main.changeScene(imageViewer);
					}
				});
				if (curLevel <= unlockedImages) {
					imageView.setImage(new Image(
							ClassLoader.getSystemResource("assets/classic/" + curLevel + "/thumbnail.png").toString()));
					imageView.setDisable(false);
				} else {
					imageView.setImage(new Image(ClassLoader.getSystemResource("assets/locked_image.png").toString()));
					imageView.setDisable(true);
				}
				grid.add(imageView, j, i);
			}
		}
		BackButton backButton = new BackButton(100, 120, new MainMenu());

		getChildren().addAll(sceneTitle, grid, backButton);
	}
}
