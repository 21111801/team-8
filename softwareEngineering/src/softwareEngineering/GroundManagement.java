package softwareEngineering;

import java.util.ArrayList;

import javax.swing.JPanel;

public class GroundManagement{
	private MadeGround made_Ground;
	private ArrayList<Ground> ground_Move_List;
	private boolean hard, move_Complete = true, check_Add;
	private int upSpeed;
	private JPanel main_Pane;
	
	public GroundManagement(int width, int height, JPanel pane){
		ground_Move_List = new ArrayList<Ground>();
		made_Ground = new MadeGround(width, height, this);
		this.main_Pane = pane;
		upSpeed = (int)(Math.random()*2);
		if(upSpeed == 0)
			upSpeed = -1;
	}
	
	public void ground(){
		moveGround();
		if(hard)
			upGround();
	}

	private void upGround(){
		move_Complete = false;
		for(int i = 0; i<ground_Move_List.size(); i++){
			Ground temp_Ground = ground_Move_List.get(i);
			
			if(temp_Ground instanceof StartGround)
				continue;
			
			temp_Ground.setY((int)(temp_Ground.getY()-upSpeed));
		}
		move_Complete = true;
	}
	
	public boolean isMoved(){
		return move_Complete;
	}
	
	public int getUpSpeed(){
		return upSpeed;
	}
	
	public void setHard(boolean h){
		hard = h;
		made_Ground.setHard(hard);
	}
	
	public void checkAdd(){
		check_Add = false;
	}
	
	public boolean isAdd(){
		return check_Add;
	}
	
	public MadeGround getMadeClass(){
		return made_Ground;
	}
	
	public ArrayList<Ground> getMoveList(){
		return ground_Move_List;
	}
	
	public int getScaleX(){
		return made_Ground.getScaleX();
	}
	
	public int getScaleY(){
		return made_Ground.getScaleY();
	}
	
	public void close(){
		for(int i = 0; i<ground_Move_List.size(); i++){
			main_Pane.remove(ground_Move_List.get(i).getLabel());
		}
		ground_Move_List.clear();
		made_Ground.getGrounds().clear();
	}
	
	public void addMove(Ground ground){
		ground_Move_List.add(ground);
		check_Add = true;
	}

	private void moveGround(){
		move_Complete = false;
		for(int i = 0; i<ground_Move_List.size(); i++){
			Ground temp_Ground = ground_Move_List.get(i);
			temp_Ground.setX((int)(temp_Ground.getX()-Ground.getSpeed()));
			
			if(temp_Ground.getX() < -temp_Ground.getScaleX()){
				ground_Move_List.remove(i);
				main_Pane.remove(temp_Ground.getLabel());
				if((int)(Math.random()*2) == 0)
					upSpeed = 1;
				else
					upSpeed = -1;
				return;
			}
		}
		move_Complete = true;
	}
	
}
