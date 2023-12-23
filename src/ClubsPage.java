import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class ClubsPage extends JFrame {

    public ClubsPage() {
        // Set up the frame
        setTitle("Isamm Clubs");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800, 600));
        setMinimumSize(new Dimension(800, 600));
        setMaximumSize(new Dimension(800, 600));

        // Initialize the current panel to the clubs page
        JPanel clubsPanel = createClubsPanel();

        // Add the clubs panel to the content pane
        getContentPane().add(clubsPanel);

        // Set up the frame
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public JPanel createClubsPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        panel.setBackground(Color.decode("#66B8CD"));

        // Add clubs logos to the panel
        for (int i = 1; i <= 12; i++) {
            final int clubIndex = i; // Make i effectively final
            JButton clubButton = createClubButton("/Users/macbookair/Documents/IsammClubs/pics/" + i, clubIndex);

            // Resize the image to a smaller size
            ImageIcon icon = (ImageIcon) clubButton.getIcon();
            Image image = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            clubButton.setIcon(new ImageIcon(image));

            gbc.gridx = i % 4;  // Column
            gbc.gridy = i / 4;  // Row
            panel.add(clubButton, gbc);

            // Add "Go Back to Portal" button in the center of the fourth line
            if (i == 12) {
                JButton goBackButton = new JButton("Go Back to Portal");
                goBackButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                        new useradmin();
            }
                    });

                gbc.gridx = 1;  // Center column
                gbc.gridy = 3;  // Fourth row
                gbc.gridwidth = 2;  // Span two columns
                panel.add(goBackButton, gbc);
            }
        }

        return panel;
    }

    private JButton createClubButton(String imagePathPrefix, int clubIndex) {
        JButton clubButton = new JButton();

        try {
            // Try to load the PNG image
            BufferedImage image = ImageIO.read(new File(imagePathPrefix + ".png"));
            clubButton.setIcon(new ImageIcon(image));
        } catch (IOException e) {
            try {
                // If PNG loading fails, try loading the JPEG image
                BufferedImage image = ImageIO.read(new File(imagePathPrefix + ".jpeg"));
                clubButton.setIcon(new ImageIcon(image));
            } catch (IOException ex) {
                // Handle the exception (e.g., show an error message)
                ex.printStackTrace();
            }
        }

        clubButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle button click (open the club's website)
                openClubWebsite(clubIndex);
            }
        });

        return clubButton;
    }

    private void openClubWebsite(int clubIndex) {
        // Replace the following URL with the actual website URLs for each club
        String[] clubWebsites = {
                "https://tn.linkedin.com/in/clubj2i",
                "https://www.facebook.com/ISAMMmc/",
                "https://www.facebook.com/ClubTunivisionsISAMM/",
                "https://fr.linkedin.com/company/isamm-microsoft-club",
                "https://tn.linkedin.com/company/log-isamm",
                "https://orendaje.com/en/",
                "https://tn.linkedin.com/company/engineers-spark-isamm?trk=public_profile_experience-item_profile-section-card_image-click",
                "https://tn.linkedin.com/in/enactus-isamm-854ab1195",
                "https://www.facebook.com/p/BoubliClub-100087931522321/",
                "https://www.facebook.com/robotiqueisamm/",
                "https://www.facebook.com/isamm.events/",
                "https://www.facebook.com/groups/uget.isamm/",
        };

        if (clubIndex >= 1 && clubIndex <= clubWebsites.length) {
            try {
                URI uri = new URI(clubWebsites[clubIndex - 1]);
                Desktop.getDesktop().browse(uri);
            } catch (IOException | URISyntaxException ex) {
                // Handle exceptions (e.g., show an error message)
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ClubsPage());
    }
}

