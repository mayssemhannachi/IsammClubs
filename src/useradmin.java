import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Class representing the main user admin interface
public class useradmin extends JFrame {

    // Constructor for creating the user admin interface
    public useradmin() {

        // Setting up the frame properties
        setTitle("Isamm Clubs Portal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800, 600));
        setMinimumSize(new Dimension(800, 600));
        setMaximumSize(new Dimension(800, 600));

        // Creating the main background panel
        JPanel backgroundPanel = new JPanel();
        backgroundPanel.setLayout(new GridLayout(1, 2));
        getContentPane().add(backgroundPanel);

        // Creating the left panel for university logo, text, and "Learn More" button
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(Color.WHITE);
        leftPanel.setLayout(new BorderLayout());

        // Loading and resizing the university logo
        ImageIcon logoIcon = new ImageIcon("/Users/macbookair/Documents/IsammClubs/pics/isamm_logo-removebg-preview (1).png");
        Image logoImage = logoIcon.getImage();
        Image resizedLogoImage = logoImage.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        ImageIcon resizedLogoIcon = new ImageIcon(resizedLogoImage);
        JLabel logoLabel = new JLabel(resizedLogoIcon);
        logoLabel.setHorizontalAlignment(JLabel.CENTER);

        // Adding "Learn More" button under the image
        JButton learnMoreButton = new JButton("Learn more about our clubs!");
        learnMoreButton.setFont(new Font("Arial", Font.PLAIN, 14));
        learnMoreButton.setPreferredSize(new Dimension(120, 30));
        learnMoreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new ClubsPage();
            }
        });

        // Adding elements to the left panel
        leftPanel.add(logoLabel, BorderLayout.CENTER);
        leftPanel.add(learnMoreButton, BorderLayout.SOUTH);

        // Creating the right panel for buttons and text
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(Color.decode("#66B8CD"));
        rightPanel.setLayout(new GridLayout(4, 1));

        // Creating labels for the right panel
        JLabel loginLabel = new JLabel("Log In As..");
        loginLabel.setHorizontalAlignment(JLabel.CENTER);
        Font loginFont = new Font("Arial", Font.PLAIN, 20);
        loginLabel.setFont(loginFont);

        JLabel welcomeLabel = new JLabel("Welcome to Isamm's");
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
        Font welcomeFont = new Font("Arial", Font.BOLD, 35);
        welcomeLabel.setFont(welcomeFont);

        JLabel welcomeLabel2 = new JLabel("Club Portal");
        welcomeLabel2.setHorizontalAlignment(JLabel.CENTER);
        Font welcomeFont2 = new Font("Arial", Font.BOLD | Font.ITALIC | Font.ROMAN_BASELINE, 40);
        welcomeLabel2.setFont(welcomeFont2);

        // Creating buttons for the right panel
        JButton adminButton = new JButton("Admin");
        JButton userButton = new JButton("User");

        // Adding panels to the frame
        backgroundPanel.add(leftPanel);
        backgroundPanel.add(rightPanel);

        // Adding elements to the right panel
        rightPanel.add(welcomeLabel);
        rightPanel.add(welcomeLabel2);
        rightPanel.add(loginLabel);

        // Creating a panel for buttons to be in a row
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(Color.decode("#66B8CD"));
        buttonPanel.add(adminButton);
        buttonPanel.add(userButton);
        rightPanel.add(buttonPanel);

        // Adding action listeners to the buttons
        userButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new userlogin();
            }
        });

        adminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new adminlogin();
            }
        });

        // Making the frame visible
        setLayout(new GridLayout(1, 1, 0, 0));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Main method to launch the application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new useradmin());
    }
}

