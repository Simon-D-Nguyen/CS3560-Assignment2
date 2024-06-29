package Users;


/**
 * Finds the number of UserGroups from a given
 * UserComponent and it's children.
 */
public class NumberOfGroupsVisitor implements UserComponentVisitor {
    private int numberOfGroups;


    public NumberOfGroupsVisitor() {
        numberOfGroups = 0;
    }


    @Override
    public void atUser(User user) {
    }


    @Override
    public void atUserGroup(UserGroup group) {
        numberOfGroups++;
        // Only needs to/can increment group at UserGroup
    }


    public int getNumberOfGroups() {
        return numberOfGroups;
    }
}
