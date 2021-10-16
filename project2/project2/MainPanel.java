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
	private JLabel lblTweets;
	private JTextArea textArea;
	private JTextArea foundTweet;
	private JLabel lblFoundTweet;
	private JLabel logo;
	private JLabel lblUsername;
	private JLabel lblTweetId;
	private JButton btnFindById;
	private JButton btnFindByUsername;
	private JButton btnRemoveAllBy;
	private JButton btnRemoveById;
	private JLabel lblPrediction;
	private JTextField newTweetField;
	private JButton btnAddNewTweet;
	private JLabel lblPrediction_1;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	//-----------------------------------------------------------------
	// Sets up the panel, including the timer for the animation.
	//-----------------------------------------------------------------
	public MainPanel()
	{
		
		tc = new TweetCollection("./project1/testProcessed.txt");
		
		setPreferredSize (new Dimension(884, 582));
		setLayout(null);
		
		
		
		lblTweets = new JLabel("Tweets");
		lblTweets.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 24));
		lblTweets.setBounds(20, 156, 171, 45);
		add(lblTweets);
		
		textArea = new JTextArea();
		textArea.setText(tc.toString());
		textArea.setBounds(10, 206, 332, 340);
		add(textArea);
		
		foundTweet = new JTextArea();
		foundTweet.setLineWrap(true);
		foundTweet.setBounds(551, 55, 271, 81);
		add(foundTweet);
		
		lblFoundTweet = new JLabel("Found Tweet:");
		lblFoundTweet.setFont(new Font("Yu Gothic", Font.PLAIN, 20));
		lblFoundTweet.setBounds(551, 11, 171, 33);
		add(lblFoundTweet);
		
		logo = new JLabel("");
		logo.setIcon(new ImageIcon(MainPanel.class.getResource("/project2/Twitter-xxl.png")));
		logo.setBounds(305, 11, 236, 170);
		add(logo);
		
		lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUsername.setBounds(352, 315, 71, 14);
		add(lblUsername);
		
		userNameField = new JTextField();
		userNameField.setBounds(462, 312, 203, 20);
		add(userNameField);
		userNameField.setColumns(10);
		
		comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = (String)comboBox.getSelectedItem();
				Tweet t = tc.getTweet(Long.parseLong(id));
				foundTweet.setText(t.toString());
			}
		});
		comboBox.setBounds(675, 312, 147, 20);
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
					foundTweet.setText(t.toString());
				}
			}
		});
		btnFindByUsername.setBounds(462, 343, 203, 23);
		add(btnFindByUsername);
		
		tweetIdField = new JTextField();
		tweetIdField.setColumns(10);
		tweetIdField.setBounds(462, 216, 203, 20);
		add(tweetIdField);
		
		lblTweetId = new JLabel("Tweet ID");
		lblTweetId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTweetId.setBounds(352, 219, 71, 14);
		add(lblTweetId);
		
		btnFindById = new JButton("Find By ID");
		btnFindById.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tweet t = tc.getTweet(Long.parseLong(tweetIdField.getText()));
				foundTweet.setText(t.toString());
			}
		});
		btnFindById.setBounds(462, 247, 203, 23);
		add(btnFindById);
		
		btnRemoveAllBy = new JButton("Remove All By User");
		btnRemoveAllBy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tc.removeByUser(userNameField.getText());
			}
		});
		btnRemoveAllBy.setBounds(462, 377, 203, 23);
		add(btnRemoveAllBy);
		
		btnRemoveById = new JButton("Remove By ID");
		btnRemoveById.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tc.removeTweet(Long.parseLong(tweetIdField.getText()));
			}
		});
		btnRemoveById.setBounds(462, 281, 203, 23);
		add(btnRemoveById);
		
		lblPrediction = new JLabel("New Tweet ");
		lblPrediction.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPrediction.setBounds(352, 411, 109, 14);
		add(lblPrediction);
		
		newTweetField = new JTextField();
		newTweetField.setBounds(462, 410, 203, 20);
		add(newTweetField);
		newTweetField.setColumns(10);
		
		btnAddNewTweet = new JButton("Add New Tweet");
		btnAddNewTweet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String un = userNameField.getText();
				String text = newTweetField.getText();
				Tweet t = new Tweet(un, text);
				tc.addTweet(t.getId(), t);
			}
		});
		btnAddNewTweet.setBounds(462, 487, 203, 23);
		add(btnAddNewTweet);
		
		lblPrediction_1 = new JLabel("Polarity");
		lblPrediction_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPrediction_1.setBounds(352, 448, 71, 20);
		add(lblPrediction_1);
		
		JRadioButton radioButton = new JRadioButton("0");
		buttonGroup.add(radioButton);
		radioButton.setBounds(462, 449, 40, 23);
		add(radioButton);
		
		JRadioButton radioButton_1 = new JRadioButton("2");
		buttonGroup.add(radioButton_1);
		radioButton_1.setBounds(504, 449, 40, 23);
		add(radioButton_1);
		
		JRadioButton radioButton_2 = new JRadioButton("4");
		buttonGroup.add(radioButton_2);
		radioButton_2.setBounds(546, 449, 40, 23);
		add(radioButton_2);
		
		
		
	}
}
