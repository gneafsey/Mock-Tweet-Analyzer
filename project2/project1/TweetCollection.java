//Gabrielle Neafsey-Wroten

package project1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class TweetCollection {
	private HashMap<Long,Tweet> myData;
	private String fileName;
	private HashMap<String,Integer> dict;
	
	public TweetCollection () {
		//default constructor
		myData = new HashMap<>();
		dict = new HashMap<>();
		fileName = null;
	}
	
	public TweetCollection (String fn) {
		//constructor with input file
		this();
		fileName = fn;
		readFile();
	}
	
	public void addTweet(Long id, Tweet t) {
		//add Tweet to collection and add text to dictionary
		myData.put(id, t);
		addDict(t);
	}
	
	public Tweet getTweet(long id) {
		//retrieve Tweet by Key id, if the id exists
		boolean found = myData.containsKey(id);
		if (found) {
			System.out.println("Found Tweet ID: " + id);
			return myData.get(id);
		}
		System.out.println("ID " + id + " not found");
		Tweet nullTweet = new Tweet(0,(long) -1,"notfound","n/a");
		return nullTweet;
	}
	public void removeTweet(long id) {
		//remove Tweet, if the id exists
		boolean found = myData.containsKey(id);
		if (found) {
			myData.remove(id);
			System.out.println("ID " + id + " removed");
		}
		else
			System.out.println("ID " + id + " not found");
	}
	public void addDict(Tweet t) {
		//add the text of a Tweet to the dictionary hashmap
		//determine whether each word is "negative," "neutral," or "positive"
		String text = t.getText();
		String[] tokens = text.split(" ");
		String aword;
		Integer ct;
		if (t.getPolarity() == 0) {
			for (int i = 0; i < tokens.length; i++) {
				aword = tokens[i];
				ct = dict.get(aword);
				if (ct == null) {
					dict.put(aword, -1);
				}
				else {
					dict.put(aword, ct--);
				}
			}
		}
		else if (t.getPolarity() == 2) {
			for (int i = 0; i < tokens.length; i++) {
				aword = tokens[i];
				ct = dict.get(aword);
				if (ct == null) {
					dict.put(aword, 0);
				}
				else {
					dict.put(aword, ct);
				}
			}
		}
		else {
			for (int i = 0; i < tokens.length; i++) {
				aword = tokens[i];
				ct = dict.get(aword);
				if (ct == null) {
					dict.put(aword, 1);
				}
				else {
					dict.put(aword, ct++);
				}
			}
		}
	}
	public ArrayList<Long> getIds() {
		//store all Tweet IDs in an arraylist
		Set<Long> keySet = myData.keySet();
		ArrayList<Long> all_ids = new ArrayList<Long>(keySet);
		return all_ids;
	}
	public ArrayList<Long> idsByUser (String un)  {
		//store tweets by username in arraylist, if username exists
		boolean found = false;
		ArrayList<Long> ibu = new ArrayList<Long>();
		for(Map.Entry<Long, Tweet> entry : myData.entrySet()) {
			if (un.equals(entry.getValue().getUserName())) {
				ibu.add(entry.getKey());
				found = true;
			}
		}
		if (!found)
			System.out.println("Username not found");
		return ibu;
	}
//	public void removeByUser (String un) {
//		boolean found = false;
//		HashMap<Long,Tweet> toRemove = new HashMap<>();
//		for(Map.Entry<Long, Tweet> entry : myData.entrySet()) {
//			if (un.equals(entry.getValue().getUserName())) {
//				toRemove.put(entry.getKey(),entry.getValue());
//				found = true;
//			}
//		}
//		if (!found)
//			System.out.println("Username not found");
//	}
	public void removeByUser (String un) {
		ArrayList<Long> toRemove = new ArrayList<Long>();
		toRemove = idsByUser(un);
		for (Long l : toRemove)
			removeTweet(l);
	}
	public double collectionPrediction(TweetCollection rhs) {
		//determine accuracy percentage of Tweet polarity predictions
		double result = 100.0;
		double match = 0;
		for(Map.Entry<Long, Tweet> entry : myData.entrySet()) {
			Tweet tempTweet = new Tweet(entry.getValue());
			rhs.tweetPrediction(entry.getValue());
			if (entry.getValue().getPolarity() == tempTweet.getPolarity()) {
				match++;
			}
		}
		result = (match/(size()))*100;
		return result;
	}
	public Tweet tweetPrediction(Tweet t) {
		//determine polarity of each Tweet
		int polcount = 0;
		String text = t.getText();
		String[] tokens = text.split(" ");
		for (int i = 0; i < tokens.length; i++) {
			for(Map.Entry<String, Integer> entry : dict.entrySet()) {
				if (tokens[i].equals(entry.getKey()))
					polcount += entry.getValue();
			}
		}
		if (polcount <= -1) {
			t.setPolarity(0);
		}
		else if (polcount == 0) {
			t.setPolarity(2);
		}
		else {
			t.setPolarity(4);
		}
		return t;
	}
	public int size() {
		//return size of Tweet collection
		return myData.size();
	}
	
	public String toString() {
		//convert Tweet collection to String
		String toReturn = "Collection of Tweets:"+"\n";
		
		for(Map.Entry<Long, Tweet> entry : myData.entrySet()) {
			toReturn += entry.getValue() + "\n";
		}
		
		return toReturn;
	}
	
	private void readFile() {
		//read Tweet data from file
		BufferedReader lineReader = null;
		try {
			FileReader fr = new FileReader(fileName);
			lineReader = new BufferedReader(fr);
			String line = null;
			while ((line = lineReader.readLine())!=null) {
				String[] tokens = line.split(",");
				int polarity = Integer.parseInt(tokens[0]);
				long id = Long.parseLong(tokens[1]);
				addTweet(id, new Tweet(polarity,id,tokens[2],tokens[3]));
			}
		} catch (Exception e) {
			System.err.println("there was a problem with the file reader, try a different read type.");
			try {
				lineReader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(fileName.substring(1))));
				String line = null;
				while ((line = lineReader.readLine()) != null) {
					String[] tokens = line.split(",");
					int polarity = Integer.parseInt(tokens[0]);
					long id = Long.parseLong(tokens[1]);
					addTweet(id, new Tweet(polarity,id,tokens[2],tokens[3]));
				}
			} catch (Exception e2) {
				System.err.println("there was a problem with the file reader, try again.  either no such file or format error");
			} finally {
				if (lineReader != null)
					try {
						lineReader.close();
					} catch (IOException e2) {
						System.err.println("could not close BufferedReader");
					}
			}			
		} finally {
			if (lineReader != null)
				try {
					lineReader.close();
				} catch (IOException e) {
					System.err.println("could not close BufferedReader");
				}
		}
		
	}
	
	public void writeFile () {
		doWrite(fileName);
	} // end of writeFile method

	public void writeFile(String altFileName) {
		doWrite(altFileName);		
	}// end of writeFile method
	
	private void doWrite(String fn) {
		//write Tweet data to file
		try
		{

			FileWriter fw = new FileWriter(fn);
			BufferedWriter myOutfile = new BufferedWriter(fw);
			
			int index = 1;
			
			for(Map.Entry<Long, Tweet> entry : myData.entrySet()) {
				myOutfile.write("Index: " + index + " " + entry.getValue());
				myOutfile.newLine();
				index++;
			}
				
			myOutfile.flush();
			myOutfile.close();
		}
		catch (Exception e) {
			e.printStackTrace();
			System.err.println("Didn't save to " + fn);
		}
	}	

}
