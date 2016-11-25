package softwareEngineering;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;

class MainGUI extends JFrame implements KeyListener{		//전반적인 UI 클래스
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static MainGUI gui = new MainGUI();
	private PazangButton btn_ReSelect, btn_End, btn_Resume;
	private JPanel pane_Menu;
	private BackGroundCanvas pane_Screen;
	private JTextField text_File;
	private JFrame frame = this;
	private ActMouse listener;
	private GameMode gm;
	private JSlider slider;
	private boolean init;
	private File file;
	private BGMPlayer bgm;
	private final static int WIDTH = 1500, HEIGHT = 1000;
	
	private MainGUI(){
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();		//현재 화면 정보 획득
		setBounds((screen.width - WIDTH)/2, (screen.height - HEIGHT)/2, WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("PazangPazang");
		setLayout(new BorderLayout());
		setResizable(false);
		requestFocus();
		setFocusable(true);
		init = false;
		bgm = BGMPlayer.getBGM();
		//Frame 구성
		
		btn_Resume = new PazangButton(PazangButton.RESUME);
		btn_ReSelect = new PazangButton(PazangButton.RESELECT);
		btn_End = new PazangButton(PazangButton.EXIT);
		pane_Menu = new JPanel();
		text_File = new JTextField();
		slider = new JSlider(0, 10, 5);
		//UI 객체 생성
		
		listener = new ActMouse();
		
		btn_Resume.addMouseListener(listener);
		btn_ReSelect.addMouseListener(listener);
		btn_End.addMouseListener(listener);
		//버튼 이벤트 추가
		
		text_File.setEditable(false);
		pane_Menu.setLayout(null);
		//UI 옵션 설정
		
		add(text_File, "South");
		//Frame에 UI객체 추가
		
	}
	
	public void setFile(File f, String level){
		file = f;
		text_File.setText(f.toString());
		setScreen();
		setLevel(level);
		gm.setFile(f);
		gm.start();
		addKeyListener(gm.getKeyListener());
		if(!init){
			addKeyListener(this);
			init = true;
		}
	}
	
	public File getFile(){
		return file;
	}
	
	private void setLevel(String level){
		if(level.equals("Easy")){
			gm = new EasyPlay(frame, pane_Screen);
		}else if(level.equals("Normal")){
			gm = new NormalPlay(frame, pane_Screen);
		}else if(level.equals("Hard")){
			gm = new HardPlay(frame, pane_Screen);
		}
	}
	
	public static MainGUI getGUI(){
		return gui;
	}
	
	private void setScreen(){
		if(pane_Screen == null){
			pane_Screen = new BackGroundCanvas(frame.getWidth()-7);
			pane_Screen.setLayout(null);
			add(pane_Screen, "Center");
			frame.repaint();
			pane_Screen.repaint();
		}
		pane_Screen.randomMap();
		setMenu();
	}
	
	private void setMenu(){
		slider.setMajorTickSpacing(1);
		slider.setPaintTicks(true);
		slider.setLabelTable(slider.createStandardLabels(1));
		slider.setPaintLabels(true);
		slider.setSnapToTicks(true);
		slider.setBackground(Color.BLACK);
		slider.setForeground(Color.WHITE);
		pane_Menu.setSize(frame.getWidth()/3, frame.getHeight()/2);
		pane_Menu.setLocation(frame.getWidth()/2 - pane_Menu.getWidth()/2, frame.getHeight()/2 - pane_Menu.getHeight()/2);
		pane_Menu.setBackground(Color.BLACK);
		pane_Menu.setOpaque(true);
		pane_Menu.setVisible(false);
		
		@SuppressWarnings("serial")
		JLabel text_lb = new JLabel(){

			@Override
			protected void paintComponent(Graphics g) {
				Image i = new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/res/image/button/option/speed.png")).getImage();
				g.drawImage(i, 0, 0, this.getWidth(), this.getHeight(), null);
			}
			
		};
		text_lb.setOpaque(false);
		text_lb.setFont(new Font("바탕", Font.BOLD, 30));
		text_lb.setForeground(Color.RED);
		text_lb.setBounds(20, 250, 100, 50);
		
		pane_Screen.add(pane_Menu);
		btn_Resume.setBounds(20, 50, 100, 40);
		pane_Menu.add(btn_Resume);
		btn_ReSelect.setBounds(200, 50, 100, 40);
		pane_Menu.add(btn_ReSelect);
		btn_End.setBounds(380, 50, 100, 40);
		pane_Menu.add(btn_End);
		slider.setBounds(30, 300, 450, 70);
		pane_Menu.add(slider);
		pane_Menu.add(text_lb);
	}
	
	private class ActMouse extends MouseAdapter{

		@Override
		public void mouseClicked(MouseEvent arg0) {
			if(arg0.getSource().equals(btn_ReSelect)){
				gm.reStart();
				gm.endGame();
				FirstGUI first_Gui = FirstGUI.getGUI();
				first_Gui.setVisible(true);
				pane_Screen.setVisible(false);
				pane_Screen.turnBackGround();
				frame.setVisible(false);
				
			} else if(arg0.getSource().equals(btn_End)){
				gm.endGame();
				FirstGUI first_Gui = FirstGUI.getGUI();
				first_Gui.dispose();
				frame.dispose();
				System.exit(0);
			} else if(arg0.getSource().equals(btn_Resume)){
				pane_Screen.turnBackGround();
				gm.reStart();
				gm.setSpeed(slider.getValue());
				pane_Menu.setVisible(false);
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			bgm.Play("btnenter");
			if(e.getSource().equals(btn_ReSelect))
				btn_ReSelect.setSeleted(true);
			else if(e.getSource().equals(btn_End))
				btn_End.setSeleted(true);
			else if(e.getSource().equals(btn_Resume))
				btn_Resume.setSeleted(true);
		}

		@Override
		public void mouseExited(MouseEvent e) {
			if(e.getSource().equals(btn_ReSelect))
				btn_ReSelect.setSeleted(false);
			else if(e.getSource().equals(btn_End))
				btn_End.setSeleted(false);
			else if(e.getSource().equals(btn_Resume))
				btn_Resume.setSeleted(false);
		}
		
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		if(arg0.getKeyCode() == KeyEvent.VK_ESCAPE){
			pane_Screen.turnBackGround();
			if(pane_Screen.isTurn()){		//일시정지일때
				gm.waitGame();
				pane_Menu.setVisible(true);
			} else {						//다시 재생
				gm.reStart();
				gm.setSpeed(slider.getValue());
				pane_Menu.setVisible(false);
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}