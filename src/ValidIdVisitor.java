import Users.User;
import Users.UserComponentVisitor;
import Users.UserGroup;


/**
 * Visits the User Components to check if there
 * are any invalid Ids. Does not have to check
 * for Unique IDs, since the implmented code
 * already denies repeated Ids.
 */
public class ValidIdVisitor implements UserComponentVisitor {
    private boolean isValid;

    public ValidIdVisitor() {
        isValid = true;
    }

    @Override
    public void atUser(User user) {
        //Check for white spaces
        if(user.getId().contains(" ")) {
            System.out.println(user.getId());
            isValid = false;
        }
        // Code already prevents
    }

    @Override
    public void atUserGroup(UserGroup group) {
        if(group.getId().contains(" ")) {
            System.out.println(group.getId());
            isValid = false;
        }
    }


    public boolean isValid() {
        return isValid;
    }
}
