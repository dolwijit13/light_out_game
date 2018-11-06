package application;

import javax.xml.bind.annotation.XmlElementDecl.GLOBAL;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class Main extends Application
{
	@Override
	public void start(Stage primaryStage)
	{
		try
		{
			Board board = new Board(5);
			Scene scene = new Scene(board, 1000, 1000);
			primaryStage.setScene(scene);
			primaryStage.show();

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static void main(String[] args)
	{
		launch(args);
	}
}
