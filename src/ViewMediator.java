import Users.User;

import java.util.HashSet;
import java.util.Set;


/**
 * Acts as a mediator for the views of Users that
 * are created. Is a combination of both a Factory
 * and Mediator design in order to ensure that open
 * views are updated. A singleton Design is also used
 * in order for no unnecessary/extra mediators are made,
 * since all the views open could be connected.
 *
 * Users are not needed ot be individually updated
 * since they have the obesrver pattern.
 */
public class ViewMediator  {
    private Set<UserViewPanel> openUserPanels;
    private static ViewMediator instance = null;


    private ViewMediator() {
        openUserPanels = new HashSet<>();
    }


    /**
     * Part of singleton design, returns the
     * single instance of the ViewMediator created.
     * @return instance of ViewMediator.
     */
    public static ViewMediator getInstance() {
        if(instance == null) {
            instance = new ViewMediator();
        }
        return instance;
    }


    /**
     * Creates and saves the view of the new user,
     * then opens up its GUI.
     *
     * @param user the User to be viewed.
     */
    public void viewUser(User user) {
        UserViewPanel viewPanel = new UserViewPanel(user);
        openUserPanels.add(viewPanel);
        viewPanel.startProgram();
    }


    /**
     * Updates the views that are stored of each GUI
     * when called.
     */
    public void updateViews() {
        for(UserViewPanel panel : openUserPanels) {
            if(panel.isDisplayable()){
                panel.updateView();
            }
            else {
                this.removeView(panel);
            }
        }
    }


    /**
     * Removes a panel view.
     *
     * @param panel the view to be removed.
     */
    public void removeView(UserViewPanel panel) {
        openUserPanels.remove(panel);
    }
}
