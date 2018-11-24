package application;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class Gallery extends VBox
{
	public Gallery()
	{
		super(10);
		setAlignment(Pos.CENTER);
		setPadding(new Insets(10, 10, 10, 10));
		Text sceneTitle = new Text("GALLERY");
		sceneTitle.setStyle("-fx-font-size: 32px; -fx-font-family:\"Arial Black\";-fx-fill: #555;");
		
//		ImageView lockedImage = new ImageView(new Image(ClassLoader.getSystemResource("assets/locked_image.png").toString()));
//		lockedImage.setFitHeight(120);
//		lockedImage.setFitWidth(120);
		
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setVgap(15);
		grid.setHgap(30);
		
		int unlockedImages = PlayerInfo.getClassicLastPassedLevel(); 
		for (int i = 0; i < 4; i++)
		{
			for (int j = 0; j < 5; j++)
			{
				int curLevel = (i * 5 + j + 1);
				Button level = new Button();
				level.setPrefHeight(120);
				level.setPrefWidth(120);
				level.setPadding(new Insets(0,0,0,0));
				//level.setStyle("-fx-background-color: #51F827;");
				level.setOnMouseClicked(mouseClick);
				level.setId("" + curLevel);
				if(curLevel <= unlockedImages)
				{
					ImageView image = new ImageView(new Image(ClassLoader.getSystemResource("classic/" + curLevel + "/full.png").toString()));
					image.setFitHeight(120);
					image.setFitWidth(120);
					level.setGraphic(image);
				}
				else {
					ImageView image = new ImageView(new Image(ClassLoader.getSystemResource("assets/locked_image.png").toString()));
					image.setFitHeight(120);
					image.setFitWidth(120);
					level.setGraphic(image);
					level.setDisable(true);
				}
				grid.add(level, j, i);
			}
		}

		ToMainMenuButton toMainMenuButton = new ToMainMenuButton();
		getChildren().addAll(sceneTitle, grid, toMainMenuButton);
	}

	private final EventHandler<MouseEvent> mouseClick = new EventHandler<MouseEvent>()
	{
		@Override
		public void handle(MouseEvent event)
		{
			Button pressed = (Button) event.getSource();
			int level = Integer.parseInt(pressed.getId());
			ImageViewer classicMode = new ImageViewer(level);
			Main.changeScene(classicMode);
		}
	};
}
