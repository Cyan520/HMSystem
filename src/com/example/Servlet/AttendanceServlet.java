package com.example.Servlet;

import com.example.Bean.Attendance;
import com.example.Bean.Employee;
import com.example.Bean.EventLog;
import com.example.Bean.TodayAttendance;
import com.example.Dao.AttendanceDAO;
import com.example.Dao.EmployeeDAO;
import com.example.Dao.EventLogDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
//import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.Date;

/**
 * @author Cyan
 * @ date 2023/6/14
 */
@WebServlet("/attendance")
public class AttendanceServlet extends HttpServlet {
    private AttendanceDAO attendanceDAO;
    private EmployeeDAO employeeDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        attendanceDAO = new AttendanceDAO();
        employeeDAO = new EmployeeDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Attendance> attendances = attendanceDAO.getAllAttendances();
            for(Attendance attendance:attendances){
                attendance.setEmployeeName(employeeDAO.getEmployeeNameById(attendance.getEmployeeId()));
            }
            request.setAttribute("attendances", attendances);
            request.getRequestDispatcher("/attendance.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action.equals("add")) {
//            int employeeId = Integer.parseInt(request.getParameter("employee_id"));
            String employeeName = request.getParameter("employee_name");
            int employeeId = employeeDAO.getEmployeeIdByName(employeeName);
            Date attendanceDate = new Date();
            Time attendanceTime = Time.valueOf(request.getParameter("attendance_time"));
            String attendanceType = request.getParameter("attendance_type");
            Attendance attendance = new Attendance();
            attendance.setEmployeeId(employeeId);
            attendance.setAttendanceDate(attendanceDate);
            attendance.setAttendanceTime(attendanceTime);
            attendance.setAttendanceType(attendanceType);
            try {
                attendanceDAO.addAttendance(attendance);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (action.equals("delete")) {
            int id = Integer.parseInt(request.getParameter("id"));
            try {
                EventLog eventLog = new EventLog();
                String username = (String)request.getSession().getAttribute("username");
                int employee_id = attendanceDAO.getEmployeeIdById(id);
                String name = employeeDAO.getEmployeeNameById(employee_id);
                eventLog.setUsername(username);
                eventLog.setEventTime(new java.util.Date());
                eventLog.setEventName("删除");
                eventLog.setEventDetail("删除员工" + name + "的考勤信息");
                try {
                    EventLogDAO eventLogDAO = new EventLogDAO();
                    eventLogDAO.addEventLog(eventLog);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                attendanceDAO.deleteAttendance(id);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (action.equals("search")) {
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

            List<Attendance> attendances;
            try {
                attendances = attendanceDAO.searchAttendances(employeeId, startTime, endTime);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            for(Attendance attendance:attendances){
                attendance.setEmployeeName(employeeDAO.getEmployeeNameById(attendance.getEmployeeId()));
            }
            // 将事件日志信息列表作为属性传递给新的页面
            request.setAttribute("attendances", attendances);

            // 转发到新的页面
            RequestDispatcher dispatcher = request.getRequestDispatcher("/search_attendance.jsp");
            dispatcher.forward(request, response);
            response.sendRedirect("search_attendance.jsp");
        }else if (action.equals("today")) {
            LocalDate currentDate = LocalDate.now();

            // 转换为java.sql.Date对象
            java.sql.Date today = java.sql.Date.valueOf(currentDate);
//            Date today = new Date();
            List<TodayAttendance> todayAttendances;
            try {
                todayAttendances = attendanceDAO.searchTodayAttendances(today);
//                System.out.println(todayAttendances.get(0));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            List<Employee> employees = employeeDAO.getAllEmployees();
            Map<Integer, Time> todayMap = new HashMap<>();
            for (TodayAttendance todayAttendance : todayAttendances) {
                todayMap.put(todayAttendance.getEmployeeId(), todayAttendance.getAttendanceTime());
            }
//            System.out.println(todayMap);
            List<TodayAttendance> requestTodayAttendances = new ArrayList<>();
            for(Employee employee:employees){
                if(todayMap.containsKey(employee.getId())){
                    TodayAttendance todayAttendance = new TodayAttendance();
                    todayAttendance.setEmployeeId(employee.getId());
                    todayAttendance.setEmployeeName(employee.getName());
                    todayAttendance.setAttendance("已打卡");
                    todayAttendance.setAttendanceTime(todayMap.get(employee.getId()));
                    requestTodayAttendances.add(todayAttendance);
                }else{
                    TodayAttendance todayAttendance = new TodayAttendance();
                    todayAttendance.setEmployeeId(employee.getId());
                    todayAttendance.setEmployeeName(employee.getName());
                    todayAttendance.setAttendance("未打卡");
                    todayAttendance.setAttendanceTime(null);
                    requestTodayAttendances.add(todayAttendance);
                }

            }
            // 将事件日志信息列表作为属性传递给新的页面
            request.setAttribute("TodayAttendances", requestTodayAttendances);
            // 转发到新的页面
            RequestDispatcher dispatcher = request.getRequestDispatcher("/today_attendance.jsp");
            dispatcher.forward(request, response);
            response.sendRedirect("today_attendance.jsp");
        }

        response.sendRedirect("attendance");
    }
}
