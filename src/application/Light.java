package application;

import javafx.scene.control.Button;

public class Light extends Button
{
	private int maxState;
	private int currentState;

	public Light()
	{
		maxState = 2;
		currentState = 0;
		textProperty().set("N/A");
		setPrefHeight(80);
		setPrefWidth(80);
		setStyle("-fx-background-color: #239914;");
		//setPickOnBounds(false);
	}

	public Light(String text,int h,int w,int maxState)
	{
		this.maxState = maxState;
		this.currentState = 0;
		textProperty().set(text);
		setPrefHeight(h);
		setPrefWidth(w);
		setStyle("-fx-background-color: #239914;");
		setPickOnBounds(false);
	}

	public void changeColor()
	{
		if (currentState == 0)
		{
			currentState = (currentState+1)%maxState;
			setStyle("-fx-background-color: #51F827;");
			///black green (to 1)
		}
		else if(currentState == maxState-1)
		{
			currentState = (currentState+1)%maxState;
			setStyle("-fx-background-color: #239914;");
			///light green (to 0)
		}
		else
		{
			currentState = (currentState+1)%maxState;
			setStyle("-fx-background-color: #C92016;");
			///red (to 2)
		}
	}
}
