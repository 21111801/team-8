package softwareEngineering;

import javax.swing.JLabel;

public class Missile {
	private int locateX, locateY, scaleX, scaleY;
	private MissileImage mi;
	private int moveSpeed, curType;
	public final static int MERCY = 0, PARA = 1, GENZI = 2;
	
	public Missile(int x, int y, int type){
		locateX = x;
		locateY = y;
		scaleX = 100;
		scaleY = 70;
		mi = new MissileImage(scaleX, scaleY);
		moveSpeed = Math.round(Ground.getSpeed())+5;
		curType = type;
		switch(curType){
		case MERCY:
			mi.setImage("mercy");
			break;
		case PARA:
			mi.setImage("para");
			break;
		case GENZI:
			mi.setImage("genzi");
			break;
		}
	}
	
	public void setX(int x){
		locateX = x;
		mi.getLabel().setLocation(x, locateY);
	}
	
	public void setY(int y){
		locateY = y;
		mi.getLabel().setLocation(locateX, y);
	}
	
	public int getX(){
		return locateX;
	}
	
	public int getY(){
		return locateY;
	}
	
	public int getWidth(){
		return scaleX;
	}
	
	public int getHeight(){
		return scaleY;
	}
	
	public int getSpeed(){
		return moveSpeed;
	}
	
	public JLabel getLabel(){
		return mi.getLabel();
	}
	
	public int getType(){
		return curType;
	}
}
