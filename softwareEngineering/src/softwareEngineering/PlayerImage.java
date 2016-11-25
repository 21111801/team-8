package softwareEngineering;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class PlayerImage extends JLabel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image cur_Image, move_Left, move_Right, stand_Left, stand_Right, fly_Left, fly_Right;
	private int dir = 0;		//1 = left, 2 = right
	private int loc_image = 2, time = 0;
	private boolean forwarding_right, stand, flying;
	public static final int TORBION = 0, MERCY = 1;
	
	public PlayerImage(int scaleX, int scaleY, int type){
		loadImage(type);
		cur_Image = stand_Right;
		forwarding_right = true;
		stand = true;
		this.setOpaque(false);
		this.setSize(scaleX, scaleY);
	}
	
	private void loadImage(int type){
		if(type == TORBION){
		move_Left = new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/res/image/character/torbion/torbion_left.png")).getImage();
		move_Right = new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/res/image/character/torbion/torbion_right.png")).getImage();
		stand_Left = new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/res/image/character/torbion/torbion_stand_left.png")).getImage();
		stand_Right = new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/res/image/character/torbion/torbion_stand_right.png")).getImage();
		fly_Right = null;
		fly_Left = null;
		
		}else if(type == MERCY){
		move_Left = new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/res/image/character/mercy/mercy_left.png")).getImage();
		move_Right = new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/res/image/character/mercy/mercy_right.png")).getImage();
		stand_Left = new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/res/image/character/mercy/mercy_stand_left.png")).getImage();
		stand_Right = new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/res/image/character/mercy/mercy_stand_right.png")).getImage();
		fly_Left = new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/res/image/character/mercy/mercy_fly_left.png")).getImage();
		fly_Right = new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/res/image/character/mercy/mercy_fly_right.png")).getImage();
		
		}
	}
	
	public void setStand(boolean s){
		stand = s;
	}
	
	public void setFly(boolean f){
		flying = f;
	}
	
	public void setDir(int dir){
		this.dir = dir;
	}
	
	@Override
	public void paint(Graphics g) {
		if(time > 10){
			time = 0;
			if(forwarding_right){
				loc_image++;
				if(loc_image == 4)
					forwarding_right = false;
			}else if(!forwarding_right){
				loc_image--;
				if(loc_image == 0)
					forwarding_right = true;
			}
		}else
			time++;
		
		if(flying){
			if(dir == 1)
				g.drawImage(fly_Left, 0, 0, this.getWidth(), this.getHeight(), null);
			else
				g.drawImage(fly_Right, 0, 0, this.getWidth(), this.getHeight(), null);
		}else if(stand){
			if(dir == 1)
				g.drawImage(stand_Left, 0, 0, this.getWidth(), this.getHeight(), null);
			else
				g.drawImage(stand_Right, 0, 0, this.getWidth(), this.getHeight(), null);
		}else{
			if(dir == 1){
				cur_Image = move_Left;
			}else if(dir == 2){
				cur_Image = move_Right;
			}
			g.drawImage(cur_Image, 0, 0, this.getWidth(), this.getHeight(), (cur_Image.getWidth(null)/5)*loc_image, 0, (cur_Image.getWidth(null)/5)*(loc_image+1), cur_Image.getHeight(null), null);
		}
		super.paint(g);
	}

}
