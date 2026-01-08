
package com.example.webapp.dao;

import com.example.webapp.model.User;
import java.sql.*;
import java.util.Properties;

public class UserDao {

    public static Connection getConnection(Properties props) throws Exception {
        return DriverManager.getConnection(
                props.getProperty("db.url"),
                props.getProperty("db.username"),
                props.getProperty("db.password")
        );
    }

    public static void save(User user, Properties props) {
        String sql = "INSERT INTO users(name, email) VALUES (?, ?)";
        try (Connection con = getConnection(props);
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
