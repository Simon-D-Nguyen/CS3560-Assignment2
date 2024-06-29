import Users.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * The Panel that shows the details of
 * a given User. Works in part with the Mediator/Facotry
 * design of the View Mediator to be created correctly
 * and to update other Views when needed.
 */
public class UserViewPanel extends JPanel implements ActionListener {

    private JList<String> newsFeed;
    private JList<String> following;
    private JTextArea userIdBox;
    private JTextArea tweetBox;
    private JButton followButton;
    private JButton postButton;
    private final User user;
    private final UserServer server;
    private final ViewMediator mediator;



    public UserViewPanel(User user) {
        super();

        this.user = user;
        server = UserServer.getInstance();
        mediator = ViewMediator.getInstance();
    }


    public void setPanel(){
        setMinimumSize(new Dimension(400, 500));
        JSplitPane mainSplit = new JSplitPane(SwingConstants.HORIZONTAL);

        //Top Half of Pane
        JSplitPane topHalf = new JSplitPane(SwingConstants.HORIZONTAL);
        following = new JList<>(user.getFollowings().toArray(new String[0]));
        following.setPreferredSize(new Dimension(100, 100));

        userIdBox = new JTextArea("Enter User ID");
        followButton = new JButton("Follow User");
        followButton.setActionCommand("follow");
        followButton.addActionListener(this);

        topHalf.setMinimumSize(new Dimension(200, 250));
        topHalf.setTopComponent(new JSplitPane(JSplitPane.VERTICAL_SPLIT, userIdBox, followButton));
        topHalf.setBottomComponent(following);


        // Bottom Half
        JSplitPane bottomHalf = new JSplitPane(SwingConstants.HORIZONTAL);
        newsFeed = new JList<>(user.getNewsFeed().toArray(new String[0]));
        newsFeed.setPreferredSize(new Dimension(100, 100));

        tweetBox = new JTextArea("Tweet Text");
        postButton = new JButton("Post Tweet");
        postButton.setActionCommand("post");
        postButton.addActionListener(this);

        bottomHalf.setMinimumSize(new Dimension(200, 250));
        bottomHalf.setTopComponent(new JSplitPane(JSplitPane.VERTICAL_SPLIT, tweetBox, postButton));
        bottomHalf.setBottomComponent(newsFeed);


        // Add in halves
        mainSplit.setTopComponent(topHalf);
        mainSplit.setBottomComponent(bottomHalf);
        add(mainSplit);
    }


    /**
     * Returns the user that the view
     * is repesenting.
     * @return the User of the view.
     */
    public User getUser() {
        return user;
    }


    /**
     * Updates the Following of the Panel
     * based on changes  to the User it represents
     */
    public void updateFollowings() {
        following.setListData(user.getFollowings().toArray(new String[0]));
    }


    /**
     * Updates the News Feed of the
     * Panel based on the User it represents.
     */
    public void updateNewsFeed(){
        newsFeed.setListData(user.getNewsFeed().toArray(new String[0]));
    }


    /**
     * Updates both mutable parts
     * of the GUI.
     */
    public void updateView() {
        updateFollowings();
        updateNewsFeed();
    }


    /**
     * Sets up the outer frame and displays
     * the GUI when called.
     */
    private void createAndShowGUI() {
        JFrame frame = new JFrame(user.getId() + " - View");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setPanel();
        frame.setContentPane(this);

        frame.setMinimumSize(new Dimension(300, 300));
        frame.dispose();
        frame.pack();
        frame.setVisible(true);
    }


    /**
     * Starts the GUI up.
     */
    public void startProgram() {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });
    }


    /**
     * Set up action performed in order to make sure
     * that the given actions where being processed
     * correctly, and to separate the instance
     * of the original buttons from the logic.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if("post".equals(e.getActionCommand())) {
            if(!tweetBox.getText().isEmpty()){
                user.postTweet(tweetBox.getText()); //Posts to the User
                mediator.updateViews(); //Updates other views open

                tweetBox.setText("");   //Reset Text
            }
        }
        else if ("follow".equals(e.getActionCommand())) {
            if(!userIdBox.getText().isEmpty()){
                user.followAUser((User) server.getComponent(userIdBox.getText())); //Follows User
                mediator.updateViews(); // Updates open views

                userIdBox.setText("");  //Resets Text
            }
        }
    }
}
