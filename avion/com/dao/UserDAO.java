package avion.com.dao;

import avion.com.models.User;
import java.sql.*;

public class UserDAO {
    private Connection connection;

    public UserDAO(Connection connection) {
        this.connection = connection;
    }

    public User login(String email, String password) throws SQLException {
        String query = "SELECT * FROM utilisateur WHERE email = ? AND password = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, email);
        stmt.setString(2, password);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            return new User(
                rs.getInt("id"),
                rs.getString("nom"),
                rs.getString("email"),
                rs.getString("password"),
                rs.getString("role")
            );
        }
        return null;
    }
}
