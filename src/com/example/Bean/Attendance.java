package com.example.Bean;

import java.sql.Time;
import java.util.Date;


/**
 * @author Cyan
 * @ date 2023/6/14
 */
public class Attendance {
    private int id;
    private int employeeId;
    private String employeeName;
    private Date attendanceDate;
    private Time attendanceTime;
    private String attendanceType;

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
    public void setEmployeeName(String employeeName){
        this.employeeName = employeeName;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public Date getAttendanceDate() {
        return attendanceDate;
    }

    public void setAttendanceDate(Date attendanceDate) {
        this.attendanceDate = attendanceDate;
    }

    public Time getAttendanceTime() {
        return attendanceTime;
    }

    public void setAttendanceTime(Time attendanceTime) {
        this.attendanceTime = attendanceTime;
    }

    public String getAttendanceType() {
        return attendanceType;
    }

    public void setAttendanceType(String attendanceType) {
        this.attendanceType = attendanceType;
    }
}

