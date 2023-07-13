package com.example.Servlet;

import com.example.Bean.Department;
import com.example.Bean.Employee;
import com.example.Bean.EventLog;
import com.example.Dao.DepartmentDAO;
import com.example.Dao.EmployeeDAO;
import com.example.Dao.EventLogDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.tree.TreeNode;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;


@WebServlet("/department")
public class DepartmentServlet extends HttpServlet {
    private DepartmentDAO departmentDAO;
    private EmployeeDAO employeeDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        departmentDAO = new DepartmentDAO();
        employeeDAO = new EmployeeDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Department> departments = departmentDAO.getAllDepartments();
            for (Department department : departments) {
                department.setManger(employeeDAO.getEmployeeNameById(department.getMangerId()));
            }

            request.setAttribute("departments", departments);
            request.getRequestDispatcher("/department.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action.equals("add")) {
            String name = request.getParameter("name");
            String manger = request.getParameter("manger");
            int mangerId = employeeDAO.getEmployeeIdByName(manger);
            int parentId = Integer.parseInt(request.getParameter("parent_id"));
            Department department = new Department();
            department.setName(name);
            department.setMangerId(mangerId);
            department.setParentId(parentId);
            try {
                departmentDAO.addDepartment(department);
                EventLog eventLog = new EventLog();
                String username = (String)request.getSession().getAttribute("username");
                eventLog.setUsername(username);
                eventLog.setEventTime(new Date());
                eventLog.setEventName("添加");
                eventLog.setEventDetail("添加部门:" + name);
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
            try {
                String name = departmentDAO.getDepartmentNameById(id);
                EventLog eventLog = new EventLog();
                String username = (String)request.getSession().getAttribute("username");
                eventLog.setUsername(username);
                eventLog.setEventTime(new Date());
                eventLog.setEventName("删除");
                eventLog.setEventDetail("删除部门:" + name);
                try {
                    EventLogDAO eventLogDAO = new EventLogDAO();
                    eventLogDAO.addEventLog(eventLog);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                departmentDAO.deleteDepartment(id);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (action.equals("update")) {
            int id = Integer.parseInt(request.getParameter("id"));
            Department department = null;
            try {
                department = departmentDAO.getDepartmentById(id);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            request.setAttribute("department", department);
            RequestDispatcher dispatcher = request.getRequestDispatcher("update_department.jsp");
            dispatcher.forward(request, response);

        }else if (action.equals("update_dep")) {
            int id = Integer.parseInt(request.getParameter("id"));
            String manger = request.getParameter("manger");
            int mangerId = employeeDAO.getEmployeeIdByName(manger);
            String name = request.getParameter("name");
            int parentId = Integer.parseInt(request.getParameter("parentId"));
            Department department = new Department();
            department.setId(id);
            department.setName(name);
            department.setMangerId(mangerId);
            department.setParentId(parentId);
            try {
                departmentDAO.updateDepartment(department);
//                String name = departmentDAO.getDepartmentNameById(id);
                EventLog eventLog = new EventLog();
                String username = (String)request.getSession().getAttribute("username");
                eventLog.setUsername(username);
                eventLog.setEventTime(new Date());
                eventLog.setEventName("修改");
                eventLog.setEventDetail("修改部门:" + name);
                try {
                    EventLogDAO eventLogDAO = new EventLogDAO();
                    eventLogDAO.addEventLog(eventLog);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (action.equals("tree")) {
            List<Department> departments = null;
            try {
                departments = departmentDAO.getAllDepartments();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            request.setAttribute("departments", departments);
            request.getRequestDispatcher("/departmentTree.jsp").forward(request, response);
            response.sendRedirect("departmentTree.jsp");
        }

        response.sendRedirect(request.getContextPath() + "/department");
    }




}


