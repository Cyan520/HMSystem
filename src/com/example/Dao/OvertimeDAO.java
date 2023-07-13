package com.example.Dao;


import com.example.Bean.Overtime;
import com.example.Bean.Overtime_Summary;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * @author Cyan
 * @ date 2023/6/14
 */
public class OvertimeDAO {
    private Connection conn;

    public OvertimeDAO(Connection conn) {
        this.conn = conn;
    }

    public void addOvertime(Overtime overtime) throws SQLException {
        String sql = "INSERT INTO overtime (employee_id, overtime_date, overtime_hours) VALUES (?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, overtime.getEmployeeId());
        stmt.setDate(2, new java.sql.Date(overtime.getOvertimeDate().getTime()));
        stmt.setFloat(3, overtime.getOvertimeHours());
        stmt.executeUpdate();
    }

    public void deleteOvertime(int id) throws SQLException {
        String sql = "DELETE FROM overtime WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }

    public Overtime getOvertimeById(int id) throws SQLException {
        String sql = "SELECT * FROM overtime WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            Overtime overtime = new Overtime();
            overtime.setId(rs.getInt("id"));
            overtime.setEmployeeId(rs.getInt("employee_id"));
            overtime.setOvertimeDate(rs.getDate("overtime_date"));
            overtime.setOvertimeHours(rs.getFloat("overtime_hours"));
            return overtime;
        } else {
            return null;
        }
    }

    public List<Overtime> getAllOvertimes() throws SQLException {
        String sql = "SELECT * FROM overtime";
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        List<Overtime> overtimes = new ArrayList<>();
        while (rs.next()) {
            Overtime overtime = new Overtime();
            overtime.setId(rs.getInt("id"));
            overtime.setEmployeeId(rs.getInt("employee_id"));
            overtime.setOvertimeDate(rs.getDate("overtime_date"));
            overtime.setOvertimeHours(rs.getFloat("overtime_hours"));
            overtimes.add(overtime);
        }
        return overtimes;
    }

    public List<Overtime> searchOvertimes(int employeeId, Date startTime, Date endTime) throws SQLException {
        String sql = "SELECT * FROM overtime WHERE 1=1";
        List<Object> params = new ArrayList<>();

        if (employeeId != 0) {
            sql += " AND employee_id = ?";
            params.add(employeeId);
        }

        if (startTime != null) {
            java.sql.Date startDate = new java.sql.Date(startTime.getTime());
            sql += " AND overtime_date >= ?";
            params.add(startDate);
        }

        if (endTime != null) {
            java.sql.Date endDate = new java.sql.Date(endTime.getTime());
            sql += " AND overtime_date <= ?";
            params.add(endDate);
        }
        PreparedStatement stmt = conn.prepareStatement(sql);
        for (int i = 0; i < params.size(); i++) {
            stmt.setObject(i + 1, params.get(i));
        }

        ResultSet rs = stmt.executeQuery();
        List<Overtime> overtimes = new ArrayList<>();
        while (rs.next()) {
            Overtime overtime = new Overtime();
            overtime.setId(rs.getInt("id"));
            overtime.setEmployeeId(rs.getInt("employee_id"));
            overtime.setOvertimeDate(rs.getDate("overtime_date"));
            overtime.setOvertimeHours(rs.getFloat("overtime_hours"));
            overtimes.add(overtime);
        }
        return overtimes;
    }
    public Overtime_Summary searchOvertimeSummary(int employeeId) throws SQLException {
        String sql = "SELECT SUM(overtime_hours) AS totalOvertime, AVG(overtime_hours) AS meanOvertime FROM overtime WHERE employee_id = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, employeeId);
        ResultSet resultSet = statement.executeQuery();
        // 处理查询结果
        Overtime_Summary overtime_summary = new Overtime_Summary();
        overtime_summary.setEmployeeId(employeeId);
        if (resultSet.next()) {
            Float totalOvertime = resultSet.getFloat("totalOvertime");
            Float meanOvertime = resultSet.getFloat("meanOvertime");
            overtime_summary.setTotalOvertime(totalOvertime);
            overtime_summary.setMeanOvertime(meanOvertime);
        }else {
            overtime_summary.setTotalOvertime((float) 0);
            overtime_summary.setMeanOvertime((float) 0);
        }
        return overtime_summary;
    }

    public int getEmployeeIdById(int id) throws SQLException {
        String sql = "SELECT employee_id from overtime WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        if(rs.next()){
            return rs.getInt("employee_id");
        }
        return 0;
    }
}
