package Users;

public class LastUpdatedVisitor implements UserComponentVisitor {
    private User lastUpdatedUser;

    public LastUpdatedVisitor() {
        lastUpdatedUser = null;
    }

    @Override
    public void atUser(User user) {
        if (lastUpdatedUser == null) {
            lastUpdatedUser = user;
        }
        else {
            if(user.getLastUpdateTime() > lastUpdatedUser.getLastUpdateTime()) {
                lastUpdatedUser = user;
            }
        }
    }

    @Override
    public void atUserGroup(UserGroup group) {
        // Not counting groups
    }


    public String getLastUpdatedUserID() {
        if (lastUpdatedUser == null){
            return "N/A";
        }
        return lastUpdatedUser.getId();
    }
}
