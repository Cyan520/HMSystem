package com.example.Bean;

import java.sql.Date;

/**
 * @author Cyan
 * @ date 2023/6/14
 */
public class Vacation {
    private int id;
    private int employeeId;
    private String employee_name;
    private Date startDate;
    private Date endDate;
    private String type;
    private String reason;


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

    public void setEmployee_name(String name){
        this.employee_name = name;
    }
    public String getEmployee_name(){
        return employee_name;
    }
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}

