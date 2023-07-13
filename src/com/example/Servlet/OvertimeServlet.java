package com.example.Servlet;


import com.example.Bean.*;
import com.example.Dao.EmployeeDAO;
import com.example.Dao.EventLogDAO;
import com.example.Dao.OvertimeDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * @author Cyan
 * @ date 2023/6/14
 */
@WebServlet("/overtime")
public class OvertimeServlet extends HttpServlet {
    private OvertimeDAO overtimeDAO;
    private EmployeeDAO employeeDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "root", "root");
            overtimeDAO = new OvertimeDAO(conn);
            employeeDAO = new EmployeeDAO();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Overtime> overtimes = overtimeDAO.getAllOvertimes();
            for(Overtime overtime:overtimes){
                overtime.setEmployeeName(employeeDAO.getEmployeeNameById(overtime.getEmployeeId()));
            }
            request.setAttribute("overtimes", overtimes);
            request.getRequestDispatcher("/overtime.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action.equals("add")) {
            String employeeName = request.getParameter("employee_name");
            int employeeId = employeeDAO.getEmployeeIdByName(employeeName);
            Date overtimeDate = new Date();
            float overtimeHours = Float.parseFloat(request.getParameter("overtime_hours"));
            Overtime overtime = new Overtime();
            overtime.setEmployeeId(employeeId);
            overtime.setOvertimeDate(overtimeDate);
            overtime.setOvertimeHours(overtimeHours);
            try {
                overtimeDAO.addOvertime(overtime);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (action.equals("delete")) {
            int id = Integer.parseInt(request.getParameter("id"));
            try {
                EventLog eventLog = new EventLog();
                String username = (String)request.getSession().getAttribute("username");
                int employee_id = overtimeDAO.getEmployeeIdById(id);
                String name = employeeDAO.getEmployeeNameById(employee_id);
                eventLog.setUsername(username);
                eventLog.setEventTime(new java.util.Date());
                eventLog.setEventName("删除");
                eventLog.setEventDetail("删除员工" + name + "的加班信息");
                try {
                    EventLogDAO eventLogDAO = new EventLogDAO();
                    eventLogDAO.addEventLog(eventLog);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }


                overtimeDAO.deleteOvertime(id);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else if (action.equals("search")) {
            // 获取查询参数
            String employeeName = request.getParameter("employee_name");
            int employeeId = employeeDAO.getEmployeeIdByName(employeeName);
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

            List<Overtime> overtimes;
            try {
                overtimes = overtimeDAO.searchOvertimes(employeeId, startTime, endTime);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            for(Overtime overtime:overtimes){
                overtime.setEmployeeName(employeeDAO.getEmployeeNameById(overtime.getEmployeeId()));
            }
            // 将事件日志信息列表作为属性传递给新的页面
            request.setAttribute("overtimes", overtimes);

            // 转发到新的页面
            RequestDispatcher dispatcher = request.getRequestDispatcher("/search_overtime.jsp");
            dispatcher.forward(request, response);
            response.sendRedirect("search_overtime.jsp");
        } else if (action.equals("summary")) {
            List<Overtime_Summary> overtimeSummaries = new ArrayList<>();
            List<Employee> employees = employeeDAO.getAllEmployees();
            for(Employee employee:employees){
                try {
                    Overtime_Summary overtime_summary = overtimeDAO.searchOvertimeSummary(employee.getId());
                    overtime_summary.setEmployeeName(employeeDAO.getEmployeeNameById(employee.getId()));
                    overtimeSummaries.add(overtime_summary);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            request.setAttribute("overtimeSummarys", overtimeSummaries);
            request.getRequestDispatcher("/overtime_summary.jsp").forward(request, response);
            response.sendRedirect("overtime_summary.jsp");
        }

        response.sendRedirect(request.getContextPath() + "/overtime");
    }
}

