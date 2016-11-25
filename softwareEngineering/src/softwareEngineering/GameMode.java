package softwareEngineering;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public abstract class GameMode extends Thread implements GameInterface{
	protected AudioManagement am;			//음악관리 클래스 (음악 오픈, 음악 재생 등..)
	protected GroundManagement gm;		//지형관리 클래스 (지형의 움직임 등..)
	protected PlayerManagement pm;		//플레이어관리 클래스 (플레이어 조작, 플레이어 정보 등..)
	protected JFrame main_Frame; // 게임화면을 출력할 메인 화면
	protected BackGroundCanvas main_Pane; // 게임화면을 조작할 Container
	protected BGMPlayer bgm;
	protected String level;
	private JLabel lb_Health, lb_endMassage, lb_Score, lb_Speed;
	private JPanel end_Screen;
	private LoadingThread lt; // 로딩을 출력해주는 클래스
	private File music_File;
	private boolean init, end, isOpenFile, stop;
	private int score, gameSpeed;
	private String endMassage;
	private Color color_Massage;
	protected boolean complete;
	
	GameMode(JFrame frame, BackGroundCanvas main_Pane) {
		main_Frame = frame;
		this.main_Pane = main_Pane;
		gm = new GroundManagement(150, 37, main_Pane);
		am = new AudioManagement(main_Pane, gm.getMadeClass());
		lt = new LoadingThread();
		bgm = BGMPlayer.getBGM();
		lb_endMassage = new JLabel();
		lb_Score = new JLabel();
		lb_Score.setBounds(50, 50, 200, 50);
		lb_Score.setFont(new Font("돋음", Font.PLAIN, 30));
		lb_Score.setForeground(Color.RED);
		lb_Health = new JLabel();
		lb_Health.setBounds(50, 800, 100, 50);
		lb_Health.setFont(new Font("돋음", Font.PLAIN, 30));
		lb_Health.setForeground(Color.RED);
		this.main_Pane.add(lb_Health);
		this.main_Pane.add(lb_Score);
		stop = true;
		score = 0;
		gameSpeed = 10;
	}
	
	public void setFile(File file){		//음악파일 지정
		music_File = file;
		pm.setGroundManagement(gm);
		main_Pane.setVisible(true);
		lt.start();
		init = true;
		isOpenFile = true;
	}
	
	public KeyListener getKeyListener(){	//플레이어의 조작키 리턴
		return pm.getKeyListener();
	}
	
	public void setSpeed(int s){
		gameSpeed = 15 - s;
	}
	
	private void checkGround(){
		ArrayList<Ground> ground_Move_List = gm.getMoveList();
		for (int i = 0; i < ground_Move_List.size(); i++) {
			Ground temp_Ground = ground_Move_List.get(i);
			int groundX = temp_Ground.getX();
			int groundY = temp_Ground.getY();
			int playerX = pm.getInfo().getX();
			int playerY = pm.getInfo().getY();
			
			if(!(temp_Ground instanceof TrapGround)){
				temp_Ground.resetImage();
			}
			
			if (groundX > playerX + pm.getInfo().getScaleX()-30) {
				pm.setStand(false);
				temp_Ground.isStand = false;
				return;
			}
			else if (groundX + temp_Ground.getScaleX() > playerX+30) {
				int temp = groundY - (playerY + pm.getInfo().getScaleY());
				
				if (temp_Ground instanceof GoalGround){
					complete = true;
					return;
				}
				if (temp > -23 && temp < -5) {
					pm.setCurrentGround(temp_Ground);
					pm.setStand(true);
					if(temp_Ground.isScore())
						score += temp_Ground.getScore(level);
					temp_Ground.isStand = true;
					whenTrap(temp_Ground);
					return;
				} else{
					pm.setStand(false);
					temp_Ground.isStand = false;
				}
			}
		}
	}
	
	private void checkPlayer(){
		Float temp_health = pm.getHealth();
		lb_Health.setText(temp_health.toString());
		
	}
	
	private void setEndScreen(){
		end_Screen = new JPanel();
		end_Screen.setBackground(Color.BLACK);
		end_Screen.setOpaque(true);
		main_Frame.add(end_Screen);
		end_Screen.setVisible(true);
		
		lb_endMassage.setPreferredSize(new Dimension(270, 80));
		lb_endMassage.setFont(new Font("궁서", Font.ITALIC, 40));
		lb_endMassage.setLocation((main_Frame.getWidth()-lb_endMassage.getWidth())/2, 0);
		lb_endMassage.setHorizontalAlignment(JLabel.CENTER);
		end_Screen.add(lb_endMassage);
	}
	
	private void endSound(String s) {
		if(s.equals("die")){
			bgm.Play("die");
		}
		else if(s.equals("win")){
			bgm.Play("win");
		}
			
	}
	
	private boolean endScreen(boolean setEnd){
		this.endGame();
		end = false;
		main_Pane.setVisible(false);
		if(setEnd){
			setEndScreen();
			if(pm.isDead()){
				endMassage = "Death";
				color_Massage = Color.RED;
				endSound("die");
			}
			else if(complete){
				end_Screen.setBackground(Color.WHITE);
				endMassage = "Complete";
				color_Massage = Color.BLUE;
				endSound("win");
			}
		}
		lb_endMassage.setForeground(color_Massage);
		lb_endMassage.setText(endMassage);
		if(lb_endMassage.getY() > main_Frame.getHeight()/2.5){
			try {
				Thread.sleep(1000);
				JButton btn_restart, btn_reselect;
				btn_restart = new JButton("재시작");
				btn_reselect = new JButton("음악 재선택");
				btn_restart.setBounds(600, 50, 100, 50);
				btn_reselect.setBounds(750, 50, 130, 50);
				btn_restart.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent arg0) {
						main_Frame.remove(end_Screen);
						MainGUI main_Gui = MainGUI.getGUI();
						main_Gui.requestFocus();
						main_Gui.setFocusable(true);
						main_Gui.setFile(main_Gui.getFile(), level);
					}
					
				});
				btn_reselect.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						FirstGUI first_Gui = FirstGUI.getGUI();
						first_Gui.setVisible(true);
						main_Frame.remove(end_Screen);
						main_Frame.setVisible(false);
						
						class Lanking = new Lanking(score);
					}
					
				});	
				end_Screen.add(btn_restart);
				end_Screen.add(btn_reselect);
				end_Screen.repaint();
				end = true;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}else{
			lb_endMassage.setLocation(lb_endMassage.getX(), lb_endMassage.getY()+3);
		}
		
		return false;
	}

	@Override
	public void run() {
		boolean setEnd = true;
		
		while (!end) {
			if(gameSpeed > 0){
				try {
					Thread.sleep(gameSpeed);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			if(init){
				am.setMusic(music_File.toString());
				init = false;
			}
			if(pm.isDead() || complete){
				setEnd = endScreen(setEnd);
				continue;
			}
			if(!stop){
				lb_Score.setText("Score : "+score);
				pm.player();
				gm.ground();
				checkGround();
				checkMissile();
				checkPlayer();
				flyMissile();
			}
			main_Pane.repaint();
		}
	}

	@Override
	public void startGame() {
		if (!isOpenFile) {
			System.err.println("음악파일이 설정되지 않았습니다.");
			return;
		}
		am.audioPlay();
		main_Pane.add(pm.getInfo().getPlayerLabel());
		stop = false;
	}

	@Override
	public void waitGame() {
		am.audioWait();
		stop = true;
	}

	@Override
	public void reStart() {
		am.audioReplay();
		stop = false;
	}

	@Override
	public void endGame() {
		stop = true;
		end = true;
		main_Pane.remove(lb_Speed);
		main_Pane.remove(pm.getInfo().getPlayerLabel());
		main_Pane.remove(lb_Health);
		main_Pane.remove(lb_Score);
		am.audioStop();
		gm.close();
		ImageStore.close();
	}
	
	public abstract void whenTrap(Ground ground);
	public abstract void flyMissile();
	public abstract void checkMissile();	
	
	class LoadingThread extends Thread{
		private boolean complete;
		
		public LoadingThread(){
			complete = false;
		}
		
		private void loadComplete(){
			Float temp_num = gm.getMadeClass().getSpeed();
			lb_Speed = new JLabel("속도 = "+temp_num.toString());
			lb_Speed.setBounds(1250, 25, 250, 100);
			lb_Speed.setFont(new Font("돋음", Font.PLAIN, 50));
			lb_Speed.setForeground(Color.RED);
			try{
				Thread.sleep(500);
			}catch(InterruptedException e){}
			main_Pane.add(lb_Speed);
			main_Pane.setLoad(false);
			main_Pane.setState(true);
			main_Pane.repaint();
			startGame();
		}
		
		@Override
		public void run() {
			main_Pane.setState(false);
			main_Pane.setLoad(true);
			while(!complete){
				complete = am.isComplete();
				if(complete)
					loadComplete();
				main_Pane.repaint();
				try{
					Thread.sleep(100);
				}catch(InterruptedException e){
					e.printStackTrace();
				}
			}
		}
	}
}
