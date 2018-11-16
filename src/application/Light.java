package application;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Light extends Button
{
	private int maxState;
	private int currentState;
	private ImageView back;
	private ImageView test;
	private int size;
	
	public Light()
	{
		back = new ImageView(new Image(ClassLoader.getSystemResource("back_light.png").toString()));
		test = new ImageView(new Image(ClassLoader.getSystemResource("test.png").toString()));
		maxState = 2;
		currentState = 0;
		textProperty().set("N/A");
		size =80;
		setPrefHeight(size);
		setPrefWidth(size);
		setGraphic(back);
	}

	public Light(String text, int h, int w, int maxState)
	{
		back = new ImageView(new Image(ClassLoader.getSystemResource("back_light.png").toString()));
		test = new ImageView(new Image(ClassLoader.getSystemResource("test.png").toString()));
		size = h;
		setMaxSize();
		setPadding(new Insets(0,0,0,0));
		this.maxState = maxState;
		this.currentState = 0;
		//textProperty().set(text);
		setMaxHeight(size);
		setMaxWidth(size);
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
			setMaxSize();
			setStyle("");
			/// black green (to 1)
		}
		else if (currentState == maxState - 1)
		{
			currentState = (currentState + 1) % maxState;
			setGraphic(back);
			setMaxSize();
			setStyle("");
			/// light green (to 0)
		}
		else
		{
			currentState = (currentState + 1) % maxState;
			setMaxSize();
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
	
	public void setMaxSize()
	{
		back.setFitHeight(size);
		back.setFitWidth(size);
		test.setFitHeight(size);
		test.setFitWidth(size);
	}
	
	public void setMinSize()
	{
		back.setFitHeight(size-6);
		back.setFitWidth(size-6);
		test.setFitHeight(size-6);
		test.setFitWidth(size-6);
	}
}
