package com.example.Dao;

import com.example.Bean.Attendance;
import com.example.Bean.EventLog;
import com.example.Bean.TodayAttendance;


import java.sql.*;
import java.util.ArrayList;
//import java.util.Date;
import java.util.List;

/**
 * @author Cyan
 * @ date 2023/6/14
 */
public class AttendanceDAO {
    private Connection conn;
    public AttendanceDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "root", "root");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }


    public void addAttendance(Attendance attendance) throws SQLException {
        String sql = "INSERT INTO attendance (employee_id, attendance_date, attendance_time, attendance_type) VALUES (?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, attendance.getEmployeeId());
        stmt.setDate(2, new java.sql.Date(attendance.getAttendanceDate().getTime()));
        stmt.setTime(3, attendance.getAttendanceTime());
        stmt.setString(4, attendance.getAttendanceType());
        stmt.executeUpdate();
    }

    public void deleteAttendance(int id) throws SQLException {
        String sql = "DELETE FROM attendance WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }

    public Attendance getAttendanceById(int id) throws SQLException {
        String sql = "SELECT * FROM attendance WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            Attendance attendance = new Attendance();
            attendance.setId(rs.getInt("id"));
            attendance.setEmployeeId(rs.getInt("employee_id"));
            attendance.setAttendanceDate(rs.getDate("attendance_date"));
            attendance.setAttendanceTime(rs.getTime("attendance_time"));
            attendance.setAttendanceType(rs.getString("attendance_type"));
            return attendance;
        } else {
            return null;
        }
    }

    public List<Attendance> getAllAttendances() throws SQLException {
        String sql = "SELECT * FROM attendance";
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        List<Attendance> attendances = new ArrayList<>();
        while (rs.next()) {
            Attendance attendance = new Attendance();
            attendance.setId(rs.getInt("id"));
            attendance.setEmployeeId(rs.getInt("employee_id"));
            attendance.setAttendanceDate(rs.getDate("attendance_date"));
            attendance.setAttendanceTime(rs.getTime("attendance_time"));
            attendance.setAttendanceType(rs.getString("attendance_type"));
            attendances.add(attendance);
        }
        return attendances;
    }

    public List<Attendance> searchAttendances(int employeeId, Date startTime, Date endTime) throws SQLException {
        String sql = "SELECT * FROM attendance WHERE 1=1";
        List<Object> params = new ArrayList<>();

        if (employeeId != 0) {
            sql += " AND employee_id = ?";
            params.add(employeeId);
        }

        if (startTime != null) {
            java.sql.Date startDate = new java.sql.Date(startTime.getTime());
            sql += " AND attendance_date >= ?";
            params.add(startDate);
        }

        if (endTime != null) {
            java.sql.Date endDate = new java.sql.Date(endTime.getTime());
            sql += " AND attendance_date <= ?";
            params.add(endDate);
        }
        PreparedStatement stmt = conn.prepareStatement(sql);
        for (int i = 0; i < params.size(); i++) {
            stmt.setObject(i + 1, params.get(i));
        }

        ResultSet rs = stmt.executeQuery();
        List<Attendance> attendances = new ArrayList<>();
        while (rs.next()) {
            Attendance attendance = new Attendance();
            attendance.setId(rs.getInt("id"));
            attendance.setEmployeeId(rs.getInt("employee_id"));
            attendance.setAttendanceDate(rs.getDate("attendance_date"));
            attendance.setAttendanceTime(rs.getTime("attendance_time"));
            attendance.setAttendanceType(rs.getString("attendance_type"));
            attendances.add(attendance);
        }
        return attendances;
    }

    public List<TodayAttendance> searchTodayAttendances(Date today) throws SQLException {
        String sql = "SELECT employee_id, attendance_time FROM attendance WHERE attendance_date = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
//        java.sql.Date sqlDate = java.sql.Date.valueOf(utilDate.toString());
        stmt.setDate(1, today);
        ResultSet rs = stmt.executeQuery();
        List<TodayAttendance> todayAttendances = new ArrayList<>();
        while (rs.next()) {
            TodayAttendance todayAttendance = new TodayAttendance();
//            todayAttendance.setEmployeeId(rs.getInt(employeeDAO.get));
            todayAttendance.setEmployeeId(rs.getInt("employee_id"));
            todayAttendance.setAttendanceTime(rs.getTime("attendance_time"));
//            attendance.setAttendanceTime(rs.getTime("attendance_time"));
//            attendance.setAttendanceType(rs.getString("attendance_type"));
            todayAttendances.add(todayAttendance);
        }
        return todayAttendances;
    }
    public int getEmployeeIdById(int id) throws SQLException {
        String sql = "SELECT employee_id from attendance WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        if(rs.next()){
            return rs.getInt("employee_id");
        }
        return 0;
    }
}
