//Gabrielle Neafsey-Wroten

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
	private JLabel lblPolarity;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton radioButton;
	private JRadioButton radioButton_1;
	private JRadioButton radioButton_2;
	private JLabel lblPrediction_1;
	private JLabel lblNewLabel;
	private JLabel lblNegative;
	private JLabel lblNeutral;
	private JLabel lblPositive;
	private JButton btnNewButton;
	private JCheckBox checkBox;
	private JCheckBox checkBox_1;
	private JCheckBox checkBox_1_1;
	private JScrollPane scrollPane;
	
	public MainPanel()
	{
		
		tc = new TweetCollection("./project1/testProcessed.txt");
		
		setPreferredSize (new Dimension(884, 600));
		setLayout(null);
		
		
		
		lblTweets = new JLabel("Tweets");
		lblTweets.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 24));
		lblTweets.setBounds(20, 156, 171, 45);
		add(lblTweets);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 192, 332, 396);
		add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setText(tc.toString());
		
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
		lblUsername.setBounds(352, 291, 71, 14);
		add(lblUsername);
		
		userNameField = new JTextField();
		userNameField.setBounds(462, 288, 203, 20);
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
		comboBox.setBounds(675, 288, 147, 20);
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
		btnFindByUsername.setBounds(462, 319, 203, 23);
		add(btnFindByUsername);
		
		tweetIdField = new JTextField();
		tweetIdField.setColumns(10);
		tweetIdField.setBounds(462, 192, 203, 20);
		add(tweetIdField);
		
		lblTweetId = new JLabel("Tweet ID");
		lblTweetId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTweetId.setBounds(352, 195, 71, 14);
		add(lblTweetId);
		
		btnFindById = new JButton("Find By ID");
		btnFindById.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tweet t = tc.getTweet(Long.parseLong(tweetIdField.getText()));
				foundTweet.setText(t.toString());
			}
		});
		btnFindById.setBounds(462, 223, 203, 23);
		add(btnFindById);
		
		btnRemoveAllBy = new JButton("Remove All By User");
		btnRemoveAllBy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tc.removeByUser(userNameField.getText());
			}
		});
		btnRemoveAllBy.setBounds(462, 353, 203, 23);
		add(btnRemoveAllBy);
		
		btnRemoveById = new JButton("Remove By ID");
		btnRemoveById.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tc.removeTweet(Long.parseLong(tweetIdField.getText()));
			}
		});
		btnRemoveById.setBounds(462, 257, 203, 23);
		add(btnRemoveById);
		
		lblPrediction = new JLabel("New Tweet ");
		lblPrediction.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPrediction.setBounds(352, 387, 109, 14);
		add(lblPrediction);
		
		lblPolarity = new JLabel("Polarity Guess");
		lblPolarity.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPolarity.setBounds(352, 413, 100, 20);
		add(lblPolarity);
		
		radioButton = new JRadioButton("0");
		buttonGroup.add(radioButton);
		radioButton.setBounds(458, 414, 40, 23);
		add(radioButton);
		
		radioButton_1 = new JRadioButton("2");
		buttonGroup.add(radioButton_1);
		radioButton_1.setBounds(500, 414, 40, 23);
		add(radioButton_1);
		
		radioButton_2 = new JRadioButton("4");
		buttonGroup.add(radioButton_2);
		radioButton_2.setBounds(542, 414, 40, 23);
		add(radioButton_2);
		
		radioButton_1.setSelected(true);
		
		newTweetField = new JTextField();
		newTweetField.setBounds(462, 386, 203, 20);
		add(newTweetField);
		newTweetField.setColumns(10);
		
		lblPrediction_1 = new JLabel("Prediction: ");
		lblPrediction_1.setBounds(675, 448, 147, 14);
		add(lblPrediction_1);
		
		btnAddNewTweet = new JButton("Add Tweet and Predict!");
		btnAddNewTweet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int pol = -1;
				int pol2 = 2;
				String accuracy = "Inaccurate. Nice try!";
				if (radioButton.isSelected())
					pol = 0;
				else if (radioButton_1.isSelected())
					pol = 2;
				else
					pol = 4;
				String un = userNameField.getText();
				String text = newTweetField.getText();
				Tweet tempTweet = new Tweet(pol, un, text);
				tc.addTweet(tempTweet.getId(), tempTweet);
				Tweet addedTweet = tc.tweetPrediction(tempTweet);
				pol2 = addedTweet.getPolarity();
				if (pol == pol2)
					accuracy = "Accurate. Great job!";
				lblPrediction_1.setText("Prediction: "+pol2+ ", "+accuracy);
			}
		});
		btnAddNewTweet.setBounds(462, 444, 203, 23);
		add(btnAddNewTweet);
		
		lblNewLabel = new JLabel("Percentage of: ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(352, 480, 100, 19);
		add(lblNewLabel);
		
		checkBox = new JCheckBox("0");
		checkBox.setBounds(462, 480, 40, 23);
		add(checkBox);
		
		checkBox_1 = new JCheckBox("2");
		checkBox_1.setBounds(462, 509, 40, 23);
		add(checkBox_1);
		
		checkBox_1_1 = new JCheckBox("4");
		checkBox_1_1.setBounds(462, 535, 40, 23);
		add(checkBox_1_1);
		
		lblNegative = new JLabel("Negative: ");
		lblNegative.setBounds(508, 484, 157, 14);
		add(lblNegative);
		
		lblNeutral = new JLabel("Neutral: ");
		lblNeutral.setBounds(508, 513, 157, 14);
		add(lblNeutral);
		
		lblPositive = new JLabel("Positive: ");
		lblPositive.setBounds(508, 539, 157, 14);
		add(lblPositive);
		
		btnNewButton = new JButton("Find Percentages");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (checkBox.isSelected()) {
					double negative = tc.numNegative(tc);
					lblNegative.setText("Negative: "+negative+"%");
				}
				if (checkBox_1.isSelected()) {
					double neutral = tc.numNeutral(tc);
					lblNeutral.setText("Neutral: "+neutral+"%");
				}
				if (checkBox_1_1.isSelected()) {
					double positive = tc.numPositive(tc);
					lblPositive.setText("Positive: "+positive+"%");
				}
			}
		});
		btnNewButton.setBounds(462, 565, 203, 23);
		add(btnNewButton);
		
		
		
	}
}
