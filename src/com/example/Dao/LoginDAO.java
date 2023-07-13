package com.example.Dao;

import com.example.Bean.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Cyan
 * @ date 2023/6/21
 */
public class LoginDAO {
    private Connection conn;

    public LoginDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "root", "root");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public Boolean haveUser(String username, String password) {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM user WHERE username = ? AND password = ?");
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}

