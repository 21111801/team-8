package softwareEngineering;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JLabel;

public class PazangButton extends JLabel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image set, reset, cur_Image;
	private boolean selected;
	public static final int START = 0, OPTION = 1, RANK = 2, FIND = 3, EASY = 4, NORMAL = 5, HARD = 6, RESELECT = 7, RESUME = 8, EXIT = 9;
	
	public PazangButton(int type){
		this.setOpaque(false);
		selected = false;
		ImageStore is = ImageStore.getImageStore();
		switch(type){
		case START:
			set = is.getButtonImage("starta");
			reset = is.getButtonImage("start");
			break;
		case OPTION:
			set = is.getButtonImage("optiona");
			reset = is.getButtonImage("option");
			break;
		case RANK:
			set = is.getButtonImage("ranka");
			reset = is.getButtonImage("rank");
			break;
		case FIND:
			set = is.getButtonImage("finda");
			reset = is.getButtonImage("find");
			break;
		case EASY:
			set = is.getButtonImage("easya");
			reset = is.getButtonImage("easy");
			break;
		case NORMAL:
			set = is.getButtonImage("normala");
			reset = is.getButtonImage("normal");
			break;
		case HARD:
			set = is.getButtonImage("harda");
			reset = is.getButtonImage("hard");
			break;
		case RESELECT:
			set = is.getButtonImage("reselecta");
			reset = is.getButtonImage("reselect");
			break;
		case RESUME:
			set = is.getButtonImage("resumea");
			reset = is.getButtonImage("resume");
			break;
		case EXIT:
			set = is.getButtonImage("exita");
			reset = is.getButtonImage("exit");
			break;
		}
	}
	
	public void setSeleted(boolean s){
		selected = s;
		this.repaint();
	}

	@Override
	public void paint(Graphics g) {
		if(selected)
			cur_Image = set;
		else
			cur_Image = reset;
		g.drawImage(cur_Image, 0, 0, this.getWidth(), this.getHeight(), null);
	}
	
	
}
