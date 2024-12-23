package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;

public class UserDao {

    private Connection connection;

    public UserDao() {
        try {
            // H2ドライバを明示的にロード
            Class.forName("org.h2.Driver");
            
            // 接続設定
            connection = DriverManager.getConnection("jdbc:h2:~/desktop/workspeace/ITworkshop/test/mentalcheckDB", "sa", "");
            if (connection == null) {
                System.out.println("Failed to make connection!");
            } else {
                System.out.println("Connection established successfully.");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Error: H2 Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Error: Unable to establish a database connection.");
            e.printStackTrace();
        }
    }

    public User getUserByName(String name) {
        try {
            String query = "SELECT * FROM users WHERE name = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setUser_id(resultSet.getInt("user_id"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(resultSet.getString("user_role"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void registerUser(User user) {
        try {
            String query = "INSERT INTO users (name, password, user_role) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, user.getName());
            statement.setString(2, user.getPassword());
            statement.setString(3, "USER");  // デフォルトは一般ユーザー
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // メンタルチェック結果をデータベースに保存
    public void saveMentalCheckResult(int userId, int score) {
        try {
            String query = "INSERT INTO mental_check_results (user_id, score) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userId);
            statement.setInt(2, score);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 質問を全て取得
    public ResultSet getAllQuestions() {
        try {
            String query = "SELECT * FROM mental_check_questions";
            PreparedStatement statement = connection.prepareStatement(query);
            return statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
