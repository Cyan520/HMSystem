package com.example.Servlet;

import com.example.Bean.Employee;
import com.example.Bean.Employee_Salary;
import com.example.Bean.EventLog;
import com.example.Bean.Salary;
import com.example.Dao.EmployeeDAO;
import com.example.Dao.EventLogDAO;
import com.example.Dao.SalaryDAO;

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
@WebServlet("/salary")
public class SalaryServlet extends HttpServlet {
    private SalaryDAO salaryDAO;
    private EmployeeDAO employeeDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "root", "root");
            salaryDAO = new SalaryDAO(conn);
            employeeDAO = new EmployeeDAO();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Salary> salaries = salaryDAO.getAllSalaries();
            for(Salary salary:salaries){
                salary.setEmployeeName(employeeDAO.getEmployeeNameById(salary.getEmployeeId()));
            }
            request.setAttribute("salaries", salaries);
            request.getRequestDispatcher("/salary.jsp").forward(request, response);
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
            Date salaryDate = new Date();
            float salaryAmount = Float.parseFloat(request.getParameter("salary_amount"));
            Salary salary = new Salary();
            salary.setEmployeeId(employeeId);
            salary.setSalaryDate(salaryDate);
            salary.setSalaryAmount(salaryAmount);
            try {
                salaryDAO.addSalary(salary);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (action.equals("delete")) {
            int id = Integer.parseInt(request.getParameter("id"));
            try {
                EventLog eventLog = new EventLog();
                String username = (String)request.getSession().getAttribute("username");
                int employee_id = salaryDAO.getEmployeeIdById(id);
                String name = employeeDAO.getEmployeeNameById(employee_id);
                eventLog.setUsername(username);
                eventLog.setEventTime(new java.util.Date());
                eventLog.setEventName("删除");
                eventLog.setEventDetail("删除员工" + name + "的发薪信息");
                try {
                    EventLogDAO eventLogDAO = new EventLogDAO();
                    eventLogDAO.addEventLog(eventLog);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                salaryDAO.deleteSalary(id);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else if (action.equals("search")) {
            String employeeName = request.getParameter("employee_name");
            int employeeId = employeeDAO.getEmployeeIdByName(employeeName);
            Employee_Salary employeeSalary;
            try {
                employeeSalary = salaryDAO.searchSalary(employeeId);
                employeeSalary.setEmployeeName(employeeName);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            List<Employee_Salary> employeeSalaries = new ArrayList<>();
            employeeSalaries.add(employeeSalary);
            request.setAttribute("employeeSalaries", employeeSalaries);
            request.getRequestDispatcher("/search_salary.jsp").forward(request, response);
            response.sendRedirect("search_salary.jsp");
        }




        response.sendRedirect(request.getContextPath() + "/salary");
    }
}
