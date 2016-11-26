package softwareEngineering;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.TextArea;
import java.awt.TextField;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import java.awt.Font;
import javax.swing.DropMode;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;

public class LankingUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LankingUI frame = new LankingUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LankingUI() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JTextPane textPane = new JTextPane();
		textPane.setBackground(SystemColor.menu);
		textPane.setEditable(false);
		textPane.setFont(new Font("Koverwatch", Font.ITALIC, 30));
		textPane.setText("\uC774\uB984\uC744 \uC785\uB825\uD558\uC138\uC694");
		textPane.setBounds(122, 44, 192, 31);
		textPane.setAlignmentX(JTextPane.CENTER_ALIGNMENT);
		panel.add(textPane);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("«‘√ ∑“µ∏øÚ", Font.PLAIN, 18));
		textField.setBounds(73, 109, 288, 38);
		panel.add(textField);
		textField.setColumns(10);
		
		JButton button = new JButton("\uD655\uC778");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String nowname;
				nowname = textField.getText();
				
			}
		});
		
		
		button.setFont(new Font("Koverwatch", Font.PLAIN, 24));
		button.setBounds(152, 175, 97, 23);
		panel.add(button);
	}
}
