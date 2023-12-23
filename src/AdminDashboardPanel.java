import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDashboardPanel extends JFrame {

    private JPanel currentPanel;
    private Connection connection;

    private JComboBox<String> filterComboBox;
    private JTextField filterValueField;
    private JButton applyFilterButton;
    private JTextArea resultTextArea;

    public AdminDashboardPanel() {
        // Initialize the database connection
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:/Users/macbookair/Documents/IsammClubs/PortalDB/portal.db");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to connect to the database.");
            System.exit(1);
        }

        // Set up the frame
        setTitle("Admin Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800, 600));
        setMinimumSize(new Dimension(800, 600));
        setMaximumSize(new Dimension(800, 600));

        // Initialize the current panel to the admin dashboard panel
        currentPanel = createAdminDashboardPanel();

        // Add the initial panel to the content pane
        getContentPane().add(currentPanel);

        // Set up the frame
        pack();
        setLocationRelativeTo(null);
    }

    // Method to create the main Admin Dashboard panel
    private JPanel createAdminDashboardPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.decode("#66B8CD"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Components for filtering
        filterComboBox = new JComboBox<>(new String[]{"Club", "Grade", "Speciality"});
        filterValueField = new JTextField(15);
        applyFilterButton = new JButton("Apply Filter");
        resultTextArea = new JTextArea(20, 50);
        resultTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultTextArea);

        // Adding components to the panel with layout constraints
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(new JLabel("Filter By:"), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(filterComboBox, gbc);

        gbc.gridx = 2;
        panel.add(new JLabel("Filter Value:"), gbc);

        gbc.gridx = 3;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(filterValueField, gbc);

        gbc.gridx = 4;
        panel.add(applyFilterButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 5;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(scrollPane, gbc);

        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton backButton = new JButton("Back to User Admin");
        panel.add(backButton, gbc);

        // Adding action listeners to buttons
        applyFilterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get selected filter type and value
                String filterType = (String) filterComboBox.getSelectedItem();
                String filterValue = filterValueField.getText().trim();

                // Build query based on filter type and value
                String query = buildQuery(filterType, filterValue);

                // Display results or show an error message
                if (query != null) {
                    displayResults(query);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid filter type or value.");
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close the current frame and open the User Admin frame
                dispose();
                // You should handle closing the database connection here
                // Assuming UserAdmin is the class for the user portal
                new useradmin();
            }
        });

        return panel;
    }

    // Method to build SQL query based on filter type
    private String buildQuery(String filterType, String filterValue) {
        switch (filterType) {
            case "Club":
                return "SELECT full_name, email FROM users WHERE club = ?";
            case "Grade":
                return "SELECT full_name, email FROM users WHERE grade = ?";
            case "Speciality":
                return "SELECT full_name, email FROM users WHERE speciality = ?";
            default:
                return null;
        }
    }

    // Method to execute query and display results
    private void displayResults(String query) {
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            // Set the filter value in the prepared statement
            statement.setString(1, filterValueField.getText().trim());

            // Execute the query and process the results
            ResultSet resultSet = statement.executeQuery();
            StringBuilder resultText = new StringBuilder("User Names and Emails:\n");

            while (resultSet.next()) {
                resultText.append(resultSet.getString("full_name"))
                        .append(" - ")
                        .append(resultSet.getString("email"))
                        .append("\n");
            }

            // Display results in the text area
            resultTextArea.setText(resultText.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Main method to launch the application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AdminDashboardPanel().setVisible(true));
    }
}


