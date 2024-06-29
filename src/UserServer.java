import Users.*;

import java.util.HashSet;
import java.util.Set;


/**
 * Acts as an intermediary for the different
 * parts of the program to access the saved/created
 * Users and User Groups. Combines elements of the Proxy
 * and Mediator designs. Singleton design used to ensure
 * that only one server/collection of users and groups
 * can be updated for consistency.
 *
 * Intentionally made to dissuade direct access
 * to the Root user group.
 */
public class UserServer {
    private final Set<User> users;
    private final Set<UserGroup> groups;
    private final UserGroup root;
    private static UserServer instance = null; // Part of singleton design


    private UserServer() {
        users = new HashSet<>();
        groups = new HashSet<>();
        root = new UserGroup("Root");
        groups.add(root);
    }


    /**
     * Part of singleton design, returns the
     * instance of User Server created.
     * @return the instance of UserServer.
     */
    public static UserServer getInstance() {
        if (instance == null) {
            instance = new UserServer();
        }
        return instance;
    }


    /**
     * Returns the root UserComponent
     * of the class server. Root is unable
     * to be changed.
     *
     * @return the root UserComponent.
     */
    public UserComponent getRoot() {
        return root;
    }


    /**
     * Adds a User to the server.
     * @param user
     */
    public void addUser(User user) {
        users.add(user);
    }


    /**
     * Adds User Group to the server.
     * @param group
     */
    public void addGroup(UserGroup group) {
        groups.add(group);
    }


    /**
     * Gets the component of with the given
     * id and returns it if it exists in the server.
     *
     * @param id The String id of the UserComponent.
     * @return the UserComponent if found, null if not.
     */
    public UserComponent getComponent(String id) {
        return root.getComponent(id);
    }


    /**
     * Checks if an ID is being used by a component
     * in the server.
     *
     * @param id the given String Id.
     * @return True if the server has a component with
     * the id, false if not.
     */
    public boolean contains(String id) {
        return root.getComponent(id) != null;
    }
}
