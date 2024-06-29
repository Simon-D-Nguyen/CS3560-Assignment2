import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.View;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreeSelectionModel;
import Users.*;

import java.awt.*;


/**
 * Handles the Tree View and selection of Users/UserGroups
 * From the Admin Panel GUI. Coupling between this class and
 * AdminPanel is needed since they are essentially displaying
 * one cohesive section.
 *
 * Utilizes the Tree Model built in for JTrees to
 * select UserComponents.
 *
 *  I used a Singleton Design for the UserTreeView because
 *  it is static when updating, does not need to be
 *  reinstatiated since it's purpose is to display
 *  the tree of UserComponents, and holds the order
 *  and organization of the UserComponents.
 */
public class UserTreeView extends JPanel implements TreeSelectionListener {

    private final JTree tree;
    private DefaultMutableTreeNode selected;
    private final UserServer server;
    private static UserTreeView instance = null; //For singleton


    /**
     * Returns the singleton instance.
     * @return a single instance of this class.
     */
    public static UserTreeView getInstance() {
        if(instance == null){
            instance = new UserTreeView();
        }

        return instance;
    }


    private UserTreeView() {
        super();

        server = UserServer.getInstance();

        // Make top of the tree
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(server.getRoot().toString());
        tree = new JTree(root);

        // Single Selection
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

        // Make panel a listener
        tree.addTreeSelectionListener(this);

        // Add Scroll Pane
        JScrollPane pane = new JScrollPane(tree);
        pane.setPreferredSize(new Dimension(200, 300));
        add(pane);
    }


    /**
     * Checks when the user chooses a new
     * member of the tree.
     *
     * @param e the event that characterizes the change.
     */
    @Override
    public void valueChanged(TreeSelectionEvent e) {
        // Get Last selected Path Component
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
        selected = node;
    }


    /**
     * Updates the tree model and the GUI.
     */
    public void updateTree() {
        DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
        model.reload();
    }


    /**
     * Gets the UserComponent represented by the
     * tree node selected in the Tree GUI.
     *
     * @param node the TreeNode selected in the GUI.
     * @return the UserComponent stored in the TreeNode.
     */
    private UserComponent getSelectedComponent(TreeNode node) {
        String componentName = node.toString().split("-")[0];
        System.out.println(componentName);
        return server.getComponent(componentName);
    }


    /**
     * Adds a UserComponent to the tree, but does not
     * if the ID is already being used.
     *
     * @param id the ID of the userComponent.
     * @param component The Instance of the Usercomponent.
     */
    private void addToTree(String id, UserComponent component) {
        if(!server.contains(id)) {
            UserComponent selectedComponent = getSelectedComponent(selected);
            UserGroup parent;

            //Checks that the selected component is a Group
            if(selectedComponent.isGroup()) {
                parent = (UserGroup) selectedComponent;

                selected.add(new DefaultMutableTreeNode(component));
                parent.addToGroup(component);

                //Adds info to server
                if (component.isGroup()) {
                    server.addGroup((UserGroup) component);
                }
                else {
                    server.addUser((User) component);
                }
            }

            updateTree();
        }
    }


    /**
     * Adds a User to the Tree. Will not add if the
     * id is in use.
     *
     * @param id the ID of the new user.
     */
    public void addUser(String id) {
        User newUser = new User(id);

        addToTree(id, newUser);
    }


    /**
     * Adds a User Group to the tree. Will
     * not add if the id is in use.
     *
     * @param id the id of the new User Group.
     */
    public void addGroup(String id) {
        UserGroup newUserGroup = new UserGroup(id);

        addToTree(id, newUserGroup);
    }


    /**
     * Calls to open a View of the info of
     * the selected User to view.
     */
    public void viewUser() {
        UserComponent selectedComponent = getSelectedComponent(selected);

        // If the selected component is a user
        if(selectedComponent != null && !selectedComponent.isGroup()) {
            //Uses the mediator to get the instance
            ViewMediator mediator = ViewMediator.getInstance();
            mediator.viewUser((User) selectedComponent);
        }
    }

}
