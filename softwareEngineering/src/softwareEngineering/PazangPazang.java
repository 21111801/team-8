package softwareEngineering;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.*;

public class PazangPazang {

	public static void main(String[] args) {
		FirstGUI start_Gui = FirstGUI.getGUI();
		start_Gui.setVisible(true);
	}

}

class FirstGUI extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static FirstGUI gui = new FirstGUI();
	private static File file;
	private final static int WIDTH = 1500, HEIGHT = 1000;
	private MainGUI main_Gui;
	private JPanel pane_Option;
	private PazangBackGround pane_Center;
	private PazangButton btn_Start, btn_Find, btn_Option, btn_Rank, btn_Easy, btn_Normal, btn_Hard;
	private JButton btn_Exit;
	private JLabel lb_Title, lb_File;
	private String level;
	private ActMouse listener;
	private BGMPlayer bgm;
	
	@SuppressWarnings("serial")
	private FirstGUI(){
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		
		setResizable(false);
		setBounds((screen.width - WIDTH)/2, ((screen.height - HEIGHT)/2)-20, WIDTH, HEIGHT);
		setTitle("PazangPazang");
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		bgm = BGMPlayer.getBGM();
		file = new File("res/sample.mp3");
		pane_Center = new PazangBackGround();
		pane_Option = new JPanel();
		btn_Exit = new JButton("종료");
		btn_Start = new PazangButton(PazangButton.START);
		btn_Find = new PazangButton(PazangButton.FIND);
		btn_Rank = new PazangButton(PazangButton.RANK);
		btn_Option = new PazangButton(PazangButton.OPTION);
		lb_Title = new JLabel(){

			@Override
			protected void paintComponent(Graphics g) {
				Image i = new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/res/image/background/title.png")).getImage();
				g.drawImage(i, 0, 0, this.getWidth(), this.getHeight(), null);
			}
			
		};
		level = new String("Easy");
		lb_File = new JLabel();
		btn_Easy = new PazangButton(PazangButton.EASY);
		btn_Normal = new PazangButton(PazangButton.NORMAL);
		btn_Hard = new PazangButton(PazangButton.HARD);
		listener = new ActMouse();
		//객체 생성
		
		
		pane_Center.setSize(WIDTH, HEIGHT);
		pane_Option.setBackground(Color.BLACK);
		pane_Option.setOpaque(true);
		pane_Option.setLayout(null);
		pane_Center.setLayout(null);
		//panel 설정
		
		btn_Start.addMouseListener(listener);
		btn_Start.setBounds(200, 300, 300, 100);
		btn_Find.addMouseListener(listener);
		btn_Find.setBounds(200, 450, 300, 100);
		btn_Option.addMouseListener(listener);
		btn_Option.setBounds(200, 600, 300, 100);
		btn_Rank.addMouseListener(listener);
		btn_Rank.setBounds(200, 750, 300, 100);
		btn_Easy.setBounds((WIDTH-250)/2, 300, 250, 100);
		btn_Easy.addMouseListener(listener);
		btn_Normal.setBounds((WIDTH-250)/2, 430, 250, 100);
		btn_Normal.addMouseListener(listener);
		btn_Hard.setBounds((WIDTH-250)/2, 560, 250, 100);
		btn_Hard.addMouseListener(listener);
		btn_Exit.setBounds(WIDTH-200, HEIGHT-100, 100, 40);
		btn_Exit.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				pane_Center.setVisible(true);
				pane_Option.setVisible(false);
			}
			
		});
		//button 설정
		JLabel lb = new JLabel(){

			@Override
			protected void paintComponent(Graphics g) {
				Image i = new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/res/image/button/option.png")).getImage();
				g.drawImage(i, 0, 0, this.getWidth(), this.getHeight(), null);
			}
			
		};
		lb.setOpaque(false);
		lb.setBounds((WIDTH-250)/2, 100, 350, 150);
		lb_Title.setHorizontalAlignment(JLabel.CENTER);
		lb_Title.setFont(new Font("궁서", Font.ITALIC, 30));
		lb_Title.setForeground(Color.BLUE);
		lb_Title.setBounds((WIDTH - 1000)/2, -50, 1000, 400);
		lb_File.setText(file.toString());
		lb_File.setBounds(0, HEIGHT-100, WIDTH, 100);
		lb_File.setFont(new Font("바탕", Font.PLAIN, 30));
		lb_File.setForeground(Color.BLUE);
		lb_File.setOpaque(false);
		//label 설정
		
		add(pane_Center, BorderLayout.CENTER);
		add(pane_Option, BorderLayout.CENTER);
		pane_Option.setVisible(false);
		pane_Center.add(lb_Title);
		pane_Center.add(btn_Find);
		pane_Center.add(btn_Start);
		pane_Center.add(btn_Option);
		pane_Center.add(btn_Rank);
		pane_Center.add(lb_File);
		pane_Option.add(btn_Easy);
		pane_Option.add(btn_Normal);
		pane_Option.add(btn_Hard);
		pane_Option.add(btn_Exit);
		pane_Option.add(lb);
	}
	public static FirstGUI getGUI(){
		return gui;
	}
	
	private class ActMouse extends MouseAdapter{

		@Override
		public void mouseClicked(MouseEvent arg0) {
			bgm.Play("btnclick");
			if(arg0.getSource().equals(btn_Find)){
				JFileChooser fc = new JFileChooser();
				if(fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
					file = fc.getSelectedFile();
					lb_File.setText(file.toString());
				}else{
					JOptionPane.showMessageDialog(null, "취소되었습니다.");
				}
				
			}else if(arg0.getSource().equals(btn_Start)){
				if(file == null){
					JOptionPane.showMessageDialog(null, "음악 파일이 선택되지 않았습니다.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				System.out.println(file.getName());
				main_Gui = MainGUI.getGUI();
				main_Gui.setFile(file, level);
				main_Gui.setVisible(true);
				gui.setVisible(false);
				
			}else if(arg0.getSource().equals(btn_Option)){
				selectLevel(level);
				pane_Center.setVisible(false);
				pane_Option.setVisible(true);
			}else if(arg0.getSource().equals(btn_Easy)){
				level = "Easy";
				selectLevel(level);
				pane_Center.setImage("mer");
			}else if(arg0.getSource().equals(btn_Normal)){
				level = "Normal";
				selectLevel(level);
				pane_Center.setImage("tor");
			}else if(arg0.getSource().equals(btn_Hard)){
				level = "Hard";
				selectLevel(level);
				pane_Center.setImage("torh");
			}
		}
		
		private void selectLevel(String s){
			btn_Easy.setSeleted(false);
			btn_Normal.setSeleted(false);
			btn_Hard.setSeleted(false);
			switch(s){
			case "Easy":
				btn_Easy.setSeleted(true);
				break;
			case "Normal":
				btn_Normal.setSeleted(true);
				break;
			case "Hard":
				btn_Hard.setSeleted(true);
				break;
			}
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			bgm.Play("btnenter");
			if(arg0.getSource().equals(btn_Start))
				btn_Start.setSeleted(true);
			else if(arg0.getSource().equals(btn_Find))
				btn_Find.setSeleted(true);
			else if(arg0.getSource().equals(btn_Option))
				btn_Option.setSeleted(true);
			else if(arg0.getSource().equals(btn_Rank))
				btn_Rank.setSeleted(true);
			else if(arg0.getSource().equals(btn_Easy))
				btn_Easy.setSeleted(true);
			else if(arg0.getSource().equals(btn_Normal))
				btn_Normal.setSeleted(true);
			else if(arg0.getSource().equals(btn_Hard))
				btn_Hard.setSeleted(true);
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			if(arg0.getSource().equals(btn_Start))
				btn_Start.setSeleted(false);
			else if(arg0.getSource().equals(btn_Find))
				btn_Find.setSeleted(false);
			else if(arg0.getSource().equals(btn_Option))
				btn_Option.setSeleted(false);
			else if(arg0.getSource().equals(btn_Rank))
				btn_Rank.setSeleted(false);
			else if(arg0.getSource().equals(btn_Easy) && !level.equals("Easy"))
				btn_Easy.setSeleted(false);
			else if(arg0.getSource().equals(btn_Normal) && !level.equals("Normal"))
				btn_Normal.setSeleted(false);
			else if(arg0.getSource().equals(btn_Hard) && !level.equals("Hard"))
				btn_Hard.setSeleted(false);
		}
		
	}
}
