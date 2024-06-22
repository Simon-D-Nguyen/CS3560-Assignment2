package Users;

import java.util.HashSet;
import java.util.Set;

public class UserGroup implements UserComponent{

    private Set<UserComponent> components;
    private String name;


    public UserGroup(String name) {
        this.name = name;
        this.components = new HashSet<>();
    }


    public void addUserToGroup(UserComponent user) {
        this.components.add(user);
    }

    @Override
    public int getUserTotal() {
        int output = 0;
        for(UserComponent userComponent: components) {
            output += userComponent.getUserTotal();
        }

        return output;
    }

    @Override
    public int getMessagesTotal() {
        int output = 0;
        for(UserComponent userComponent: components) {
            output += userComponent.getMessagesTotal();
        }

        return output;
    }

    @Override
    public int getGroupTotal() {
        int output = 1;
        for(UserComponent userComponent: components) {
            output += userComponent.getGroupTotal();
        }

        return output;
    }

    @Override
    public int getPositiveWords() {
        int output = 0;
        for(UserComponent userComponent: components) {
            output += userComponent.getPositiveWords();
        }

        return output;
    }
}
