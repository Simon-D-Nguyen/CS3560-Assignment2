package Users;

/**
 * Finds the number of Users starting from a given
 * spot
 */
public class NumberOfUsersVisitor implements UserComponentVisitor{

    private int numberOfUsers;

    public NumberOfUsersVisitor() {
        numberOfUsers = 0;
    }

    public int getNumberOfUsers() {
        return numberOfUsers;
    }

    @Override
    public void atUser(User user) {
        numberOfUsers++;
        //Doesn't need to check for children because
        // User cannot have childrens
    }

    @Override
    public void atUserGroup(UserGroup group) {
    }
}
