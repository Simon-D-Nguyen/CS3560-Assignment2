package Users;

public interface UserComponent {

    public boolean isGroup();
    public void accept(Visitor vis);
}
