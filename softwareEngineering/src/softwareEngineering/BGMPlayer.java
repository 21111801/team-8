package softwareEngineering;

import java.awt.Toolkit;
import java.net.URL;
import java.util.HashMap;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.Line;

public class BGMPlayer {
	private static BGMPlayer bgm = new BGMPlayer();
	private boolean in_Complete;
	private static HashMap<String, Clip> set_Clip;
	public static final int TORBION = 0, MERCY = 1;
	
	private BGMPlayer(){
		set_Clip = new HashMap<String, Clip>();
		addClip("btnenter", Toolkit.getDefaultToolkit().getClass().getResource("/res/sound/btn_enter.wav"));
		addClip("btnclick", Toolkit.getDefaultToolkit().getClass().getResource("/res/sound/btn_click.wav"));
	}
	
	public void setVoice(int name){
		if(in_Complete){
			System.err.println("보이스 등록 해제");
			set_Clip.remove("die");
			set_Clip.remove("win");
		}
		URL url1 = null, url2 = null;
		if(name == TORBION){
			url1 = Toolkit.getDefaultToolkit().getClass().getResource("/res/sound/tor_die.wav");
			url2 = Toolkit.getDefaultToolkit().getClass().getResource("/res/sound/tor_win.wav");
		}else if(name == MERCY){
			url1 = Toolkit.getDefaultToolkit().getClass().getResource("/res/sound/mer_die.wav");
			url2 = Toolkit.getDefaultToolkit().getClass().getResource("/res/sound/mer_win.wav");
		}
		addClip("die", url1);
		addClip("win", url2);
		in_Complete = true;
	}
	
	public static BGMPlayer getBGM(){
		return bgm;
	}
	
	private void addClip(String key, URL url){
		try{
			Line.Info linfo = new Line.Info(Clip.class);
			Line line = AudioSystem.getLine(linfo);
			Clip clip1 = (Clip)line;
			AudioInputStream ais = AudioSystem.getAudioInputStream(url);
			clip1.open(ais);
			set_Clip.put(key, clip1);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void Play(String key){
		Clip clip = set_Clip.get(key);
		clip.stop();
		clip.setFramePosition(0);
		clip.start();
	}
}
