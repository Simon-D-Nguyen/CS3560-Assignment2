package Users;

/**
 * Part of Visitor design implementation, where
 *  it makes each component/node implementation
 *  seperate from the operations on each component.
 *
 *  Allows the for increased ability to add
 *  operations on UserComponents
 */
public interface UserComponentVisitor {
    /**
     * Defines the operation to be done when
     * at a User instance of UserComponent.
     *
     * @param user instance of a User.
     */
    public void atUser(User user);


    /**
     * Defines the operation to be done when
     * at a UserGroup isntance of UserComponent.
     *
     * @param group instance of a UserGroup.
     */
    public void atUserGroup(UserGroup group);
}
