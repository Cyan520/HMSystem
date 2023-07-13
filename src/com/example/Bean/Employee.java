package com.example.Bean;

/**
 * @author Cyan
 * @ date 2023/6/14
 */
public class Employee {
    private int id;
    private String name;
    private String gender;
    private int age;
    private String position;
    private double salary;
    private int departmentId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
    public int getDepartmentId() {return departmentId;}
    public void setDepartmentId(int departmentId) {this.departmentId = departmentId;}
}

