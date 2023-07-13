package com.example.Dao;

import com.example.Bean.Vacation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Cyan
 * @ date 2023/6/14
 */
public class VacationDAO {
    private  Connection conn;

    public VacationDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "root", "root");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void addVacation(Vacation vacation) throws SQLException {
        String sql = "INSERT INTO vacation (employee_id, start_date, end_date, type, reason) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, vacation.getEmployeeId());
        stmt.setDate(2, vacation.getStartDate());
        stmt.setDate(3, vacation.getEndDate());
        stmt.setString(4, vacation.getType());
        stmt.setString(5, vacation.getReason());
        stmt.executeUpdate();
    }

    public void deleteVacation(int id) throws SQLException {
        String sql = "DELETE FROM vacation WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }

    public void updateVacation(Vacation vacation) throws SQLException {
        String sql = "UPDATE vacation SET employee_id = ?, start_date = ?, end_date = ?, type = ?, reason = ? WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, vacation.getEmployeeId());
        stmt.setDate(2, vacation.getStartDate());
        stmt.setDate(3, vacation.getEndDate());
        stmt.setString(4, vacation.getType());
        stmt.setString(5, vacation.getReason());
        stmt.setInt(6, vacation.getId());
        stmt.executeUpdate();
    }

    public Vacation getVacationById(int id) throws SQLException {
        String sql = "SELECT * FROM vacation WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            Vacation vacation = new Vacation();
            vacation.setId(rs.getInt("id"));
            vacation.setEmployeeId(rs.getInt("employee_id"));
            vacation.setStartDate(rs.getDate("start_date"));
            vacation.setEndDate(rs.getDate("end_date"));
            vacation.setType(rs.getString("type"));
            vacation.setReason(rs.getString("reason"));
            return vacation;
        } else {
            return null;
        }
    }

    public List<Vacation> getAllVacations() throws SQLException {
        String sql = "SELECT * FROM vacation";
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        List<Vacation> vacations = new ArrayList<>();
        while (rs.next()) {
            Vacation vacation = new Vacation();
            vacation.setId(rs.getInt("id"));
            vacation.setEmployeeId(rs.getInt("employee_id"));
            vacation.setStartDate(rs.getDate("start_date"));
            vacation.setEndDate(rs.getDate("end_date"));
            vacation.setType(rs.getString("type"));
            vacation.setReason(rs.getString("reason"));
            vacations.add(vacation);
        }
        return vacations;
    }
    public List<Double> getSumAndAvgDays(int employeeId) throws SQLException {
        // 执行查询
        // 执行查询
        String sql = "SELECT SUM(DATEDIFF(end_date, start_date) + 1) AS totalDays, AVG(DATEDIFF(end_date, start_date) + 1) AS averageDays FROM vacation WHERE employee_id = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, employeeId);
        ResultSet resultSet = statement.executeQuery();
        // 处理查询结果
        if (resultSet.next()) {
//            System.out.println("getSumAndAvgDays不为空");
            List<Double> vacation_info = new ArrayList<>();
            int totalDays = resultSet.getInt("totalDays");
//            double totalDays = Days;
            vacation_info.add((double) totalDays);
            double averageDays = resultSet.getDouble("averageDays");
            vacation_info.add(averageDays);
            return vacation_info;
        }else {
            return null;
        }
    }
    public int getEmployeeIdById(int id) throws SQLException {
        String sql = "SELECT employee_id FROM vacation WHERE id = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        // 处理查询结果
        if (resultSet.next()) {
            return resultSet.getInt("employee_id");
        }else {
            return 0;
        }
    }
}
