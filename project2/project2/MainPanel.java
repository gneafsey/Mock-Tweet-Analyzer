package project2;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import project1.TweetCollection;

public class MainPanel extends JPanel {
	private TweetCollection tc;
	private JTextField txtRemoveTweet;
	//-----------------------------------------------------------------
	// Sets up the panel, including the timer for the animation.
	//-----------------------------------------------------------------
	public MainPanel()
	{
		tc = new TweetCollection("./project1/testProcessed.txt");
		
		setPreferredSize (new Dimension(884, 582));
		setLayout(null);
		
		
		
		JLabel lblTweets = new JLabel("Tweets");
		lblTweets.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 24));
		lblTweets.setBounds(10, 11, 171, 43);
		add(lblTweets);
		
		JTextArea textArea = new JTextArea();
		textArea.setText(tc.toString());
		textArea.setBounds(10, 46, 332, 339);
		add(textArea);
		
		txtRemoveTweet = new JTextField();
		txtRemoveTweet.setText("Remove Tweet");
		txtRemoveTweet.setBounds(435, 64, 96, 20);
		add(txtRemoveTweet);
		txtRemoveTweet.setColumns(10);
		
	}
}
