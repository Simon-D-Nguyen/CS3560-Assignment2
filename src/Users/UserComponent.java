package Users;


/**
 * Part of the Composite pattern and Visitor interface.
 *
 * Allows for all implementations of the interface to
 * call or call other components with the same
 * methods. Also allows for searching across all
 * components.
 */
public interface UserComponent {

    /**
     * Returns the id of the current user.
     * @return the String value of the id
     */
    public String getId();


    /**
     * Checks if the component is a group of users
     * @return a boolean value
     */
    public boolean isGroup();


    /**
     * Accepts a visitor to visit the data
     * stored in the component.
     *
     * @param vis A specified visitor.
     */
    public void accept(UserComponentVisitor vis);


    /**
     * Returns an already created component if
     * with the given id, if it exists.
     *
     * @param id the id of a UserComponent
     * @return a UserComponent if it exists, or null.
     */
    public UserComponent getComponent(String id);
}
