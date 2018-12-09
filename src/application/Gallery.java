package application;

import application.Button.BackButton;
import application.Button.ToMainMenuButton;
import application.PlayerData.PlayerInfo;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class Gallery extends VBox
{
	public Gallery()
	{
		super();
		setStyle("-fx-background-color: #886ABB;");
		setAlignment(Pos.TOP_CENTER);
		
		Label sceneTitle = new Label("GALLERY");
		sceneTitle.setPrefWidth(1280);
		sceneTitle.setPrefHeight(70);
		sceneTitle.setAlignment(Pos.CENTER);
		sceneTitle.setStyle("-fx-font-size: 48px; -fx-text-fill: white; -fx-font-family:\"Arial Black\"; -fx-background-color: #4200B6; -fx-background-radius: 0;");
		
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setPadding(new Insets(10,0,10,0));
		grid.setVgap(15);
		grid.setHgap(30);
		grid.setStyle("-fx-background-color: rgba(255, 255, 255, 0.5);");
		
		int unlockedImages = PlayerInfo.getClassicPassedLevel(); 
		for (int i = 0; i < 4; i++)
		{
			for (int j = 0; j < 5; j++)
			{
				int curLevel = (i * 5 + j + 1);
				ImageView imageView = new ImageView();
				imageView.setFitHeight(120);
				imageView.setFitWidth(120);
				imageView.setId(""+curLevel);
				imageView.setOnMouseClicked(mouseClick);
				if(curLevel <= unlockedImages) {
					imageView.setImage(new Image(ClassLoader.getSystemResource("classic/"+curLevel+"/thumbnail.png").toString()));
					imageView.setDisable(false);
				}else {
					imageView.setImage(new Image(ClassLoader.getSystemResource("assets/locked_image.png").toString()));
					imageView.setDisable(true);
				}
				grid.add(imageView, j, i);
			}
		}

		BackButton backButton = new BackButton(100,120,1);
		getChildren().addAll(sceneTitle, grid, backButton);
	}

	private final EventHandler<MouseEvent> mouseClick = new EventHandler<MouseEvent>()
	{
		@Override
		public void handle(MouseEvent event)
		{
			ImageView pressed = (ImageView) event.getSource();
			int level = Integer.parseInt(pressed.getId());
			ImageViewer imageViewer = new ImageViewer(level);
			Main.changeScene(imageViewer);
		}
	};
}
