package softwareEngineering;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class BackGroundCanvas extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int x, temp = 0, temp2 = 0, width;
	private static int map;
	private static BufferedImage backGround, backGroundGray;
	private Image loadImage[];
	private Graphics2D g;
	private boolean gray ,state, load;
	public static final int HANAMURA = 0, RIJANG = 1;
	
	public BackGroundCanvas(int screenWidth){
		this.setOpaque(false);
		if(loadImage == null){
			loadImage = new Image[4];
			loadImage[0] = new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/res/image/background/loading_1.png")).getImage();
			loadImage[1] = new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/res/image/background/loading_2.png")).getImage();
			loadImage[2] = new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/res/image/background/loading_3.png")).getImage();
			loadImage[3] = new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/res/image/background/loading_4.png")).getImage();
		}
		randomMap();
		x = 0;
		width = screenWidth;
	}
	
	public static int getWhere(){
		return map;
	}
	
	public void setLoad(boolean l){
		load = l;
	}
	
	public void randomMap(){
		try {
			map = (int)(Math.random()*2);
			if(map == HANAMURA)
				backGround = ImageIO.read(Toolkit.getDefaultToolkit().getClass().getResource("/res/image/map2/map.png"));
			else if(map == RIJANG)
				backGround = ImageIO.read(Toolkit.getDefaultToolkit().getClass().getResource("/res/image/map1/map.png"));
			backGroundGray = new BufferedImage(backGround.getWidth(), backGround.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
			g = backGroundGray.createGraphics();
			g.drawImage(backGround, 0, 0, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void paint(Graphics screen) {
		if(state){
			if(gray){
				screen.drawImage(backGroundGray, x, 0, this.getWidth(), this.getHeight(), null);
				screen.drawImage(backGroundGray, x+width, 0, this.getWidth(), this.getHeight(), null);
			}else {
				screen.drawImage(backGround, x, 0, this.getWidth(), this.getHeight(), null);
				screen.drawImage(backGround, x+width, 0, this.getWidth(), this.getHeight(), null);
			}
			if(!gray && temp % 5 == 0){
				x = (x-1)%width;
				temp = 0;
			}
			temp++;
		}else if(!state && load){
			screen.drawImage(loadImage[temp2], 0, 0, this.getWidth(), this.getHeight(), null);
			temp2 = (temp2 + 1) % 4;
		}
		super.paint(screen);
	}
	
	public void setState(boolean s){
		state = s;
	}
	
	public void turnBackGround(){
		gray = !gray;
	}
	
	public boolean isTurn(){
		return gray;
	}
}
