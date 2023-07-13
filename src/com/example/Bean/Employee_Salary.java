package com.example.Bean;

/**
 * @author Cyan
 * @ date 2023/6/23
 */
public class Employee_Salary {
    private int employeeId;
    private String employeeName;
    private Float totalSalary;
    private Float meanSalary;

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public Float getMeanSalary() {
        return meanSalary;
    }

    public void setMeanSalary(Float meanSalary) {
        this.meanSalary = meanSalary;
    }

    public Float getTotalSalary() {
        return totalSalary;
    }

    public void setTotalSalary(Float totalSalary) {
        this.totalSalary = totalSalary;
    }
}
