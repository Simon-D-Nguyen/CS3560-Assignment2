package Users;

/**
 * Implementation of a user group.
 *
 * Uses both the Composite and Visitor design
 * patterns in order to be searchable and contain
 * other users.
 */
public class UserGroup extends CompositeUser {

    public UserGroup(String name) {
        super(name);
    }


    @Override
    public void accept(UserComponentVisitor vis) {
        vis.atUserGroup(this);
        super.accept(vis);
    }

}
