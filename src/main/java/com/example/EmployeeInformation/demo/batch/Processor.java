package com.example.EmployeeInformation.demo.batch;

import com.example.EmployeeInformation.demo.data.entity.Employee;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class Processor implements ItemProcessor<Employee, Employee> {

    public Processor() {

    }

    @Override
    public Employee process(Employee employee) throws Exception {

        return employee;
    }
}

