package Users;

import java.util.HashSet;
import java.util.Set;


/**
 * Part of the Composite design, designing the behaviors
 * for componets with children (UserGroup).
 *
 * An Abstract class was used, since most of the
 * functionality will be shared within Composite instances.
 *
 * Also implements ability for Visitors to extract
 * information from each Component child.
 */
public abstract class CompositeUser implements UserComponent{
    private final Set<UserComponent> components;
    private final String id;


    public CompositeUser(String id) {
        this.id = id;
        this.components = new HashSet<>();
    }


    @Override
    public String getId() {
        return id;
    }


    /**
     * Adds a component to the current Composite
     * instance. can add another composite to the
     * current one.
     *
     * @param newComponent A new child component.
     */
    public void addToGroup(UserComponent newComponent) {
        this.components.add(newComponent);
    }


    @Override
    public void accept(Visitor vis) {
        for(UserComponent component: components) {
            component.accept(vis);
        }
    }


    @Override
    public boolean isGroup() {
        return true;
    }


    @Override
    public UserComponent getComponent(String id) {
        if (this.id.equals(id)) {
            return this;
        }

        UserComponent output = null;
        for(UserComponent component: components) {
            if(output == null) {
                output = component.getComponent(id);
            }
        }
        return output;
    }


    @Override
    public String toString() {
        return id + "- Group";
    }
}
