package com.example.Dao;

import com.example.Bean.Department;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Cyan
 * @ date 2023/6/14
 */
public class DepartmentDAO {
    private Connection conn;
    public DepartmentDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "root", "root");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

//    public EmployeeDAO employeeDAO;

    public void addDepartment(Department department) throws SQLException {
        String sql = "INSERT INTO department (name, manger_id, parent_id) VALUES (?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, department.getName());
        stmt.setInt(2, department.getMangerId());
        stmt.setInt(3, department.getParentId());
        stmt.executeUpdate();
    }

    public void deleteDepartment(int id) throws SQLException {
        String sql = "DELETE FROM department WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }

    public void updateDepartment(Department department) throws SQLException {
        String sql = "UPDATE department SET name = ?, manger_id = ?, parent_id = ? WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, department.getName());
        stmt.setInt(2, department.getMangerId());
        stmt.setInt(3, department.getParentId());
        stmt.setInt(4, department.getId());
        stmt.executeUpdate();
    }

    public Department getDepartmentById(int id) throws SQLException {
        String sql = "SELECT * FROM department WHERE id = ?";
        EmployeeDAO employeeDAO = new EmployeeDAO();
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            Department department = new Department();
            department.setId(rs.getInt("id"));
            department.setName(rs.getString("name"));
            department.setMangerId(rs.getInt("manger_id"));
            department.setManger(employeeDAO.getEmployeeNameById(rs.getInt("manger_id")));
            department.setParentId(rs.getInt("parent_id"));
            return department;
        } else {
            return null;
        }
    }

    public List<Department> getAllDepartments() throws SQLException {
        String sql = "SELECT * FROM department";
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        List<Department> departments = new ArrayList<>();
        while (rs.next()) {
            Department department = new Department();
            department.setId(rs.getInt("id"));
            department.setName(rs.getString("name"));
            department.setMangerId(rs.getInt("manger_id"));
            department.setParentId(rs.getInt("parent_id"));
            departments.add(department);
        }
        return departments;
    }
    public String getDepartmentNameById(int id) {
//        int id = 0;
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT name FROM department WHERE id = ?");
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

