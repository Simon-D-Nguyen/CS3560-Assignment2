package Users;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * User class that holds the information for each individual
 * user.
 *
 * Utilizes the Observer/Subject pattern in order to update
 * other users/followers automatically when updates occur.
 *
 * Also utilizes Visitor and Composite patterns, explained
 * within their interfaces. Allows for a more unified searching
 * and visitor flexibility.
 */
public class User implements UserComponent, Observer, Subject{
    private final String id;
    private final Set<String> followers; //users the follow this user
    private final Set<String> followings; //users that this user follows
    private final ArrayList<String> newsFeed;
    private final ArrayList<String> personalTweets;
    private final Set<Observer> observers;
    private String newTweet;


    public User(String id) {
        this.id = id;
        this.personalTweets = new ArrayList<>();
        this.followers = new HashSet<>();
        this.followings = new HashSet<>();
        this.newsFeed = new ArrayList<>();
        this.observers = new HashSet<>();
    }


    public String getId() {
        return id;
    }


    /**
     * Sets a new person to follow this User.
     *
     * @param follower User who follows this user.
     */
    public void setNewFollower(User follower) {
        followers.add(follower.getId());
    }


    /**
     * Sets a new user for this user to follow,
     * and also attaches this user to recieve
     * updates.
     *
     * @param following User who this user is following.
     */
    public void followAUser(User following) {
        followings.add(following.getId());
        following.setNewFollower(this);

        // This user attaches to the person they are following
        following.attach(this);
    }


    /**
     * Posts and saves a tweet by the user.
     * Updates all followers.
     *
     * @param tweet String that is tweeted.
     */
    public void postTweet(String tweet) {
        personalTweets.add(tweet);
        newsFeed.add(tweet);
        newTweet = id+ ": " + tweet;

        notifyObservers();
    }


    /**
     * Recieves a copy of the set of
     * user IDs the user follows.
     *
     * @return A copy of the user ids.
     */
    public Set<String> getFollowings() {
        return new HashSet<>(followings);
    }


    /**
     * Returns a copy of the news Feed of
     * tweets of this user.
     *
     * @return a copy of the news feed.
     */
    public List<String> getNewsFeed() {
        return new ArrayList<>(newsFeed);
    }


    /**
     * Returns a copy of the persnal feed
     * of tweets of this user.
     *
     * @return a copy of the personal feed.
     */
    public List<String> getPersonalFeed() {
        return new ArrayList<>(personalTweets);
    }



    @Override
    public void update(String update) {
        newsFeed.add(update);
    }


    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }


    @Override
    public void notifyObservers() {
        for(Observer observer : observers) {
            observer.update(newTweet);
        }
    }


    @Override
    public boolean isGroup() {
        return false;
    }



    @Override
    public void accept(UserComponentVisitor vis) {
        vis.atUser(this);
    }


    @Override
    public UserComponent getComponent(String id) {
        if(this.id.equals(id)) {
            return this;
        }

        return null;
    }


    @Override
    public String toString() {
        return id;
    }
}