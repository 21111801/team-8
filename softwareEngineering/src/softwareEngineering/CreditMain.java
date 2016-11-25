package softwareEngineering;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CreditMain{

	public static void main(String[] args) {
		GUI gui = new GUI();
		gui.setVisible(true);
		Credit cd = new Credit(gui);
		cd.start();
	}

}

class GUI extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel jp;
	private ArrayList<JLabel> jl_List;
	private JLabel jl;
	
	public GUI(){
		this.setTitle("CREDIT");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(400, 100, 500, 500);
		jl_List = new ArrayList<JLabel>();
		
		jp = new JPanel();
		jp.setLayout(null);
		jp.setBounds(0, 0, this.getWidth(), this.getHeight());
		
		setList();
		
		this.add(jp, BorderLayout.CENTER);
		jp.setBackground(Color.BLACK);
	}
	
	public JPanel getPane(){
		return jp;
	}
	
	public JLabel getLabel(){
		return jl;
	}
	
	private void setList(){
		String s[] = new String[6];
		s[0] = "¸¸µç »ç¶÷µé";
		s[1] = "±è°æÇÑ - ";
		s[2] = "ÀÌµ¿Çö - ";
		s[3] = "¹ÚÀçÀÌ - ";
		s[4] = "±èÅÂÇå - ";
		s[5] = "±è³ªÀº - ";
		
		for(int i = 0; i<s.length; i++){
			JLabel temp_jl = makeLabel(s[i], (this.getWidth() - 100)/2, jp.getHeight()+60*i, 100, 50);
			jl_List.add(temp_jl);
			jp.add(temp_jl);
		}
	}
	
	private JLabel makeLabel(String text, int x, int y, int width, int height){
		JLabel temp_jl= new JLabel(text);
		temp_jl.setSize(new Dimension(width, height));
		temp_jl.setLocation(x, y);
		temp_jl.setBounds(temp_jl.getX(), temp_jl.getY(), width, height);
		temp_jl.setForeground(Color.WHITE);
		temp_jl.setFont(new Font("¹ÙÅÁ", Font.BOLD, 15));
		return temp_jl;
	}
	
	public ArrayList<JLabel> getList(){
		return jl_List;
	}
}

class Credit extends Thread{
	private GUI gui;
	
	public Credit(GUI gui){
		this.gui = gui;
	}

	@Override
	public void run() {
		
		while(true){
			try{
			Thread.sleep(10);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
			ArrayList<JLabel> list = gui.getList();
			
			for(int i = 0; i<list.size(); i++){
				JLabel temp_jl = list.get(i);
				temp_jl.setLocation(temp_jl.getX(), temp_jl.getY()-1);
			}
			gui.getPane().repaint();
		}
	}
	
}
