package project2;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {
	private ImageIcon logo;
	private int x, y;
	
	public ImagePanel() {
		x = 35;
		y = 35;
		logo = new ImageIcon("./proj2start/Twitter_logo2.png");
	}
	public void paintComponent(Graphics page) {
		super.paintComponent(page);
		logo.paintIcon(this, page, x, y);
	}
}
