package softwareEngineering;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class PazangBackGround extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image tor, mer, torh, cur_Image;
	
	public PazangBackGround(){
		tor = new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/res/image/background/torbi_main.png")).getImage();
		mer = new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/res/image/background/merci_main.png")).getImage();
		torh = new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/res/image/background/torbihard_main.png")).getImage();
		cur_Image = mer;
		this.setOpaque(false);
	}
	
	public void setImage(String name){
		if(name.equals("mer"))
			cur_Image = mer;
		else if(name.equals("tor"))
			cur_Image = tor;
		else if(name.equals("torh"))
			cur_Image = torh;
		this.repaint();
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(cur_Image, 0, 0, this.getWidth(), this.getHeight(), null);
		super.paint(g);
	}
	
	
}
