package application;

import application.Button.ToMainMenuButton;
import application.PlayerData.PlayerInfo;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class ImageViewer extends VBox {
	public ImageViewer(int level) {
		super(10);
		setAlignment(Pos.CENTER);
		setPadding(new Insets(10, 10, 10, 10));
		Text sceneTitle = new Text("LEVEL " + level);
		sceneTitle.setStyle("-fx-font-size: 32px; -fx-font-family:\"Arial Black\";-fx-fill: #555;");
		
		ImageView imageView = new ImageView(new Image(ClassLoader.getSystemResource("classic/"+level+"/full.png").toString()));
		imageView.setFitHeight(590);
		imageView.setFitWidth(590);
		
		HBox hBox = new HBox(15);
		hBox.setAlignment(Pos.CENTER);
		Button previousImageButton = new Button("Previous");
		Button nextImageButton = new Button("Next");
		ToMainMenuButton toMainMenuButton = new ToMainMenuButton();
		hBox.getChildren().addAll(previousImageButton, toMainMenuButton, nextImageButton);
		
		int unlockedImages = PlayerInfo.getClassicPassedLevel();
		if(level - 1 == 0) previousImageButton.setDisable(true);
		if(level + 1 > unlockedImages) nextImageButton.setDisable(true);
		
		previousImageButton.setOnMouseClicked(new EventHandler<MouseEvent>()
		{
			@Override
			public void handle(MouseEvent event)
			{
				ImageViewer imageViewer = new ImageViewer(level-1);
				Main.changeScene(imageViewer);
			}
		});
		
		nextImageButton.setOnMouseClicked(new EventHandler<MouseEvent>()
		{
			@Override
			public void handle(MouseEvent event)
			{
				ImageViewer imageViewer = new ImageViewer(level+1);
				Main.changeScene(imageViewer);
			}
		});
		
		getChildren().addAll(sceneTitle, imageView, hBox);
	}
}
