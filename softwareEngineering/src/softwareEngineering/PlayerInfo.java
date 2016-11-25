package softwareEngineering;

import java.awt.Point;

public class PlayerInfo {
	private PlayerImage player_Image;
	private Point loc_point;
	private int scaleX = 100, scaleY = 100;
	private float health;
	
	public PlayerInfo(int x, int y, int type){
		loc_point = new Point(x, y);
		if(type == PlayerImage.MERCY){
			scaleX += 35;
			scaleY += 35;
			player_Image = new PlayerImage(scaleX, scaleY, PlayerImage.MERCY);
		}
		else if(type == PlayerImage.TORBION){
			scaleX -= 15;
			scaleY -= 15;
			player_Image = new PlayerImage(scaleX, scaleY, PlayerImage.TORBION);
		}
		health = 100;
	}
	
	public void setStand(boolean s){
		player_Image.setStand(s);
	}
	
	public Point getLocation(){
		return loc_point;
	}
	
	public void setPoint(Point point){
		this.loc_point = point;
		setLocation(loc_point);
	}
	
	public void setPoint(int x, int y){
		loc_point.setLocation(x, y);
		setLocation(loc_point);
	}
	
	public void setX(int x){
		loc_point.setLocation(x, loc_point.y);
		setLocation(loc_point);
	}
	
	public int getX(){
		return loc_point.x;
	}
	
	public void setY(int y){
		loc_point.setLocation(loc_point.x, y);
		setLocation(loc_point);
	}
	
	public int getY(){
		return loc_point.y;
	}
	
	public int getScaleX(){
		return scaleX;
	}
	
	public int getScaleY(){
		return scaleY;
	}
	
	public void setHealth(float h){
		health = health + h;
		if(health > 100) health = 100;
		else if (health < 0) health = 0;
	}
	
	public float getHealth(){
		return health;
	}
	
	private void setLocation(Point point){
		player_Image.setLocation(point);
	}
	
	public PlayerImage getPlayerLabel(){
		return player_Image;
	}
}