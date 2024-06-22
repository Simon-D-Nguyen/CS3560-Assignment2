package Users;

public interface Visitor {
    public void atUser(User user);
    public void atUserGroup(UserGroup group);
    public int getUserTotal();
    public int getMessagesTotal();
    public int getGroupTotal();
    public int getPositiveWords();
}
