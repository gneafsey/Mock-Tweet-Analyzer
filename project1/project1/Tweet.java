//Gabrielle Neafsey-Wroten

package project1;

import java.util.HashMap;

public class Tweet {
	private int polarity;
	private long id;
	private String userName;
	private String tweetText;
	
	public Tweet (int p, long d, String un, String t) {
		//Tweet constructor
		polarity = p;
		id = d;
		userName = un;
		tweetText = t;
	}
	public Tweet(Tweet t) {
		// TODO Auto-generated constructor stub
	}
	public String toString() {
		//Tweet toString method
		String toReturn = "Polarity: " + polarity + "\t";
		toReturn += "Tweet ID: " + id + "\t";
		toReturn += "Username: " + userName + "\t\t";
		toReturn += "Text: " + tweetText;
		
		return toReturn;
	}
	public boolean nameEquals(Tweet rhs) {
		//equals method comparing usernames of Tweets
		if (rhs.userName.equals(userName)) {
			return true;
		}
		return false;
	}
	
	//Getters and setters
	
	public int getPolarity() {
		return polarity;
	}
	public void setPolarity(int polarity) {
		this.polarity = polarity;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getText() {
		return tweetText;
	}
	public void setText(String tweetText) {
		this.tweetText = tweetText;
	}
	
}
