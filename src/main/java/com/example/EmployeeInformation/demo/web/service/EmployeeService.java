package com.example.EmployeeInformation.demo.web.service;

import com.example.EmployeeInformation.demo.data.entity.Employee;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

public interface EmployeeService {
    public Long uploadEmployeeDataFromFile(MultipartFile file) throws IOException, JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException;

    public void saveEmployee(Employee employee);

    public boolean updateEmployee(Employee employee);

    public Optional<Employee> getEmployeeDetails(Long empId);

    public void deleteEmployeeDetails(Long empId);
}
