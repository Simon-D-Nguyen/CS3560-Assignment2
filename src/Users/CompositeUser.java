package Users;

import java.util.HashSet;
import java.util.Set;

public abstract class CompositeUser implements UserComponent{
    private Set<UserComponent> components;
    private String name;


    public CompositeUser(String name) {
        this.name = name;
        this.components = new HashSet<>();
    }

    public void addUserToGroup(UserComponent user) {
        this.components.add(user);
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
}
