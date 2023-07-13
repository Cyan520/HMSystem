package com.example.Dao;

import com.example.Bean.Report;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Cyan
 * @ date 2023/6/14
 */
public class ReportDAO {
    private Connection conn;

    public ReportDAO(Connection conn) {
        this.conn = conn;
    }

    public void addReport(Report report) throws SQLException {
        String sql = "INSERT INTO report (report_name, report_data) VALUES (?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, report.getReportName());
        stmt.setString(2, report.getReportData());
        stmt.executeUpdate();
    }

    public void deleteReport(int id) throws SQLException {
        String sql = "DELETE FROM report WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }

    public Report getReportById(int id) throws SQLException {
        String sql = "SELECT * FROM report WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            Report report = new Report();
            report.setId(rs.getInt("id"));
            report.setReportName(rs.getString("report_name"));
            report.setReportData(rs.getString("report_data"));
            return report;
        } else {
            return null;
        }
    }

    public List<Report> getAllReports() throws SQLException {
        String sql = "SELECT * FROM report";
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        List<Report> reports = new ArrayList<>();
        while (rs.next()) {
            Report report = new Report();
            report.setId(rs.getInt("id"));
            report.setReportName(rs.getString("report_name"));
            report.setReportData(rs.getString("report_data"));
            reports.add(report);
        }
        return reports;
    }
}

