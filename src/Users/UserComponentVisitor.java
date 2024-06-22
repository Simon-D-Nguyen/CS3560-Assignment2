package Users;

public class UserComponentVisitor implements Visitor{

    private int numberOfUsers;
    private int numberOfGroups;
    private int numberOfMessages;
    private int numberOfPositiveTweets;


    public UserComponentVisitor() {
        numberOfGroups = 0;
        numberOfMessages = 0;
        numberOfUsers = 0;
        numberOfPositiveTweets = 0;
    }


    @Override
    public void atUser(User user) {
        numberOfUsers++;
        numberOfMessages += user.getMessagesTotal();
        numberOfPositiveTweets += user.getPositiveWords();
    }

    @Override
    public void atUserGroup(UserGroup group) {
        numberOfGroups++;
    }

    @Override
    public int getUserTotal() {
        return numberOfUsers;
    }

    @Override
    public int getMessagesTotal() {
        return numberOfMessages;
    }

    @Override
    public int getGroupTotal() {
        return numberOfGroups;
    }

    @Override
    public int getPositiveWords() {
        return numberOfPositiveTweets;
    }
}
