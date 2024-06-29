package Users;

public interface Subject {

    /**
     * Attaches an observer to this subject to be
     * updated when changes occur.
     *
     * @param observer observes change in subject.
     */
    public void attach(Observer observer);


    /**
     * Notifies Observers when changes occur.
     */
    public void notifyObservers();
}
