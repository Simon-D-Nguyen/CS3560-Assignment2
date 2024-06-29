package Users;


/**
 * Visits to find the number of Tweets from the
 * given UserComponents.
 */
public class NumberOfTweetsVisitor implements UserComponentVisitor{

    private int numberOfTweets;

    public NumberOfTweetsVisitor() {
        numberOfTweets = 0;
    }

    @Override
    public void atUser(User user) {
        numberOfTweets += user.getPersonalFeed().size();
        //Doesn't need to check for children because
        // User cannot have childrens
    }

    @Override
    public void atUserGroup(UserGroup group) {
    }

    public int getNumberOfTweets() {
        return numberOfTweets;
    }
}
