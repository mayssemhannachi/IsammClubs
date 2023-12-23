import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class signup extends JFrame {

    private JPanel currentPanel;


    public signup() {
        // Set up the frame
        setTitle("SignUp");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800, 600));
        setMinimumSize(new Dimension(800, 600));
        setMaximumSize(new Dimension(800, 600));

        // Initialize the current panel to the sign-up panel
        currentPanel = createSignUpPanel();

        // Add the initial panel to the content pane
        getContentPane().add(currentPanel);

        // Set up the frame
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel createSignUpPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.decode("#66B8CD"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JTextField nameField = new JTextField(30);
        JTextField emailField = new JTextField(30);
        JPasswordField passwordField = new JPasswordField(30);

        JButton signUpButton = new JButton("Sign Up");
        JButton backButton = new JButton("Back to Login");

        Font labelFont = new Font("Arial", Font.PLAIN, 14);
        Font fieldFont = new Font("Arial", Font.PLAIN, 14);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(labelFont);
        panel.add(nameLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        nameField.setFont(fieldFont);
        panel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(labelFont);
        panel.add(emailLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        emailField.setFont(fieldFont);
        panel.add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(labelFont);
        panel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        passwordField.setFont(fieldFont);
        panel.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        signUpButton.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(signUpButton, gbc);

        gbc.gridy = 5;
        gbc.gridwidth = 2;

        // Add label on top of the "Back to Login" label
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JLabel backToLoginLabel = new JLabel("Already have an account?");
        backToLoginLabel.setFont(labelFont);
        panel.add(backToLoginLabel, gbc);

        // Move the "Back to Login" button below the label
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        panel.add(backButton, gbc);

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Simulate successful account creation
                String name = nameField.getText().trim();
                String email = emailField.getText().trim();
                String password = new String(passwordField.getPassword());
        
                if (isValidEmail(email)) {
                    // Add code to handle the sign-up logic (e.g., store user information)
                    DatabaseHandler databaseHandler = new DatabaseHandler();
                    databaseHandler.insertUserSignupData(name, email, password);
                    databaseHandler.closeConnection();
        
                    JOptionPane.showMessageDialog(null, "Account created successfully!");
        
                    // For demonstration purposes, switch to another panel (you can replace this logic)
                    dispose();
                    new userlogin();
        
                    // Show another message asking the user to log in again
                    JOptionPane.showMessageDialog(null, "Now, please log in again.");
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid email address. Please enter a valid email.");
                }
            }
        });   

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new userlogin();
            }
        });

        return panel;
    }

    private boolean isValidEmail(String email) {
        // Use a regular expression for basic email validation
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new signup());
    }
}
