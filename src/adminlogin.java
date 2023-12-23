import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// Class representing the admin login functionality
public class adminlogin extends JFrame {

    private JPanel currentPanel;
    private Connection connection;

    // Constructor for initializing the frame and database connection
    public adminlogin() {
        // Initialize the database connection
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:/Users/macbookair/Documents/IsammClubs/PortalDB/portal.db");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to connect to the database.");
            System.exit(1);
        }

        // Initialize the current panel to the login panel
        currentPanel = createLoginPanel();

        // Add the initial panel to the content pane
        getContentPane().add(currentPanel);

        // Set up the frame
        setTitle("Admin Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800, 600));
        setMinimumSize(new Dimension(800, 600));
        setMaximumSize(new Dimension(800, 600));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Method to create the login panel with UI components
    private JPanel createLoginPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.decode("#66B8CD"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JTextField emailField = new JTextField(30);
        JPasswordField passwordField = new JPasswordField(30);

        JButton loginButton = new JButton("Login as Admin");
        JButton backButton = new JButton("Back to Isamm's Club Portal");

        Font labelFont = new Font("Arial", Font.PLAIN, 14);
        Font fieldFont = new Font("Arial", Font.PLAIN, 14);

        // Adding components to the panel with layout constraints
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(labelFont);
        panel.add(emailLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        emailField.setFont(fieldFont);
        panel.add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(labelFont);
        panel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        passwordField.setFont(fieldFont);
        panel.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        loginButton.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(loginButton, gbc);

        gbc.gridy = 4;
        gbc.gridwidth = 2;
        panel.add(backButton, gbc);

        // Adding action listeners to buttons
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get email and password from text fields
                String email = emailField.getText().trim();
                char[] passwordChars = passwordField.getPassword();
                String password = new String(passwordChars);

                // Validate admin credentials
                if (isValidAdminCredentials(email, password)) {
                    JOptionPane.showMessageDialog(null, "Admin login successful!");
                    dispose();  // Close the current frame

                    // Open the AdminDashboardPanel
                    SwingUtilities.invokeLater(() -> new AdminDashboardPanel().setVisible(true));
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid admin credentials. Please enter valid credentials.");
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                // Assuming UserAdmin is the class for the user portal
                new useradmin();
            }
        });

        return panel;
    }

    // Method to validate admin credentials against the database
    private boolean isValidAdminCredentials(String email, String password) {
        try {
            String sql = "SELECT * FROM admin WHERE email = ? AND password = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, email);
                statement.setString(2, password);

                ResultSet resultSet = statement.executeQuery();
                return resultSet.next();  // Return true if there is a match in the database
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;  // Return false in case of an exception or no match
        }
    }

    // Main method to launch the application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new adminlogin().setVisible(true));
    }
}

