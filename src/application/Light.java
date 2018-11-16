package application;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Light extends Button
{
	private int maxState;
	private int currentState;
	private ImageView back = new ImageView(new Image(ClassLoader.getSystemResource("back_light.png").toString()));
	private ImageView test = new ImageView(new Image(ClassLoader.getSystemResource("test.png").toString()));
	
	public Light()
	{
		maxState = 2;
		currentState = 0;
		textProperty().set("N/A");
		setPrefHeight(80);
		setPrefWidth(80);
		setGraphic(back);
		// setPickOnBounds(false);
	}

	public Light(String text, int h, int w, int maxState)
	{
		back.setFitHeight(h);
		back.setFitWidth(w);
		test.setFitHeight(h);
		test.setFitWidth(w);
		setPadding(new Insets(0,0,0,0));
		this.maxState = maxState;
		this.currentState = 0;
		//textProperty().set(text);
		setPrefHeight(h);
		setPrefWidth(w);
		setGraphic(back);
		setPickOnBounds(false);
		setId(text);
	}
	
	//for BoardSolver
	public Light(int curState,int maxState)
	{
		this.maxState = maxState;
		this.currentState = curState;
	}

	public void changeColor()
	{
		if (currentState == 0)
		{
			currentState = (currentState + 1) % maxState;
			setGraphic(test);
			setStyle("");
			/// black green (to 1)
		}
		else if (currentState == maxState - 1)
		{
			currentState = (currentState + 1) % maxState;
			setGraphic(back);
			setStyle("");
			/// light green (to 0)
		}
		else
		{
			currentState = (currentState + 1) % maxState;
			setStyle("-fx-background-color: #C92016;");
			/// red (to 2)
		}
	}

	public void setCurrentState(int currentState)
	{
		this.currentState = currentState;
		setGraphic(back);
		setStyle("");
	}
	
	public int getCurrentState()
	{
		return currentState;
	}
	
	public Light plus(Light o)
	{
		if(this.getCurrentState() + o.getCurrentState() == 1)
			return new Light(1,2);
        return new Light(0,2);
	}
	
	public Light multiply(Light o)
	{
		if(this.getCurrentState() == o.getCurrentState() && o.getCurrentState()==1)
            return new Light(1,2);
        return new Light(0,2);
	}
}
