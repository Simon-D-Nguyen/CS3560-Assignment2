package Users;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class User implements Observer, Subject, UserComponent{
    private String id;
    private Set<String> followers;
    private Set<String> followings;
    private ArrayList<String> newsFeed;
    private ArrayList<String> personalTweets;
    private Set<Observer> observers;


    public static final User instance = null;

    public User(String id, ArrayList<String> personalTweets) {
        this.id = id;
        this.personalTweets = personalTweets;
        this.followers = new HashSet<>();
        this.followings = new HashSet<>();
        this.newsFeed = new ArrayList<>();
        this.observers = new HashSet<>();
    }


    public String getId() {
        return id;
    }


    public void setNewFollower(User follower) {
        followers.add(follower.getId());
    }


    public void followAUser(User following) {
        followings.add(following.getId());
        following.setNewFollower(this);

        // Attach the
        attach(following);
    }


    public void postTweet(String tweet) {
        personalTweets.add(tweet);
        newsFeed.add(tweet);

        notifyObservers();
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
            observer.update(personalTweets.getFirst());
        }
    }



    public int getUserTotal() {
        return 1;
    }

    public int getMessagesTotal() {
        return personalTweets.size();
    }


    public int getGroupTotal() {
        return 0;
    }


    public int getPositiveWords() {
        int output = 0;
        for(String tweet: personalTweets) {
            if (tweet.contains("happy") || tweet.contains("joy")) {
                output = output + 1;
            }
        }

        return output;
    }


    @Override
    public boolean isGroup() {
        return false;
    }

    @Override
    public void accept(Visitor vis) {
        vis.atUser(this);
    }
}