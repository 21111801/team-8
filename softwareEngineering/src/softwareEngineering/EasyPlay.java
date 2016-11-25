package softwareEngineering;

import javax.swing.JFrame;

public class EasyPlay extends GameMode {

	EasyPlay(JFrame frame, BackGroundCanvas main_Pane) {
		super(frame, main_Pane);
		selectCharacter(frame, main_Pane);
		gm.getMadeClass().setTrapLevel(30);
		this.level = "Easy";
		
	}
	
	protected void selectCharacter(JFrame frame, BackGroundCanvas main_Pane){
		pm = new PlayerManagement(frame, main_Pane, PlayerImage.MERCY);
		bgm.setVoice(BGMPlayer.MERCY);
	}

	@Override
	public void flyMissile() {
		return;
	}
	
	@Override
	public void whenTrap(Ground ground){
		switch(ground.activeTrap()){
		case Ground.SAFTY:
			ground.setImage();
			return;
		case Ground.DAMAGE:
			
			return;
		case Ground.FALL:
			pm.setStand(false);
			return;
		case Ground.HOLD:
			return;
		}
	}

	@Override
	public void checkMissile() {
		return;
	}
}
