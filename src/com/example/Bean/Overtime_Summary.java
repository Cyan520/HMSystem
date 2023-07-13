package com.example.Bean;

/**
 * @author Cyan
 * @ date 2023/6/22
 */
public class Overtime_Summary {
    private int employeeId;
    private String employeeName;
    private Float totalOvertime;
    private Float meanOvertime;

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

    public Float getMeanOvertime() {
        return meanOvertime;
    }

    public void setMeanOvertime(Float meanOvertime) {
        this.meanOvertime = meanOvertime;
    }

    public void setTotalOvertime(Float totalOvertime) {
        this.totalOvertime = totalOvertime;
    }

    public Float getTotalOvertime() {
        return totalOvertime;
    }
}
