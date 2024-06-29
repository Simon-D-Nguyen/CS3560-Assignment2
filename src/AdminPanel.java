import Users.NumberOfGroupsVisitor;
import Users.NumberOfTweetsVisitor;
import Users.NumberOfUsersVisitor;
import Users.PositivePercentTweetVisitor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * The main panel that serves as the Admin GUI in the project.
 * Acts as a pseudo-facade for the user to interact
 * with and change/view various parts of the program.
 *
 * I decided to use a panel instead of a Frame in the case of
 * moving/implementing the GUI into something else.
 */
public class AdminPanel extends JPanel implements ActionListener {

    private JTextArea userIdTextArea;
    private JTextArea groupIdTextArea;
    private UserTreeView userTreeView;
    private final UserServer server;


    public AdminPanel(){
        super();
        server = UserServer.getInstance();
    }


    /**
     * Sets up the configuration of
     * this Jpanel with the various JComponents
     * in design.
     */
    private void setPanel() {
        userTreeView = UserTreeView.getInstance();


        // Adding User with ID
        userIdTextArea = new JTextArea();
        JButton addUser = new JButton("Add User");
        addUser.setActionCommand("user");
        addUser.addActionListener(this);

        // Adding Group with ID
        groupIdTextArea = new JTextArea();
        JButton addGroup = new JButton("Add Group");
        addGroup.setActionCommand("group");
        addGroup.addActionListener(this);


        // Adding button for user windows
        JButton openUser = new JButton("Open User Profile");
        openUser.setActionCommand("open");
        openUser.addActionListener(this);


        //Split pane for the buttons and tree
        JSplitPane pane = new JSplitPane(SwingConstants.VERTICAL);
        pane.setMinimumSize(new Dimension(600, 200));
        GridLayout layout = new GridLayout(5, 2);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.add(userIdTextArea);
        buttonsPanel.add(addUser);
        buttonsPanel.add(groupIdTextArea);
        buttonsPanel.add(addGroup);
        buttonsPanel.setLayout(layout);
        buttonsPanel.add(openUser);
        buttonsPanel.add(new JPanel());

        // New Buttons
        JButton totalUsersButton = new JButton("Show Total Users");
        totalUsersButton.setActionCommand("totalUsers");
        totalUsersButton.addActionListener(this);
        buttonsPanel.add(totalUsersButton);

        JButton totalGroupsButton = new JButton("Show Total Groups");
        totalGroupsButton.setActionCommand("totalGroups");
        totalGroupsButton.addActionListener(this);
        buttonsPanel.add(totalGroupsButton);


        JButton totalTweetsButton = new JButton("Show Total Tweets");
        totalTweetsButton.setActionCommand("totalTweets");
        totalTweetsButton.addActionListener(this);
        buttonsPanel.add(totalTweetsButton);


        JButton positivePercentageOfTweets = new JButton("Show Positive Percentage");
        positivePercentageOfTweets.setActionCommand("posPercent");
        positivePercentageOfTweets.addActionListener(this);
        buttonsPanel.add(positivePercentageOfTweets);


        pane.add(userTreeView);
        pane.add(buttonsPanel);
        add(pane);
    }


    /**
     * Sets up the outer frame for the AdminPanel
     * to sit in to be displayed.
     */
    public void createAndShowGUI() {
        JFrame frame = new JFrame("AdminWindow");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setPanel();
        frame.setContentPane(this);

        frame.setMinimumSize(new Dimension(700, 400));
        frame.pack();
        frame.setVisible(true);
    }


    /**
     * Launches the GUI when called.
     */
    public void startProgram() {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });
    }



    // I designed the button actionPerformed
    // to be in one location of better readability
    // and not extend the panel format.
    @Override
    public void actionPerformed(ActionEvent e) {
        if("group".equals(e.getActionCommand())) {
            String id = groupIdTextArea.getText();
            if(id != null) {
                userTreeView.addGroup(id);
            }
            groupIdTextArea.setText("");
        }


        else if ("user".equals(e.getActionCommand())) {
            String id = userIdTextArea.getText();
            if(id != null) {
                userTreeView.addUser(id);
            }
            userIdTextArea.setText("");
        }

        else if ("open".equals(e.getActionCommand())) {
            userTreeView.viewUser();
        }

        else if("totalUsers".equals(e.getActionCommand())) {
            NumberOfUsersVisitor visitor = new NumberOfUsersVisitor();
            server.getRoot().accept(visitor);
            int totalUsers = visitor.getNumberOfUsers();


            JOptionPane.showMessageDialog(new JFrame(), totalUsers, "Total Users", JOptionPane.INFORMATION_MESSAGE);
        }

        else if("totalGroups".equals(e.getActionCommand())) {
            NumberOfGroupsVisitor visitor = new NumberOfGroupsVisitor();
            server.getRoot().accept(visitor);
            int totalGroups = visitor.getNumberOfGroups();

            JOptionPane.showMessageDialog(new JFrame(), totalGroups, "Total Groups", JOptionPane.INFORMATION_MESSAGE);
        }

        else if("totalTweets".equals(e.getActionCommand())) {
            NumberOfTweetsVisitor visitor = new NumberOfTweetsVisitor();
            server.getRoot().accept(visitor);
            int totalTweets = visitor.getNumberOfTweets();

            JOptionPane.showMessageDialog(new JFrame(), totalTweets, "Total Tweets", JOptionPane.INFORMATION_MESSAGE);
        }

        else if("posPercent".equals(e.getActionCommand())) {
            PositivePercentTweetVisitor visitor = new PositivePercentTweetVisitor();
            server.getRoot().accept(visitor);
            double positivePercent = visitor.getPositiveTweetPercentage();

            JOptionPane.showMessageDialog(new JFrame(), positivePercent, "Pos. Percentage", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
