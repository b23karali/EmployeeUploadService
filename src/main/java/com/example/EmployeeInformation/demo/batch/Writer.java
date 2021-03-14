package com.example.EmployeeInformation.demo.batch;

import com.example.EmployeeInformation.demo.data.entity.Employee;
import com.example.EmployeeInformation.demo.data.repository.EmployeeRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Writer implements ItemWriter<Employee> {

    private EmployeeRepository employeeRepository;

    @Autowired
    public Writer (EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void write(List<? extends Employee> employees) throws Exception{
        System.out.println("Data Saved for Employees: " + employees);
        employeeRepository.saveAll(employees);
    }
}
