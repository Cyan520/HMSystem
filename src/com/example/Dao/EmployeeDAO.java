package com.example.Dao;

import com.example.Bean.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Cyan
 * @ date 2023/6/14
 */
public class EmployeeDAO {
    private Connection conn;

    public EmployeeDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "root", "root");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void addEmployee(Employee employee) {
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO employee (name, gender, age, position, salary, department_id) VALUES (?, ?, ?, ?, ?,?)");
            ps.setString(1, employee.getName());
            ps.setString(2, employee.getGender());
            ps.setInt(3, employee.getAge());
            ps.setString(4, employee.getPosition());
            ps.setDouble(5, employee.getSalary());
            ps.setInt(6, employee.getDepartmentId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteEmployee(int id) {
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM employee WHERE id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateEmployee(Employee employee) {
        try {
            PreparedStatement ps = conn.prepareStatement("UPDATE employee SET name = ?, gender = ?, age = ?, position = ?, salary = ?,departmentid = ? WHERE id = ?");
            ps.setString(1, employee.getName());
            ps.setString(2, employee.getGender());
            ps.setInt(3, employee.getAge());
            ps.setString(4, employee.getPosition());
            ps.setDouble(5, employee.getSalary());
            ps.setInt(6, employee.getId());
            ps.setInt(7, employee.getDepartmentId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Employee getEmployeeById(int id) {
        Employee employee = null;
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM employee WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                employee = new Employee();
                employee.setId(rs.getInt("id"));
                employee.setName(rs.getString("name"));
                employee.setGender(rs.getString("gender"));
                employee.setAge(rs.getInt("age"));
                employee.setPosition(rs.getString("position"));
                employee.setSalary(rs.getDouble("salary"));
                employee.setDepartmentId(rs.getInt("department_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM employee");
            while (rs.next()) {
                Employee employee = new Employee();
                employee.setId(rs.getInt("id"));
                employee.setName(rs.getString("name"));
                employee.setGender(rs.getString("gender"));
                employee.setAge(rs.getInt("age"));
                employee.setPosition(rs.getString("position"));
                employee.setSalary(rs.getDouble("salary"));
                employee.setDepartmentId(rs.getInt("department_id"));
                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }
    public int getEmployeeIdByName(String name) {
//        int id = 0;
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT id FROM employee WHERE name = ?");
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("getEmployeeIdByName不为空");
                return rs.getInt("id");
//                return id;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public String getEmployeeNameById(int id) {
//        int id = 0;
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT name FROM employee WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
//                System.out.println("getEmployeeIdByName不为空");
                return rs.getString("name");
//                return id;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

