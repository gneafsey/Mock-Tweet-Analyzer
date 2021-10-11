//Gabrielle Neafsey-Wroten

package project1;

import java.util.ArrayList;
import java.util.Map;

public class Tester {

	public static void main(String[] args) {
		//Test Student constructor
		Tweet t1 = new Tweet(4, (long) 7382, "@CriticalSmart", "This is an epic tweet");
		System.out.println("Printing Critical Smart's Tweet");
		System.out.println(t1);
		//This worked after adding a toString class -- before it just
		//printed aTweet's memory address
		Tweet t2 = new Tweet(0, (long) 3528, "@RandomUser", "I am stressed today");
		System.out.println("Printing Random User's Tweet");
		System.out.println(t2);
		Tweet t3 = new Tweet(4, (long) 4499, "@RandomUser", "Today was better");
		Tweet t4 = new Tweet(4, (long) 5242, "@RandomUser", "Things are looking up");
		Tweet t5 = new Tweet(0, (long) 1010, "@TrollAccount", "TROLOLOLOL");
		
		TweetCollection tweetColl = new TweetCollection();
		System.out.println("Printing empty collection");
		System.out.println(tweetColl);
		tweetColl.addTweet((long) 7382, t1);
		tweetColl.addTweet((long) 3528, t2);
		tweetColl.addTweet((long) 4499, t3);
		tweetColl.addTweet((long) 5242, t4);
		tweetColl.addTweet((long) 1010, t5);
		System.out.println("Printing filled collection");
		System.out.println(tweetColl);
		
		TweetCollection tweetColl2 = new TweetCollection("./project1/tester1.txt");
		System.out.println("Printing collection");
		System.out.println(tweetColl2);
		System.out.println(tweetColl2.size());
		
		//test getTweet
		System.out.println("Getting Tweet: " + tweetColl2.getTweet((long)1467811193));
		System.out.println("Getting Tweet: " + tweetColl2.getTweet((long)146781119));
		
		//test removeTweet
		tweetColl2.removeTweet((long)1467811184);
		tweetColl2.removeTweet((long)146781118);
		System.out.println(tweetColl2);
		System.out.println("Collection size: " + tweetColl2.size());
		
		//test allIds
		ArrayList<Long> allIds = tweetColl2.getIds();
		System.out.println("Tweet IDs:");
		System.out.println(allIds);
		
		//test someIds
		ArrayList<Long> someIds = tweetColl.idsByUser("@RandomUser");
		System.out.println("Tweets by Random: " + someIds);
		ArrayList<Long> someIds2 = tweetColl.idsByUser("@CriticalSmart");
		System.out.println(someIds2);
		ArrayList<Long> someIds3 = tweetColl.idsByUser("@TrollAccount");
		System.out.println(someIds3);
		ArrayList<Long> someIds4 = tweetColl.idsByUser("@Unknown");
		System.out.println("Was this found?" + someIds4);
		
		//test removeByUser
		System.out.println(tweetColl);
		System.out.println("Removing Tweets by Random");
		tweetColl.removeByUser("@RandomUser");
		System.out.println(tweetColl);
		
		//test tweetPrediction
		System.out.println(tweetColl.tweetPrediction(t4));
		System.out.println(tweetColl.tweetPrediction(t2));
		
		//test collectionPrediction
		System.out.println("Collection1 accuracy: " + tweetColl.collectionPrediction(tweetColl) + "%");
		System.out.println("Collection2 accuracy: " + tweetColl2.collectionPrediction(tweetColl2) + "%");
		TweetCollection tweetColl3 = new TweetCollection("./project1/testProcessed.txt");
		System.out.println("Collection3 accuracy: " + tweetColl3.collectionPrediction(tweetColl3) +"%");
		System.out.println("Collection3 size: " + tweetColl3.size());
//		TweetCollection tweetColl4 = new TweetCollection("./project1/trainingProcessed.txt");
//		System.out.println("Collection4 accuracy: " + tweetColl4.collectionPrediction(tweetColl4) +"%");
//		System.out.println("Collection4 size: " + tweetColl4.size());
		
		//test writing to file
		tweetColl3.writeFile("./project1/dataWriteTest.txt");
		
	}

}
