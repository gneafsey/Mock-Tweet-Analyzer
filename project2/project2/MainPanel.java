package project2;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import project1.Tweet;
import project1.TweetCollection;

public class MainPanel extends JPanel {
	private TweetCollection tc;
	private JTextField userNameField;
	private JTextField tweetIdField;
	private JComboBox comboBox;
	private JLabel lblTweetId;
	private JLabel lblTweetData;
	private JButton btnFindById;
	private JButton btnFindByUsername;
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
		lblTweets.setBounds(20, 156, 171, 45);
		add(lblTweets);
		
		JTextArea textArea = new JTextArea();
		textArea.setText(tc.toString());
		textArea.setBounds(10, 206, 332, 340);
		add(textArea);
		
		JLabel logo = new JLabel("");
		logo.setIcon(new ImageIcon(MainPanel.class.getResource("/project2/Twitter-xxl.png")));
		logo.setBounds(305, 11, 238, 173);
		add(logo);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUsername.setBounds(364, 209, 71, 14);
		add(lblUsername);
		
		userNameField = new JTextField();
		userNameField.setBounds(445, 206, 147, 20);
		add(userNameField);
		userNameField.setColumns(10);
		
		comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = (String)comboBox.getSelectedItem();
				Tweet t = tc.getTweet(Long.parseLong(id));
				lblTweetData.setText("Tweet: "+t.toString());
			}
		});
		comboBox.setBounds(445, 271, 147, 22);
		add(comboBox);
		
		btnFindByUsername = new JButton("Find By Username");
		btnFindByUsername.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Long> ids = tc.idsByUser(userNameField.getText());
				String[] idsStr = new String[ids.size()];
				int index = 0;
				for (Long long1 : ids) {
					idsStr[index] = long1.toString();
					index++;
				}
				comboBox.setModel(new DefaultComboBoxModel(idsStr));
				if (idsStr.length > 0) {
					Tweet t = tc.getTweet(Long.parseLong(idsStr[0]));
					lblTweetData.setText("Tweet: "+t.toString());
				}
			}
		});
		btnFindByUsername.setBounds(445, 237, 147, 23);
		add(btnFindByUsername);
		
		tweetIdField = new JTextField();
		tweetIdField.setColumns(10);
		tweetIdField.setBounds(690, 206, 147, 20);
		add(tweetIdField);
		
		lblTweetId = new JLabel("Tweet ID");
		lblTweetId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTweetId.setBounds(609, 209, 71, 14);
		add(lblTweetId);
		
		lblTweetData = new JLabel("Tweet:");
		lblTweetData.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTweetData.setBounds(354, 501, 439, 45);
		add(lblTweetData);
		
		btnFindById = new JButton("Find By ID");
		btnFindById.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tweet t = tc.getTweet(Long.parseLong(tweetIdField.getText()));
				lblTweetData.setText("Tweet: "+t.toString());
			}
		});
		btnFindById.setBounds(690, 237, 147, 23);
		add(btnFindById);
		
	}
}
