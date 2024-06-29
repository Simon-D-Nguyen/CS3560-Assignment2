package Users;

public interface Observer {

    /**
     * Updates the observer when called.
     *
     * @param update A string that represents the update/
     *               update value.
     */
    public void update(String update);
}
