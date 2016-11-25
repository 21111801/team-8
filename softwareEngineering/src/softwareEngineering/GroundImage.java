package softwareEngineering;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JLabel;

public class GroundImage extends JLabel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image cur_Image;
	private ImageStore imageStore;
	
	public GroundImage(int width, int height){
		super();
		imageStore = ImageStore.getImageStore();
		this.setSize(width, height);
		initGround();
	}
	
	private void initGround(){
		this.setOpaque(false);
		cur_Image = imageStore.getGroundImage("reset");
	}
	
	public JLabel getLabel(){
		return this;
	}

	public void setImage(String name) {
		cur_Image = imageStore.getGroundImage(name);
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(cur_Image, 0, 0, this.getWidth(), this.getHeight(), null);
		super.paint(g);
	}
}
