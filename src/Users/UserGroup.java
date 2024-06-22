package Users;

import java.util.HashSet;
import java.util.Set;

public class UserGroup extends CompositeUser{


    public UserGroup(String name) {
        super(name);
    }


    @Override
    public void accept(Visitor vis) {
        vis.atUserGroup(this);
        super.accept(vis);
    }
}
