package softwareEngineering;

import java.util.ArrayList;

public class MadeGround{
	private ArrayList<Ground> ground_List;
	private int scaleX, scaleY, trap_Percent = 0, start_Trap_Num = 5, limit_Trap = 0;
	private static boolean hard;
	private static int MAXIMUM_HEIGHT = 650, MINIMUM_HEIGHT = 350;
	public GroundManagement ground_Manager;
	public static int DISTANCE;
	
	public MadeGround(int scaleX, int scaleY, GroundManagement gm){
		this.scaleX = scaleX;
		this.scaleY = scaleY;
		hard = false;
		ground_List = new ArrayList<Ground>();
		ground_Manager = gm;
		DISTANCE = 35;
	}
	
	public void setTrapLevel(int percent){
		trap_Percent = percent;
	}
	
	public void addGround(int locateX, int locateY){
		if(locateX == -1 && locateY == -1){
			ground_List.add(new GoalGround(1500, 0, 500, 3000));
			return;
		}
		if(locateX == 0){
			ground_List.add(new StartGround(0, locateY, 1500, ground_Manager.getScaleY()));
			return;
		}
		if (locateY > MAXIMUM_HEIGHT) locateY = MAXIMUM_HEIGHT;
		else if(locateY < MINIMUM_HEIGHT) locateY = MINIMUM_HEIGHT;
		if(start_Trap_Num > 0 || (int)(Math.random()*100) >= trap_Percent){
			if(start_Trap_Num != 0)
				start_Trap_Num--;
			ground_List.add(new Ground(locateX, locateY, scaleX, scaleY));
			limit_Trap = 0;
		}
		else{
			ground_List.add(new TrapGround(locateX, locateY, scaleX, scaleY));
			limit_Trap++;
			if(limit_Trap >= 2)
				start_Trap_Num++;
		}
	}
	
	public Ground getGround(int index){
		Ground temp_Ground = ground_List.get(index);
		ground_Manager.addMove(temp_Ground);
		return temp_Ground;
	}
	
	public float getSpeed(){
		float temp = Ground.getSpeed();
		return temp;
	}
	
	public ArrayList<Ground> getGrounds(){
		return ground_List;
	}

	public int getNumGround(){
		return ground_List.size();
	}
	
	public int getScaleX(){
		return 100;
	}
	
	public int getScaleY(){
		return scaleY;
	}
	
	public void setHard(boolean h){
		hard = h;
		if(hard)
			MINIMUM_HEIGHT = 400;
	}
	
	public static void setDistance(float s){
		int temp_Num = Math.round(s);
		DISTANCE = DISTANCE - 4*(temp_Num-2);
	}
}