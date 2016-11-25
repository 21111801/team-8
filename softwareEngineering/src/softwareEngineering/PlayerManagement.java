package softwareEngineering;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class PlayerManagement implements KeyListener {
	private JFrame frame;
	private PlayerInfo player_Info;
	private GroundManagement gm;
	private Ground cur_Ground;
	private int moveSpeed = 5, jumpSpeed = 12, dir, jump_Num, temp_Dir;
	private boolean jumping, stand, fall, hard, easy, glide, dead;
	private float temp_Speed, temp_JumpSpeed;
	
	public PlayerManagement(JFrame f, JPanel p, int type) {
		player_Info = new PlayerInfo(5, 5, type);
		if(type == PlayerImage.MERCY)
			easy = true;
		frame = f;
		init();
	}

	private void init() {
		glide = false;
		jumping = false;
		fall = false;
		stand = false;
		dir = 0;
		jump_Num = 1;
		temp_Speed = 0;
		temp_JumpSpeed = 0;
	}
	
	private void move() {
		int speed = 0;
		if(dir != 0){
			if(temp_Dir != dir)
				temp_Speed = 0;
			temp_Dir = dir;
			if(temp_Speed < (moveSpeed*9)/10)
				temp_Speed = temp_Speed + (float)0.4;
			else
				temp_Speed = temp_Speed + (float)0.8;
			if(temp_Speed > moveSpeed) temp_Speed = moveSpeed;
		}
		else {
			if(!stand){
				if(temp_Speed < (moveSpeed*9)/10)
					temp_Speed = temp_Speed - (float)0.1;
				else
					temp_Speed = temp_Speed - (float)0.3;
			}else{
				if(temp_Speed < (moveSpeed*9)/10)
					temp_Speed = temp_Speed - (float)0.5;
				else
					temp_Speed = temp_Speed - (float)1.0;
			}
			if(temp_Speed < 0){
				temp_Speed = 0;
				temp_Dir = 0;
			}
		}
		switch (temp_Dir) {
		case 0:
			return;
		case 1:
			if (stand)
				speed = -(int) (temp_Speed + Ground.getSpeed()/1.5);
			else{
				if(temp_Speed == 0) temp_Speed = -1;
				speed = -(int)(temp_Speed+1);
			}
			break;
		case 2:
			if (stand)
				speed = (int) (temp_Speed + Ground.getSpeed());
			else
				speed = (int)(temp_Speed);
			break;
		}
		player_Info.setX(player_Info.getX() + speed);
	}

	private void jump() {
		if(jump_Num == 0){
			jumping = false;
			return;
		}
		temp_JumpSpeed = jumpSpeed;
		jump_Num--;
		
	}
	
	public void bonusJump(){
		temp_JumpSpeed = jumpSpeed;
	}

	private void gravity() {
		if (player_Info.getY() + player_Info.getScaleY()/2 > frame.getHeight()){
			fall = true;
			return;
		}
		if(temp_JumpSpeed <= 0) jumping = false;
		if(!jumping && stand){
			jump_Num = 2;
			temp_JumpSpeed = 0;
			player_Info.setX((int) (player_Info.getX() - Ground.getSpeed()));
			if(!(cur_Ground instanceof StartGround) && hard){
				player_Info.setY((int) (player_Info.getY() - gm.getUpSpeed()));
			}
		}else if(!stand && jump_Num == 2){
			jump_Num = 1;
			temp_JumpSpeed = 0;
		}
		
		player_Info.setY(player_Info.getY() - (int)temp_JumpSpeed);
		if(glide && temp_JumpSpeed < 0){
			temp_JumpSpeed = -2;
			player_Info.getPlayerLabel().setFly(true);
		}else if(stand && glide){
			player_Info.getPlayerLabel().setFly(false);
		}
		else{
			temp_JumpSpeed = temp_JumpSpeed - (float)0.3;
			player_Info.getPlayerLabel().setFly(false);
		}
		
		if(temp_JumpSpeed < -11) temp_JumpSpeed = -11;
	}
	
	public void setHard(boolean h){
		hard = h;
	}
	
	public void setCurrentGround(Ground g){
		cur_Ground = g;
	}
	
	public void setStand(boolean s){
		stand = s;
	}

	public void setGroundManagement(GroundManagement gm) {
		this.gm = gm;
	}
	
	public KeyListener getKeyListener(){
		return this;
	}
	
	public PlayerInfo getInfo(){
		return player_Info;
	}
	
	public void setLocation(Dimension d){
		player_Info.setX(d.width);
		player_Info.setY(d.height);
	}

	private void checkLocate() {
		if (player_Info.getX() < 0)
			player_Info.setX(0);
		else if (player_Info.getX() > 1395 + player_Info.getScaleX())
			player_Info.setX(1445);
	}

	private void playerFall() {
		player_Info.setHealth(-30);
		if(player_Info.getHealth() <= 0){
			player_Info.getPlayerLabel().setVisible(false);
			dead = true;
			return;
		}
		player_Info.setPoint(0, 0);
		init();
		Ground temp_Ground;
		for(int i = 2; i<gm.getMoveList().size(); i++){
			temp_Ground = gm.getMoveList().get(i);
			if(!(temp_Ground instanceof TrapGround)){
				player_Info.setPoint(temp_Ground.getX(), temp_Ground.getY()-(player_Info.getScaleY())+10);
				break;
			}
		}
		fall = false;
	}
	
	private void checkGroundAdd(){
		if(gm.isAdd()){
			player_Info.setHealth((float)0.3);
			gm.checkAdd();
		}
	}
	
	public boolean isFall(){
		return fall;
	}

	public boolean isDead(){
		return dead;
	}
	
	public float getHealth(){
		return player_Info.getHealth();
	}
	
	public void addHealth(float h){
		if(player_Info.getHealth() + h > 100){
			player_Info.setHealth(100);
			return;
		}
		else if(player_Info.getHealth() + h <= 0){
			dead = true;
			return;
		}
		player_Info.setHealth(h);
	}
	
	public void player(){
		move();
		gravity();
		checkLocate();
		checkGroundAdd();
		if(dir != 0){
			PlayerImage pi = player_Info.getPlayerLabel();
			pi.setDir(dir);
			pi.repaint();
		}
		if (fall)
			playerFall();
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		if (arg0.getKeyCode() == KeyEvent.VK_SPACE) {
			if(!glide && jump_Num > 0){
				jumping = true;
				jump();
				try{
				Thread.sleep(1);
				}catch(InterruptedException e){
					e.printStackTrace();
				}
			}
			if(easy)
				glide = true;
		}
		if (arg0.getKeyCode() == KeyEvent.VK_LEFT) {
			dir = 1;
			player_Info.setStand(false);
		} else if (arg0.getKeyCode() == KeyEvent.VK_RIGHT) {
			dir = 2;
			player_Info.setStand(false);
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		if (arg0.getKeyCode() == KeyEvent.VK_LEFT || arg0.getKeyCode() == KeyEvent.VK_RIGHT){
			dir = 0;
			player_Info.setStand(true);
		}
		if(arg0.getKeyCode() == KeyEvent.VK_SPACE){
			glide = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}
}
