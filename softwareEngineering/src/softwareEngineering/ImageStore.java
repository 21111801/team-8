package softwareEngineering;

import java.awt.Image;
import java.awt.Toolkit;
import java.util.HashMap;

import javax.swing.ImageIcon;

public class ImageStore{
	private static ImageStore is = new ImageStore();
	private static HashMap<String, Image> ground_Image_Map;
	private static HashMap<String, Image> missile_Image_Map;
	private static HashMap<String, Image> button_Image_Map;
	private static final int GROUND = 0, MISSILE = 1, BUTTON = 2;
	
	private ImageStore(){
		ground_Image_Map = new HashMap<String, Image>();
		missile_Image_Map = new HashMap<String, Image>();
		button_Image_Map = new HashMap<String, Image>();
	}

	public Image getGroundImage(String name) {
		if(!ground_Image_Map.containsKey(name))
			loadImage(name, GROUND);
		return ground_Image_Map.get(name);
	}
	
	public Image getMissileImage(String name){
		if(!missile_Image_Map.containsKey(name))
			loadImage(name, MISSILE);
		return missile_Image_Map.get(name);
	}
	
	public Image getButtonImage(String name){
		if(!button_Image_Map.containsKey(name))
			loadImage(name, BUTTON);
		return button_Image_Map.get(name);
	}
	
	public static ImageStore getImageStore(){
		return is;
	}
	
	public static void close(){
		ground_Image_Map.clear();
	}

	private void loadImage(String name, int type) {
		if(type == GROUND){
			String s1 = new String("/res/image/");
			String s2 = null;
			if(BackGroundCanvas.getWhere() == BackGroundCanvas.HANAMURA)
				s2 = s1+"map2/";
			else if(BackGroundCanvas.getWhere() == BackGroundCanvas.RIJANG)
				s2 = s1+"map1/";
			switch(name){
			case "start":
				ground_Image_Map.put(name, new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource(s2+"ground_start.png")).getImage());
				break;
			case "set":
				ground_Image_Map.put(name, new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource(s2+"ground_on.png")).getImage());
				break;
			case "reset":
				ground_Image_Map.put(name, new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource(s2+"ground_off.png")).getImage());
				break;
			case "trap":
				ground_Image_Map.put(name, new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource(s2+"trap_fall.png")).getImage());
				break;
			}
		}else if(type == MISSILE){
			switch(name){
			case "mercy":
				missile_Image_Map.put(name, new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/res/image/missile/missile_mercy.png")).getImage());
				break;
			case "para":
				missile_Image_Map.put(name, new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/res/image/missile/missile_para.png")).getImage());
				break;
			case "genzi":
				missile_Image_Map.put(name, new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/res/image/missile/missile_genzi.png")).getImage());
				break;
			}
		}else if(type == BUTTON){
			switch(name){
			case "start":
				button_Image_Map.put(name, new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/res/image/button/start.png")).getImage());
				break;
			case "starta":
				button_Image_Map.put(name, new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/res/image/button/starta.png")).getImage());
				break;
			case "rank":
				button_Image_Map.put(name, new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/res/image/button/rank.png")).getImage());
				break;
			case "ranka":
				button_Image_Map.put(name, new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/res/image/button/ranka.png")).getImage());
				break;
			case "find":
				button_Image_Map.put(name, new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/res/image/button/find.png")).getImage());
				break;
			case "finda":
				button_Image_Map.put(name, new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/res/image/button/finda.png")).getImage());
				break;
			case "option":
				button_Image_Map.put(name, new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/res/image/button/option.png")).getImage());
				break;
			case "optiona":
				button_Image_Map.put(name, new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/res/image/button/optiona.png")).getImage());
				break;
			case "easy":
				button_Image_Map.put(name, new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/res/image/button/easy.png")).getImage());
				break;
			case "easya":
				button_Image_Map.put(name, new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/res/image/button/easya.png")).getImage());
				break;
			case "normal":
				button_Image_Map.put(name, new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/res/image/button/normal.png")).getImage());
				break;
			case "normala":
				button_Image_Map.put(name, new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/res/image/button/normala.png")).getImage());
				break;
			case "hard":
				button_Image_Map.put(name, new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/res/image/button/hard.png")).getImage());
				break;
			case "harda":
				button_Image_Map.put(name, new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/res/image/button/harda.png")).getImage());
				break;
			case "exita":
				button_Image_Map.put(name, new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/res/image/button/option/exita.png")).getImage());
				break;
			case "exit":
				button_Image_Map.put(name, new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/res/image/button/option/exit.png")).getImage());
				break;
			case "reselecta":
				button_Image_Map.put(name, new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/res/image/button/option/reselecta.png")).getImage());
				break;
			case "reselect":
				button_Image_Map.put(name, new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/res/image/button/option/reselect.png")).getImage());
				break;
			case "resumea":
				button_Image_Map.put(name, new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/res/image/button/option/resumea.png")).getImage());
				break;
			case "resume":
				button_Image_Map.put(name, new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/res/image/button/option/resume.png")).getImage());
				break;
				
			}
		}
	}

}
