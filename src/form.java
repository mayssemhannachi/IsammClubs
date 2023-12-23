import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashMap;
import java.util.Map;

public class form extends JFrame {

    private JPanel currentPanel;
    private JComboBox<String> gradeComboBox;
    private JComboBox<String> specialityComboBox;
    private DatabaseHandler databaseHandler;

    private Map<String, String[]> specialitiesMap;

    public form() {
        // Set up the frame
        setTitle("Club Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800, 600));
        setMinimumSize(new Dimension(800, 600));
        setMaximumSize(new Dimension(800, 600));

        // Initialize the current panel to the sign-up panel
        currentPanel = createSignupFormPanel();

        // Add the initial panel to the content pane
        getContentPane().add(currentPanel);

        // Set up the frame
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        // Initialize the database handler
        databaseHandler = new DatabaseHandler();
    }

    private JPanel createSignupFormPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.decode("#66B8CD"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JTextField nameField = new JTextField(30);
        JTextField ageField = new JTextField(30);

        // Create a JComboBox for selecting grades
        String[] grades = {"Licences", "Engineering", "Masters"};
        gradeComboBox = new JComboBox<>(grades);

        // Create a map to store specialities for each grade
        specialitiesMap = new HashMap<>();
        specialitiesMap.put("Licences", new String[]{"IM", "BD", "MIME", "CM","CAV","COCO 3D","COCO JV"});
        specialitiesMap.put("Engineering", new String[]{"IMM"});
        specialitiesMap.put("Masters", new String[]{"DSIR", "IDIAG", "TMAC","IM","PAR","DCA"});

        // Create a JComboBox for selecting speciality
        specialityComboBox = new JComboBox<>(specialitiesMap.get(grades[0]));

        // Create a JComboBox for selecting clubs
        String[] clubs = {"Jeunes Ingénieurs ISAMM", "Music Club ISAMM", "Tunivisions Club ISAMM","Microsoft Club ISAMM","LOG ISAMM Club","ORENDA Junior Entreprise","Engineers Spark ISAMM","Enactus ISAMM","Boubli'Club","Robotique Club ISAMM","ISAMM Events","UGET ISAMM"};
        JComboBox<String> clubsComboBox = new JComboBox<>(clubs);

        JButton joinButton = new JButton("Join");
        JButton cancelButton = new JButton("Cancel");


        Font labelFont = new Font("Arial", Font.PLAIN, 14);
        Font fieldFont = new Font("Arial", Font.PLAIN, 14);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        JLabel nameLabel = new JLabel("Full Name:");
        nameLabel.setFont(labelFont);
        panel.add(nameLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        nameField.setFont(fieldFont);
        panel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        JLabel ageLabel = new JLabel("Age:");
        ageLabel.setFont(labelFont);
        panel.add(ageLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        ageField.setFont(fieldFont);
        panel.add(ageField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        JLabel gradeLabel = new JLabel("Grade:");
        gradeLabel.setFont(labelFont);
        panel.add(gradeLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gradeComboBox.setFont(fieldFont);
        panel.add(gradeComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.EAST;
        JLabel specialityLabel = new JLabel("Speciality:");
        specialityLabel.setFont(labelFont);
        panel.add(specialityLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        specialityComboBox.setFont(fieldFont);
        panel.add(specialityComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.EAST;
        JLabel clubsLabel = new JLabel("Clubs:");
        clubsLabel.setFont(labelFont);
        panel.add(clubsLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        clubsComboBox.setFont(fieldFont);
        panel.add(clubsComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        joinButton.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(joinButton, gbc);

        gbc.gridy = 7;
        gbc.gridwidth = 2;

        // Add label on top of the "Cancel" label
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JLabel cancelLabel = new JLabel("Do you want to cancel?");
        cancelLabel.setFont(labelFont);
        panel.add(cancelLabel, gbc);

        // Move the "Cancel" button below the label
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        panel.add(cancelButton, gbc);

        // Add an ItemListener to the gradeComboBox to update the specialityComboBox
        gradeComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    // Update the specialityComboBox based on the selected grade
                    String selectedGrade = (String) gradeComboBox.getSelectedItem();
                    String[] specialities = specialitiesMap.get(selectedGrade);
                    specialityComboBox.setModel(new DefaultComboBoxModel<>(specialities));
                }
            }
        });

        joinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add code to handle join logic
                String fullName = nameField.getText().trim();
                String ageText = ageField.getText().trim();
                String selectedSpeciality = (String) specialityComboBox.getSelectedItem();
                String selectedClub = (String) clubsComboBox.getSelectedItem();
                String selectedGrade = (String) gradeComboBox.getSelectedItem();
        
                try {
                    // Fetch user_id from the database based on full_name
                    int userId = databaseHandler.getUserIdByFullName(fullName);
        
                    // Try to parse age as an integer
                    int age = Integer.parseInt(ageText);
        
                    if (userId != -1) {
                        // User exists, update the information
                        databaseHandler.updateUserData(fullName, age, selectedGrade, selectedSpeciality, selectedClub);
                    } else {
                        // User does not exist, insert new information
                        databaseHandler.insertUserData(fullName, "", "", age, selectedGrade, selectedSpeciality, selectedClub);
                    }
        
                    // For demonstration purposes, just display a message
                    JOptionPane.showMessageDialog(null, "Joined successfully!");
                } catch (NumberFormatException ex) {
                    // Handle the case where age is not a valid integer
                    JOptionPane.showMessageDialog(null, "Please enter a valid age (numeric value).", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        
        

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add code to handle cancel logic
                dispose();
                // For demonstration purposes, return to the login/signup form
                new useradmin();
            }
        });

        return panel;
    }

    // Override the form's dispose method to close the database connection
    @Override
    public void dispose() {
        super.dispose();
        databaseHandler.closeConnection();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new form());
    }
}
