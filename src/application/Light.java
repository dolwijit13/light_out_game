package application;

import javafx.scene.control.Button;

public class Light extends Button
{
	private Boolean isOpen;

	public Light()
	{
		isOpen = false;
		textProperty().set("N/A");
		setPrefHeight(80);
		setPrefWidth(80);
		setStyle("-fx-background-color: #239914;");
		setPickOnBounds(false);
	}

	public Light(String text)
	{
		isOpen = false;
		textProperty().set(text);
		setPrefHeight(80);
		setPrefWidth(80);
		setStyle("-fx-background-color: #239914;");
		setPickOnBounds(false);
	}

	public void changeColor()
	{
		if (isOpen)
		{
			isOpen = false;
			setStyle("-fx-background-color: #239914;");
		}
		else
		{
			isOpen = true;
			setStyle("-fx-background-color: #51F827;");
		}
	}
}
