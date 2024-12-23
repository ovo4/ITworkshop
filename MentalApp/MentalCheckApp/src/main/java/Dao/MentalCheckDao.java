package Dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.MentalCheckResult;

public class MentalCheckDao {
    private static final String DB_URL = "jdbc:h2:~/desktop/workspeace/ITworkshop/test/mentalcheckDB";
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "";
    
    public List<MentalCheckResult> getPastResults(int userId) {
        List<MentalCheckResult> results = new ArrayList<>();
        String query = "SELECT score, date FROM mental_check_results WHERE user_id = ? ORDER BY date DESC LIMIT 10";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int score = resultSet.getInt("score");
                Date date = resultSet.getDate("date");
                results.add(new MentalCheckResult(score, date));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }
    
    public ResultSet getMentalCheckResultsByUserId(int userId) {
        String query = "SELECT * FROM mental_check_results WHERE user_id = ? ORDER BY date DESC";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);
            return statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    

    public MentalCheckDao() {
        try {
            // H2ドライバを明示的にロード
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Error: H2 Driver not found.");
            e.printStackTrace();
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    public void saveMentalCheckResult(String userId, int score) {
        String query = "INSERT INTO mental_check_results (user_id, score) VALUES (?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, userId);
            statement.setInt(2, score);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
