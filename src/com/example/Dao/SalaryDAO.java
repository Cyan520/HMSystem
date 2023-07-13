package com.example.Dao;


import com.example.Bean.Employee_Salary;
import com.example.Bean.Salary;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * @author Cyan
 * @ date 2023/6/14
 */
public class SalaryDAO {
    private Connection conn;

    public SalaryDAO(Connection conn) {
        this.conn = conn;
    }

    public void addSalary(Salary salary) throws SQLException {
        String sql = "INSERT INTO salary (employee_id, salary_date, salary_amount) VALUES (?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, salary.getEmployeeId());
        stmt.setDate(2, new java.sql.Date(salary.getSalaryDate().getTime()));
        stmt.setFloat(3, salary.getSalaryAmount());
        stmt.executeUpdate();
    }

    public void deleteSalary(int id) throws SQLException {
        String sql = "DELETE FROM salary WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }

    public Salary getSalaryById(int id) throws SQLException {
        String sql = "SELECT * FROM salary WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            Salary salary = new Salary();
            salary.setId(rs.getInt("id"));
            salary.setEmployeeId(rs.getInt("employee_id"));
            salary.setSalaryDate(rs.getDate("salary_date"));
            salary.setSalaryAmount(rs.getFloat("salary_amount"));
            return salary;
        } else {
            return null;
        }
    }

    public List<Salary> getAllSalaries() throws SQLException {
        String sql = "SELECT * FROM salary";
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        List<Salary> salaries = new ArrayList<>();
        while (rs.next()) {
            Salary salary = new Salary();
            salary.setId(rs.getInt("id"));
            salary.setEmployeeId(rs.getInt("employee_id"));
            salary.setSalaryDate(rs.getDate("salary_date"));
            salary.setSalaryAmount(rs.getFloat("salary_amount"));
            salaries.add(salary);
        }
        return salaries;
    }

    public Employee_Salary searchSalary(int employeeId) throws SQLException {
        String sql = "SELECT SUM(salary_amount) AS totalSalary, AVG(salary_amount) AS meanSalary FROM salary WHERE employee_id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, employeeId);
        ResultSet rs = stmt.executeQuery();
        Employee_Salary salary = new Employee_Salary();
        salary.setEmployeeId(employeeId);
        if(rs.next()) {
            salary.setTotalSalary(rs.getFloat("totalSalary"));
            salary.setMeanSalary(rs.getFloat("meanSalary"));
        }
        return salary;
    }

    public int getEmployeeIdById(int id) throws SQLException {
        String sql = "SELECT employee_id from salary WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        if(rs.next()){
            return rs.getInt("employee_id");
        }
        return 0;
    }
}

