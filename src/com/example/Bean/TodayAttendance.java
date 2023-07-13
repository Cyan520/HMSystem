package com.example.Bean;

import java.sql.Time;
import java.util.Date;

/**
 * @author Cyan
 * @ date 2023/6/22
 */
public class TodayAttendance {
    private int employeeId;
    private String employeeName;
    private String attendance;
    private Time attendanceTime;

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

    public void setAttendance(String attendance) {
        this.attendance = attendance;
    }

    public String getAttendance() {
        return attendance;
    }

    public void setAttendanceTime(Time attendanceTime) {
        this.attendanceTime = attendanceTime;
    }

    public Time getAttendanceTime() {
        return attendanceTime;
    }
}
