package com.example.EmployeeInformation.demo.data.entity;

import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;

@Entity
@Table(name="Employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="EMP_ID")
    private long employeeId;

    @Column(name="EMPLOYEE_NAME")
    private String employeeName;

    @Column(name="AGE")
    private int age;

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", employeeName='" + employeeName + '\'' +
                ", age=" + age +
                '}';
    }
}
