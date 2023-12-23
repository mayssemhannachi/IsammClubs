import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class userlogin extends JFrame {

    private JPanel currentPanel;
    private DatabaseHandler databaseHandler;

    public userlogin() {
        // Set up the frame
        setTitle("UserLogin");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800, 600));
        setMinimumSize(new Dimension(800, 600));
        setMaximumSize(new Dimension(800, 600));

        // Initialize the database handler
        databaseHandler = new DatabaseHandler();

        // Initialize the current panel to the login panel
        currentPanel = createLoginPanel();

        // Add the initial panel to the content pane
        getContentPane().add(currentPanel);

        // Set up the frame
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel createLoginPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.decode("#66B8CD"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JTextField emailField = new JTextField(30);
        JPasswordField passwordField = new JPasswordField(30);

        JButton loginButton = new JButton("Login");
        JButton signupButton = new JButton("Sign UP");
        JButton backToPortalButton = new JButton("Back to Isamm's Club Portal");

        Font labelFont = new Font("Arial", Font.PLAIN, 14);
        Font fieldFont = new Font("Arial", Font.PLAIN, 14);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

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

        // Add label on top of the "Don't have an account?" label
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JLabel registerLabel = new JLabel("Don't have an account?");
        registerLabel.setFont(labelFont);
        panel.add(registerLabel, gbc);

        // Move the "Sign UP" button below the label
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        panel.add(signupButton, gbc);

        // New button added below the "Sign UP" button
        gbc.gridy = 6;
        panel.add(backToPortalButton, gbc);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText().trim();
                char[] passwordChars = passwordField.getPassword();
                String password = new String(passwordChars);

                // Check if the entered email and password exist in the database
                if (databaseHandler.checkUserCredentials(email, password)) {
                    JOptionPane.showMessageDialog(null, "You are IN!");
                    dispose();
                    new form(); // Open the form page

                    // For demonstration purposes, switch to another panel (you can replace this logic)
                    switchPanel(createAnotherPanel());
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid email or password. Please try again.");
                }

                // Clear the password field for security reasons
                passwordField.setText("");
            }
        });

        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new signup();
            }
        });

        // ActionListener for the new "Back to Isamm's Club Portal" button
        backToPortalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new useradmin();
            }
        });

        return panel;
    }

    private JPanel createAnotherPanel() {
        JPanel panel = new JPanel();
        // Add components to the panel for the "another" page

        return panel;
    }

    private void switchPanel(JPanel newPanel) {
        // Remove the current panel
        getContentPane().remove(currentPanel);

        // Set the new panel as the current panel
        currentPanel = newPanel;

        // Add the new panel to the content pane
        getContentPane().add(currentPanel);

        // Refresh the frame
        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new userlogin());
    }
}

