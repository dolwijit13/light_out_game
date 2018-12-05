package application.GameLogic;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Light extends Button
{
	private int maxState;
	private int currentState;
	private ImageView state0;
	private ImageView state1;
	private ImageView state2;
	private int size;
	private int mode;
	private int shouldPress;
	
	public Light()
	{
		//back = new ImageView(new Image(ClassLoader.getSystemResource("back_light.png").toString()));
		//test = new ImageView(new Image(ClassLoader.getSystemResource("test.png").toString()));
		maxState = 2;
		currentState = 0;
		textProperty().set("N/A");
		size =80;
		setPrefHeight(size);
		setPrefWidth(size);
		//setGraphic(back);
	}

	public Light(String text, int h, int w, int maxState, int mode, int level) //mode : 0 = classic, 1 = time, 2 = ???, 3 = 3 colors
	{
		this.shouldPress=0;
		this.mode = mode;
		switch (mode) {
		case 0:
			//for classic
			state0 = new ImageView(new Image(ClassLoader.getSystemResource("classic/"+level+"/"+text+".png").toString()));
			state1 = new ImageView(new Image(ClassLoader.getSystemResource("back_light.png").toString()));
			break;
		case 1:
			//for timer
			state0 = new ImageView(new Image(ClassLoader.getSystemResource("green.png").toString()));
			state1 = new ImageView(new Image(ClassLoader.getSystemResource("red.png").toString()));
			break;
		case 2:
			//for
			state0 = new ImageView(new Image(ClassLoader.getSystemResource("back_light.png").toString()));
			state1 = new ImageView(new Image(ClassLoader.getSystemResource("green.png").toString()));
			state2 = new ImageView(new Image(ClassLoader.getSystemResource("red.png").toString()));
			break;
		default:
			break;
		}
		//test = new ImageView(new Image(ClassLoader.getSystemResource("test.png").toString()));
		size = h;
		setPadding(new Insets(0,0,0,0));
		this.maxState = maxState;
		this.currentState = 0;
		//textProperty().set(text);
		setMaxHeight(size);
		setMaxWidth(size);
		setMaxSize();
		setGraphic(state0);
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
			setGraphic(state1);
			//setMaxSize();
			//setStyle("");
			/// black green (to 1)
		}
		else if (currentState == maxState - 1)
		{
			currentState = (currentState + 1) % maxState;
			setGraphic(state0);
			//setMaxSize();
			//setStyle("");
			/// light green (to 0)
		}
		else
		{
			currentState = (currentState + 1) % maxState;
			setGraphic(state2);
			//setMaxSize();
			//setStyle("");
			/// red (to 2)
		}
	}

	public void setCurrentState(int currentState)
	{
		this.currentState = currentState;
		switch(currentState) {
		case 0 : setGraphic(state0);
		case 1 : setGraphic(state1);
		case 2 : setGraphic(state2);
		}
		setStyle("");
	}
	
	public int getCurrentState()
	{
		return currentState;
	}
	
	public Light plus(Light o)
	{
		int tmp=this.getCurrentState()+o.getCurrentState();
        return new Light(tmp%2,2);
	}
	
	public Light multiply(Light o)
	{
		int tmp=this.getCurrentState()*o.getCurrentState();
        return new Light(tmp%2,2);
	}
	
	public void setMaxSize()
	{
		state0.setFitHeight(size);
		state0.setFitWidth(size);
		state1.setFitHeight(size);
		state1.setFitWidth(size);
		if(mode == 2) {
			state2.setFitHeight(size);
			state2.setFitWidth(size);
		}
	}
	
	public void setMinSize()
	{
		state0.setFitHeight(size-6);
		state0.setFitWidth(size-6);
		state1.setFitHeight(size-6);
		state1.setFitWidth(size-6);
		if(mode == 2) {
			state2.setFitHeight(size-6);
			state2.setFitWidth(size-6);
		}
	}
	
	public int getMaxState()
	{
		return maxState;
	}
	
	public void addShouldPress()
	{
		shouldPress = (shouldPress+1 ) %maxState;
	}
	
	public boolean isShouldPress()
	{
		return shouldPress!=0;
	}
}
