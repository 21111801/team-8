package softwareEngineering;

import javax.swing.JLabel;

public class Ground {
	protected int locateX, locateY, scaleX, scaleY;
	protected GroundImage gi;
	protected final static int SAFTY = 0, DAMAGE = 1, FALL = 2, HOLD = 3;
	private static float moveSpeed = (float) 2.0;		//2.1 ~ 9.9
	protected boolean score;
	public boolean isStand;
	private final static int EASY = 10, NORMAL = 20, HARD = 30;
	
	public Ground(int x, int y, int width, int height){
		locateX = x;
		locateY = y;
		scaleX = width;
		scaleY = height;
		score = true;
		gi = new GroundImage(width, height);
		isStand = false;
	}
	
	synchronized public JLabel getLabel(){
		JLabel temp_Label = gi.getLabel();
		temp_Label.setLocation(locateX, locateY);
		return temp_Label;
	}
	
	public boolean isScore(){
		return score;
	}
	
	public int getScore(String level){
		if(level.equals("Easy")){
			score = false;
			return EASY;
		}
		else if(level.equals("Normal")){
			score = false;
			return NORMAL;
		}
		else{
			score = false;
			return HARD;
		}
		
	}

	public static void setSpeed(float s){
		if(s < 2.0){
			moveSpeed = (float)2.0;
			return;
		}
		else if(s > 10.0){
			moveSpeed = (float)10.0;
			return;
		}
		s = s * 10;
		s = Math.round(s);
		moveSpeed = s / 10;
	}
	
	public void setX(int x){
		locateX = x;
		gi.getLabel().setLocation(x, locateY);
	}
	
	public void setY(int y){
		locateY = y;
		gi.getLabel().setLocation(locateX, y);
	}
	
	public int getX(){
		return locateX;
	}
	
	public int getY(){
		return locateY;
	}
	
	public void setScale(int x, int y){
		scaleX = x;
		scaleY = y;
	}
	
	public int getScaleX(){
		return scaleX;
	}
	
	public int getScaleY(){
		return scaleY;
	}
	
	public static float getSpeed(){
		return moveSpeed;
	}
	
	public void setImage(){
		gi.setImage("set");
	}
	
	public void resetImage(){
		gi.setImage("reset");
	}
	
	public int activeTrap(){
		return SAFTY;
	}
}
