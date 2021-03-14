package com.example.EmployeeInformation.demo.data.repository;

import com.example.EmployeeInformation.demo.data.entity.Employee;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long> {

}
