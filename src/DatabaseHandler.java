import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// Class responsible for handling database operations
public class DatabaseHandler {

    // JDBC URL for SQLite database
    private static final String JDBC_URL = "jdbc:sqlite:/Users/macbookair/Documents/IsammClubs/PortalDB/portal.db";
    private Connection connection;

    // Constructor for initializing the database connection
    public DatabaseHandler() {
        try {
            connection = DriverManager.getConnection(JDBC_URL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to insert user signup data into the database
    public void insertUserSignupData(String full_name, String email, String password) {
        String insertSQL = "INSERT INTO users (id, full_name, email, password) VALUES (?,?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
            preparedStatement.setString(2, full_name);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, password);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to check user credentials in the database
    public boolean checkUserCredentials(String email, String password) {
        String query = "SELECT * FROM users WHERE email = ? AND password = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next(); // Returns true if a matching user is found
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Method to get user ID by full name from the database
    public int getUserIdByFullName(String fullName) {
        String query = "SELECT id FROM users WHERE full_name = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, fullName);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Return -1 if user_id is not found
    }

    // Method to update user data in the database
    public void updateUserData(String full_name, int age, String grade, String speciality, String club) {
        String updateSQL = "UPDATE users SET age=?, grade=?, speciality=?, club=? WHERE full_name=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateSQL)) {
            preparedStatement.setInt(1, age);
            preparedStatement.setString(2, grade);
            preparedStatement.setString(3, speciality);
            preparedStatement.setString(4, club);
            preparedStatement.setString(5, full_name);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to insert user data into the database
    public void insertUserData(String full_name, String email, String password, int age, String grade, String speciality, String club) {
        String insertSQL = "INSERT INTO users (full_name, email, password, age, grade, speciality, club) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
            preparedStatement.setString(1, full_name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, password);
            preparedStatement.setInt(4, age);
            preparedStatement.setString(5, grade);
            preparedStatement.setString(6, speciality);
            preparedStatement.setString(7, club);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to check if additional information exists for a user in the database
    public boolean hasAdditionalInfo(int userId) {
        String query = "SELECT * FROM user_additional_info WHERE user_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next(); // Returns true if additional information exists
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Method to close the database connection
    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


