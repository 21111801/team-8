package softwareEngineering;

import java.util.ArrayList;

import javax.swing.JFrame;

public class NormalPlay extends EasyPlay{
	protected ArrayList<Missile> missile_List = new ArrayList<Missile>();
	private boolean wait_Missile;

	NormalPlay(JFrame frame, BackGroundCanvas main_Pane) {
		super(frame, main_Pane);
		wait_Missile = false;
		this.level = "Normal";
	}

	@Override
	protected void selectCharacter(JFrame frame, BackGroundCanvas main_Pane) {
		pm = new PlayerManagement(frame, main_Pane, PlayerImage.TORBION);
		bgm.setVoice(BGMPlayer.TORBION);
	}

	@Override
	public void flyMissile() {
		if(wait_Missile) return;
		int random = (int)(Math.random()*100);
		if(random == 0)
			create_Missile();
		move_Missile();
	}

	private void create_Missile(){
		int x = main_Frame.getWidth();
		int y = (int)(Math.random()*main_Frame.getHeight());
		Missile temp_Missile = new Missile(x,y, (int)(Math.random()*3));
		this.main_Pane.add(temp_Missile.getLabel());
		missile_List.add(temp_Missile);
	}
	
	private void delete_Missile(Missile m){
		main_Frame.remove(m.getLabel());
		m.getLabel().setVisible(false);
		missile_List.remove(m);
	}
	
	
	
	private void move_Missile(){
		for(int i = 0; i<missile_List.size(); i++){
			Missile temp_Missile = missile_List.get(i);
			temp_Missile.setX(temp_Missile.getX() - temp_Missile.getSpeed());
			
			if(temp_Missile.getX() < -temp_Missile.getWidth()){
				delete_Missile(temp_Missile);
			}
		}
	}

	@Override
	public void endGame() {
		super.endGame();
		for(int i = 0; i<missile_List.size(); i++){
			this.main_Pane.remove(missile_List.get(i).getLabel());
		}
	}

	@Override
	public void checkMissile() {
		for (int i = 0; i < missile_List.size(); i++) {
			Missile temp_Missile = missile_List.get(i);
			int missileX = temp_Missile.getX();
			int missileY = temp_Missile.getY();
			int playerX = pm.getInfo().getX();
			int playerY = pm.getInfo().getY();
			
			if (missileX > playerX + pm.getInfo().getScaleX() - 30) {
				return;
			}
			else if (missileX + temp_Missile.getWidth() > playerX + 30) {
				int y1 = missileY;
				int y2 = missileY+temp_Missile.getHeight();
				if(playerY + pm.getInfo().getScaleY() < y1)
					return;
				else if(playerY > y2)
					return;
				else{
					effectMissile(temp_Missile);
					return;
				}
			}
		}
	}
	
	private void effectMissile(Missile missile){
		switch(missile.getType()){
		case Missile.MERCY:
			pm.addHealth(5);
			break;
		case Missile.PARA:
			pm.addHealth(-5);
			break;
		case Missile.GENZI:
			pm.bonusJump();
			break;
		}
		delete_Missile(missile);
	}

	@Override
	public void waitGame() {
		super.waitGame();
		wait_Missile = true;
	}

	@Override
	public void reStart() {
		super.reStart();
		wait_Missile = false;
	}
	
	
}
