package com.example.Servlet;

import com.example.Bean.EventLog;
import com.example.Dao.EventLogDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.Date;
//import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Cyan
 * @ date 2023/6/14
 */
@WebServlet("/event_log")
public class EventLogServlet extends HttpServlet {
    private EventLogDAO eventLogDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        eventLogDAO = new EventLogDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<EventLog> eventLogs = eventLogDAO.getAllEventLogs();
            request.setAttribute("eventLogs", eventLogs);
            request.getRequestDispatcher("/event_log.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IOException {
        String action = request.getParameter("action");
        if (action.equals("add")) {
            String username = request.getParameter("username");
            Date eventTime = new Date();
            String eventName = request.getParameter("event_name");
            String eventDetail = request.getParameter("event_detail");
            EventLog eventLog = new EventLog();
            eventLog.setUsername(username);
            eventLog.setEventTime(eventTime);
            eventLog.setEventName(eventName);
            eventLog.setEventDetail(eventDetail);
            try {
                eventLogDAO.addEventLog(eventLog);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (action.equals("delete")) {
            int id = Integer.parseInt(request.getParameter("id"));
            try {
                eventLogDAO.deleteEventLog(id);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (action.equals("search")) {
            // 获取查询参数
            String username = request.getParameter("username");
            String startTimeParam = request.getParameter("start_time");
            java.sql.Date startTime = null;
            if (startTimeParam != null && !startTimeParam.trim().isEmpty()) {
                startTime = java.sql.Date.valueOf(startTimeParam);
            }

            String endTimeParam = request.getParameter("end_time");
            java.sql.Date endTime = null;
            if (endTimeParam != null && !endTimeParam.trim().isEmpty()) {
                endTime = java.sql.Date.valueOf(endTimeParam);
            }
            String eventName = request.getParameter("event_name");

            List<EventLog> eventLogs;
            try {
                eventLogs = eventLogDAO.searchEventLogs(username, startTime, endTime, eventName);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            // 将事件日志信息列表作为属性传递给新的页面
            request.setAttribute("eventLogs", eventLogs);

            // 转发到新的页面
            RequestDispatcher dispatcher = request.getRequestDispatcher("search_log.jsp");
            dispatcher.forward(request, response);


        }
        response.sendRedirect(request.getContextPath() + "/event_log");
    }
}
