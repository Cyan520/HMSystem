package com.example.Dao;

import com.example.Bean.EventLog;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Cyan
 * @ date 2023/6/14
 */
public class EventLogDAO {
    private Connection conn;

    public EventLogDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "root", "root");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void addEventLog(EventLog eventLog) throws SQLException {
        String sql = "INSERT INTO event_log (username, event_time, event_name, event_detail) VALUES (?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, eventLog.getUsername());
        stmt.setTimestamp(2, new Timestamp(eventLog.getEventTime().getTime()));
        stmt.setString(3, eventLog.getEventName());
        stmt.setString(4, eventLog.getEventDetail());
        stmt.executeUpdate();
    }

    public void deleteEventLog(int id) throws SQLException {
        String sql = "DELETE FROM event_log WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }

    public EventLog getEventLogById(int id) throws SQLException {
        String sql = "SELECT * FROM event_log WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            EventLog eventLog = new EventLog();
            eventLog.setId(rs.getInt("id"));
            eventLog.setUsername(rs.getString("username"));
            eventLog.setEventTime(rs.getTimestamp("event_time"));
            eventLog.setEventName(rs.getString("event_name"));
            eventLog.setEventDetail(rs.getString("event_detail"));
            return eventLog;
        } else {
            return null;
        }
    }

    public List<EventLog> getAllEventLogs() throws SQLException {
        String sql = "SELECT * FROM event_log";
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        List<EventLog> eventLogs = new ArrayList<>();
        while (rs.next()) {
            EventLog eventLog = new EventLog();
            eventLog.setId(rs.getInt("id"));
            eventLog.setUsername(rs.getString("username"));
            eventLog.setEventTime(rs.getTimestamp("event_time"));
            eventLog.setEventName(rs.getString("event_name"));
            eventLog.setEventDetail(rs.getString("event_detail"));
            eventLogs.add(eventLog);
        }
        return eventLogs;
    }

    public List<EventLog> searchEventLogs(String username, Date startTime, Date endTime, String eventName) throws SQLException {
//        System.out.println(username);
//        System.out.println(startTime);
//        System.out.println(eventName);
//        System.out.println(eventName);
        String sql = "SELECT * FROM event_log WHERE 1=1";
        List<Object> params = new ArrayList<>();

        if (username != null && !username.isEmpty()) {
            sql += " AND username LIKE ?";
            params.add("%" + username + "%");
        }

        if (startTime != null) {
            java.sql.Date startDate = new java.sql.Date(startTime.getTime());
            sql += " AND event_time >= ?";
            params.add(startDate);
        }

        if (endTime != null) {
            java.sql.Date endDate = new java.sql.Date(endTime.getTime());
            sql += " AND event_time <= ?";
            params.add(endDate);
        }

        if (eventName != null && !eventName.isEmpty()) {
            sql += " AND event_name LIKE ?";
            params.add("%" + eventName + "%");
        }

        PreparedStatement stmt = conn.prepareStatement(sql);
        for (int i = 0; i < params.size(); i++) {
            stmt.setObject(i + 1, params.get(i));
        }

        ResultSet rs = stmt.executeQuery();
        List<EventLog> eventLogs = new ArrayList<>();
        while (rs.next()) {
            EventLog eventLog = new EventLog();
            eventLog.setId(rs.getInt("id"));
            eventLog.setUsername(rs.getString("username"));
            eventLog.setEventTime(rs.getTimestamp("event_time"));
            eventLog.setEventName(rs.getString("event_name"));
            eventLog.setEventDetail(rs.getString("event_detail"));
            eventLogs.add(eventLog);
        }
        return eventLogs;
    }


}
