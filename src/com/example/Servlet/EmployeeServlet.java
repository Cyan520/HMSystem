package com.example.Servlet;

import com.example.Bean.Employee;
import com.example.Bean.EventLog;
import com.example.Dao.EmployeeDAO;
import com.example.Dao.EventLogDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * @author Cyan
 * @ date 2023/6/14
 */
@WebServlet("/employee")
public class EmployeeServlet extends HttpServlet {
    private EmployeeDAO employeeDAO;

    public void init() {
        employeeDAO = new EmployeeDAO();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }
        switch (action) {
            case "list":
                listEmployees(request, response);
                break;
            case "showAddForm":
                showAddForm(request, response);
                break;
            case "add":
                addEmployee(request, response);
                break;
            case "showUpdateForm":
                showUpdateForm(request, response);
                break;
            case "update":
                updateEmployee(request, response);
                break;
            case "delete":
                deleteEmployee(request, response);
                break;
            default:
                listEmployees(request, response);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private void listEmployees(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServletException, IOException {
        List<Employee> employees = employeeDAO.getAllEmployees();
        request.setAttribute("employees", employees);
        RequestDispatcher dispatcher = request.getRequestDispatcher("list-employees.jsp");
        dispatcher.forward(request, response);
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("add-employee.jsp");
        dispatcher.forward(request, response);
    }

    private void addEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String gender = request.getParameter("gender");
        int age = Integer.parseInt(request.getParameter("age"));
        String position = request.getParameter("position");
        double salary = Double.parseDouble(request.getParameter("salary"));
        int departmentid = Integer.parseInt(request.getParameter("departmentid"));
        Employee employee = new Employee();
        employee.setName(name);
        employee.setGender(gender);
        employee.setAge(age);
        employee.setPosition(position);
        employee.setSalary(salary);
        employee.setDepartmentId(departmentid);
        employeeDAO.addEmployee(employee);

        EventLog eventLog = new EventLog();
        String username = (String)request.getSession().getAttribute("username");
        eventLog.setUsername(username);
        eventLog.setEventTime(new Date());
        eventLog.setEventName("添加");
        eventLog.setEventDetail("添加员工:" + name);
        try {
            EventLogDAO eventLogDAO = new EventLogDAO();
            eventLogDAO.addEventLog(eventLog);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect("employee?action=list");
    }

    private void showUpdateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Employee employee = employeeDAO.getEmployeeById(id);
        request.setAttribute("employee", employee);
        RequestDispatcher dispatcher = request.getRequestDispatcher("update-employee.jsp");
        dispatcher.forward(request, response);
    }

    private void updateEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String gender = request.getParameter("gender");
        int age = Integer.parseInt(request.getParameter("age"));
        String position = request.getParameter("position");
        double salary = Double.parseDouble(request.getParameter("salary"));
        int departmentid = Integer.parseInt(request.getParameter("departmentid"));
        Employee employee = new Employee();
        employee.setId(id);
        employee.setName(name);
        employee.setGender(gender);
        employee.setAge(age);
        employee.setPosition(position);
        employee.setSalary(salary);
        employee.setDepartmentId(departmentid);
        employeeDAO.updateEmployee(employee);

        EventLog eventLog = new EventLog();
        String username = (String)request.getSession().getAttribute("username");
        eventLog.setUsername(username);
        eventLog.setEventTime(new Date());
        eventLog.setEventName("更新");
        eventLog.setEventDetail("更新员工:" + name);
        try {
            EventLogDAO eventLogDAO = new EventLogDAO();
            eventLogDAO.addEventLog(eventLog);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect("employee?action=list");
    }

    private void deleteEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = employeeDAO.getEmployeeNameById(id);
        EventLog eventLog = new EventLog();
        String username = (String)request.getSession().getAttribute("username");
        eventLog.setUsername(username);
        eventLog.setEventTime(new Date());
        eventLog.setEventName("删除");
        eventLog.setEventDetail("删除员工:" + name);
        try {
            EventLogDAO eventLogDAO = new EventLogDAO();
            eventLogDAO.addEventLog(eventLog);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        employeeDAO.deleteEmployee(id);
        response.sendRedirect("employee?action=list");
    }
}

