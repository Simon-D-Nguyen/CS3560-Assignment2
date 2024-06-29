package Users;

import java.util.ArrayList;
import java.util.List;


/**
 * Finds the percentage of positive Tweets from
 * Users/UserComponents from a given UserComponent.
 */
public class PositivePercentTweetVisitor implements UserComponentVisitor{

    private int numberofTweets;
    private int numberOfPositiveTweets;
    private List<String> positiveWords;


    public PositivePercentTweetVisitor() {
        numberofTweets = 0;
        numberOfPositiveTweets = 0;
        positiveWords = new ArrayList<>();
        defaultPositiveWords();
    }


    /**
     * Sets the positive words that the visitor looks for.
     *
     * @param positiveWords List of Strings.
     */
    public void setPositiveWords(List<String> positiveWords) {
        this.positiveWords = new ArrayList<>(positiveWords);
    }


    /**
     * Sets a default list of "Positive" words
     * for functionality.
     */
    public void defaultPositiveWords() {
        positiveWords.clear();
        positiveWords.add("good");
        positiveWords.add("joy");
        positiveWords.add("great");
    }


    /**
     * Checks the tweets from a given user
     * to see if they contain any "positive words".
     * The count can only increase once per tweet.
     *
     * @param user A User that has tweets.
     * @return the number of tweets that
     * contain positive words.
     */
    public int numberOfPositiveTweetsFromUser(User user) {
        int count = 0;
        for(String tweet: user.getPersonalFeed())  {
            for(String positiveWord : positiveWords) {
                if (tweet.toLowerCase().contains(positiveWord.toLowerCase())) {
                    count++;
                    break;
                }
            }
        }

        return count;
    }


    @Override
    public void atUser(User user) {
        numberofTweets += user.getPersonalFeed().size();
        numberOfPositiveTweets += numberOfPositiveTweetsFromUser(user);
        //Doesn't need to check for children because
        // User cannot have childrens
    }

    @Override
    public void atUserGroup(UserGroup group) {
    }



    public double getPositiveTweetPercentage() {
        if (numberOfPositiveTweets == 0) {
            return 0;
        }
        return (double) numberOfPositiveTweets / numberofTweets;
    }
}
