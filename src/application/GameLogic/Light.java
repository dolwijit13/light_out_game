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
	private String color0;
	private String color1;
	private String color2;
	private int size;
	private int mode;
	private int shouldPress;
	private String colorStyle = "";
	private String borderStyle = "";

	//mode : 0 = classic, 1 = timer, 2 = draw, 3 = triColor
	public Light(String text, int h, int w, int maxState, int mode, int level)
	{
		this.shouldPress=0;
		this.mode = mode;
		switch (mode) {
		case 0:
			//for classic
			state0 = new ImageView(new Image(ClassLoader.getSystemResource("assets/classic/"+level+"/"+text+".png").toString()));
			state1 = new ImageView(new Image(ClassLoader.getSystemResource("assets/classic/back_light.png").toString()));
			break;
		case 1:
			//for timer
			color0 = "#444444";
			color1 = "#69B6FF";
			break;
		case 2:
			//for draw
			color0 = "#444444";
			color1 = "#69B6FF";
			break;
		case 3:
			//for triColor
			color0 = "#444444";
			color1 = "#69B6FF";
			color2 = "#FF6969";
			break;
		default:
			break;
		}
		size = h;
		setPadding(new Insets(0,0,0,0));
		this.maxState = maxState;
		this.currentState = 0;
		setPrefHeight(size);
		setPrefWidth(size);
		setMaxHeight(size);
		setMaxWidth(size);
		setMaxSize();
		if(mode == 0) {
			setGraphic(state0);
		}else {
			setStyle("-fx-background-color: "+ color0 +";");
		}
		setPickOnBounds(false);
		setId(text);
	}
	
	//for BoardSolver
	public Light(int curState,int maxState)
	{
		this.currentState = curState;
		this.maxState = maxState;
	}

	public void changeColor()
	{
		if (currentState == 0)
		{
			currentState = (currentState + 1) % maxState;
			if(mode == 0) {
				setGraphic(state1);
			}else {
				setColor(color1);
			}
			/// Gray to Blue (to 1)
		}
		else if (currentState == maxState - 1)
		{
			currentState = (currentState + 1) % maxState;
			if(mode == 0) {
				setGraphic(state0);
			}else {
				setColor(color0);
			}
			/// Blue (or Red) to Gray (to 0)
		}
		else
		{
			currentState = (currentState + 1) % maxState;
			setColor(color2);
			/// Blue to Red (to 2)
		}
	}
	
	public void setColor(String color) {
		colorStyle = "-fx-background-color: "+ color +";";
		setStyle(colorStyle+borderStyle);
	}
	
	public void setBorder(String border) {
		borderStyle = border;
		setStyle(colorStyle+borderStyle);
	}
	
	public String getBorderStyle() {
		return borderStyle;
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
		if(mode == 0) {
			state0.setFitHeight(size);
			state0.setFitWidth(size);
			state1.setFitHeight(size);
			state1.setFitWidth(size);
		}
	}
	
	public void setMinSize()
	{
		if(mode == 0) {
			state0.setFitHeight(size-6);
			state0.setFitWidth(size-6);
			state1.setFitHeight(size-6);
			state1.setFitWidth(size-6);
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
