package softwareEngineering;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

class AudioManagement{		//음악 관리를 위한 클래스 (쓰레드로 관리하여 독립적인 재생 가능)
	private boolean cur_Audio; 
	private TimeThread tt;
	private MusicPlayer music;
	private JPanel main_Pane;
	private static JLabel jl;
	
	public AudioManagement(JPanel p, MadeGround mg){
		tt = new TimeThread(p);
		main_Pane = p;
		music = new MusicPlayer(main_Pane, mg);
	}
	
	public void setMusic(String path){
		cur_Audio = music.open(path);
	}
	
	public void audioPlay(){
		if(!cur_Audio) return;
		tt.start();
		music.start();
	}
	
	public void audioStop(){
		if(!cur_Audio) return;
		music.stopMusic();		//외부에서만 stop 가능
		tt.stopTime();
		main_Pane.remove(jl);
		cur_Audio = false;
	}
	
	public void audioWait(){
		music.waitMusic();
		tt.waitTime();
		
	}
	
	public void audioReplay(){
		music.interrupt();
		tt.interrupt();
	}
	
	public int getMusicSize(){
		return music.getSize();
	}
	
	public boolean isComplete(){
		return cur_Audio;
	}
	
	public static class TimeThread extends Thread{
		private static int end_Time;
		private int start_Time;
		private boolean stop, wait;
		private JPanel pane;
		
		public TimeThread(JPanel p){
			pane = p;
			start_Time = 0;
			jl = new JLabel();
			jl.setBounds(1150, 800, 200, 50);
			jl.setFont(new Font("돋음", Font.PLAIN, 30));
			jl.setForeground(Color.BLUE);
			jl.setVisible(false);
			stop = false;
			wait = false;
			pane.add(jl);
		}
		
		public void stopTime(){
			stop = true;
		}
		
		public static void setEndTime(int t){
			end_Time = t;
		}
		
		public void waitTime(){
			wait = true;
		}

		@Override
		public void run() {
			jl.setVisible(true);
			while(start_Time <= end_Time && !stop){
				String start_min, start_sec;
				String end_min, end_sec;
				start_min = checkTime(start_Time, "min");
				start_sec = checkTime(start_Time, "sec");
				end_min = checkTime(end_Time, "min");
				end_sec = checkTime(end_Time, "sec");
				jl.setText(start_min + ":" + start_sec + " / " + end_min + ":" + end_sec);
				start_Time++;
				try{
					if(wait){
						synchronized(this){
							this.wait();
						}
					}
					Thread.sleep(1000);
				}catch(InterruptedException e){
					wait = false;
				}
			}
		}
		
		private String checkTime(int t, String temp){
			switch(temp){
			case "min":
				if(t/60 < 10) return "0"+t/60;
				else return ""+t/60;
			case "sec":
				if(t%60 < 10) return "0"+t%60;
				else return ""+t%60;
			}
			return null;
		}
	}
	
}