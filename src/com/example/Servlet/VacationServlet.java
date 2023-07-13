package com.example.Servlet;

import com.example.Bean.Employee;
import com.example.Bean.EventLog;
import com.example.Bean.Vacation;
import com.example.Dao.DepartmentDAO;
import com.example.Dao.EmployeeDAO;
import com.example.Dao.EventLogDAO;
import com.example.Dao.VacationDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
//import java.util.Date;
import java.util.List;

/**
 * @author Cyan
 * @ date 2023/6/14
 */
@WebServlet("/vacation")
public class VacationServlet extends HttpServlet {
    private VacationDAO vacationDAO;
    private  EmployeeDAO employeeDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            vacationDAO = new VacationDAO();
            employeeDAO = new EmployeeDAO();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Vacation> vacations = vacationDAO.getAllVacations();
//            List<Employee> employees = employeeDAO.getAllEmployees();
            for (Vacation vacation : vacations) {
//                EmployeeDAO employeeDAO = new EmployeeDAO();
                String name = employeeDAO.getEmployeeNameById(vacation.getEmployeeId());
                vacation.setEmployee_name(name);
            }

//            request.setAttribute("employees", employees);
            request.setAttribute("vacations", vacations);
            request.getRequestDispatcher("/vacation.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IOException {
        String action = request.getParameter("action");
        if (action.equals("add")) {
            String employeeName = request.getParameter("employee_name");
            int employeeId = employeeDAO.getEmployeeIdByName(employeeName);
            Date startDate = Date.valueOf(request.getParameter("start_date"));
            Date endDate = Date.valueOf(request.getParameter("end_date"));
            String type = request.getParameter("type");
            String reason = request.getParameter("reason");
            Vacation vacation = new Vacation();
            vacation.setEmployeeId(employeeId);
            vacation.setStartDate(startDate);
            vacation.setEndDate(endDate);
            vacation.setType(type);
            vacation.setReason(reason);
            try {
                vacationDAO.addVacation(vacation);

                String name = employeeDAO.getEmployeeNameById(employeeId);
                EventLog eventLog = new EventLog();
                String username = (String)request.getSession().getAttribute("username");
                eventLog.setUsername(username);
                eventLog.setEventTime(new java.util.Date());
                eventLog.setEventName("添加");
                eventLog.setEventDetail("添加员工" + name + "的休假信息");
                try {
                    EventLogDAO eventLogDAO = new EventLogDAO();
                    eventLogDAO.addEventLog(eventLog);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (action.equals("delete")) {
            int id = Integer.parseInt(request.getParameter("id"));
            int employee_id = 0;
            try {
                employee_id = vacationDAO.getEmployeeIdById(id);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            String name = employeeDAO.getEmployeeNameById(employee_id);

            try {
                vacationDAO.deleteVacation(id);

                EventLog eventLog = new EventLog();
                String username = (String)request.getSession().getAttribute("username");
                eventLog.setUsername(username);
                eventLog.setEventTime(new java.util.Date());
                eventLog.setEventName("删除");
                eventLog.setEventDetail("删除员工" + name + "的休假信息");
                try {
                    EventLogDAO eventLogDAO = new EventLogDAO();
                    eventLogDAO.addEventLog(eventLog);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } else if (action.equals("update")) {
            int id = Integer.parseInt(request.getParameter("id"));
            int employeeId = Integer.parseInt(request.getParameter("employee_id"));
            Date startDate = Date.valueOf(request.getParameter("start_date"));
            Date endDate = Date.valueOf(request.getParameter("end_date"));
            String type = request.getParameter("type");
            String reason = request.getParameter("reason");
            Vacation vacation = new Vacation();
            vacation.setId(id);
            vacation.setEmployeeId(employeeId);
            vacation.setStartDate(startDate);
            vacation.setEndDate(endDate);
            vacation.setType(type);
            vacation.setReason(reason);
            try {
                vacationDAO.updateVacation(vacation);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (action.equals("search")) {
            String name = request.getParameter("employee_name");
//            System.out.println(name);
            int id = employeeDAO.getEmployeeIdByName(name);
            if(id != 0){
//                System.out.println(id);
                List<Double> vacation_info;
                try {
                    vacation_info = vacationDAO.getSumAndAvgDays(id);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                double totalDays = vacation_info.get(0);
                double averageDays = vacation_info.get(1);
//                System.out.println(totalDays);
//                System.out.println(averageDays);
                request.setAttribute("employeeName", name);
                request.setAttribute("totalDays", totalDays);
                request.setAttribute("averageDays", averageDays);
                request.getRequestDispatcher("/vacation_query.jsp").forward(request, response);
                response.sendRedirect("vacation_query.jsp");
            }else{
                request.setAttribute("errorMessage", "此员工不存在!");
                doGet(request, response);
            }
        }
        response.sendRedirect("vacation");
    }
}

