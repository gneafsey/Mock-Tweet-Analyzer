package project2;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import project1.TweetCollection;

public class MainPanel extends JPanel {
	private TweetCollection tc;
	private JTextField txtRemoveTweet;
	private ImageIcon logo;
	//-----------------------------------------------------------------
	// Sets up the panel, including the timer for the animation.
	//-----------------------------------------------------------------
	public MainPanel()
	{
		//logo = new ImageIcon(this.getClass().getResource(".project2/Twitter_logo2.png"));
		
		tc = new TweetCollection("./project1/testProcessed.txt");
		
		setPreferredSize (new Dimension(884, 582));
		setLayout(null);
		
		
		
		JLabel lblTweets = new JLabel("Tweets");
		lblTweets.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 24));
		lblTweets.setBounds(20, 154, 171, 43);
		add(lblTweets);
		
		JTextArea textArea = new JTextArea();
		textArea.setText(tc.toString());
		textArea.setBounds(20, 208, 332, 339);
		add(textArea);
		
		txtRemoveTweet = new JTextField();
		txtRemoveTweet.setText("Remove Tweet");
		txtRemoveTweet.setBounds(435, 64, 96, 20);
		add(txtRemoveTweet);
		txtRemoveTweet.setColumns(10);
		
	}
//	public void paintComponent(Graphics page) {
//		super.paintComponent(page);
//		logo.paintIcon(this, page, 35, 35);
//	}
}
