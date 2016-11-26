import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import softwareEngineering.Lanking;

import java.awt.Panel;
import java.awt.TextField;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.awt.Font;
import javax.swing.JTextPane;
import java.awt.SystemColor;
import java.awt.List;

public class LankingPrintUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LankingPrintUI frame = new LankingPrintUI();
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
	public LankingPrintUI() {
		
		int[] savepoint = new int[10];
		String[] savename = new String[10];
		int[] savelank = new int[10];
		int i=0;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 455);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Panel panel = new Panel();
		panel.setBounds(0, 0, 434, 416);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(186, 10, 39, 31);
		textPane.setEditable(false);
		textPane.setBackground(SystemColor.menu);
		textPane.setFont(new Font("Koverwatch", Font.PLAIN, 25));
		textPane.setText("\uB7AD\uD0B9");
		panel.add(textPane);
		
		List list = new List();
		list.setFont(new Font("«‘√ ∑“µ∏øÚ", Font.PLAIN, 20));
		list.setBounds(10, 47, 414, 359);
		
		Scanner scan = null;
		try {
			scan = new Scanner(new File("Lanking.dat"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		while (scan.hasNext()) {
			list.add(scan.next() + " " + Integer.toString(scan.nextInt()) + " " + Integer.toString(scan.nextInt()));
		}	
		panel.add(list);
		
	}
}
