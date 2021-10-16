//Gabrielle Neafsey-Wroten

package project2;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame {
	public static void main(String[] args) {
		JFrame frame = new JFrame("Sentiment Analysis");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 500);
		
		frame.getContentPane().add(new MainPanel());
		
		frame.pack();
		frame.setVisible(true);
		}
}
