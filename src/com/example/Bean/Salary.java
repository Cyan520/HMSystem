package com.example.Bean;

import java.util.Date;

/**
 * @author Cyan
 * @ date 2023/6/14
 */
public class Salary {
    private int id;
    private int employeeId;
    private String employeeName;
    private Date salaryDate;
    private float salaryAmount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
    public String getEmployeeName() {
        return employeeName;
    }

    public Date getSalaryDate() {
        return salaryDate;
    }

    public void setSalaryDate(Date salaryDate) {
        this.salaryDate = salaryDate;
    }

    public float getSalaryAmount() {
        return salaryAmount;
    }

    public void setSalaryAmount(float salaryAmount) {
        this.salaryAmount = salaryAmount;
    }
}
