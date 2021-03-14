package com.example.EmployeeInformation.demo.web.controller;

import com.example.EmployeeInformation.demo.data.entity.Employee;
import com.example.EmployeeInformation.demo.data.repository.EmployeeRepository;
import com.example.EmployeeInformation.demo.web.service.EmployeeService;
import com.example.EmployeeInformation.demo.web.service.EmployeeServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping(value = "/api")
public class EmployeeController {

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    JobExplorer jobExplorer;

    @Autowired
    Job job;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    EmployeeService employeeService;

    Logger logger = LoggerFactory.getLogger(EmployeeController.class);


    @PostMapping(value = "/employee", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity loadEmployeeData(@RequestParam("action") String[] filters,
                               @RequestParam("file") MultipartFile file) throws Exception {

        if(filters.length > 0 && filters[0].equalsIgnoreCase("upload")){
            Long jobExectionId = employeeService.uploadEmployeeDataFromFile(file);
            return new ResponseEntity(jobExectionId, HttpStatus.ACCEPTED);
        }else
            throw new Exception("Action not identified");
    }

    @GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Employee> getAllEmployee(){
        Iterable<Employee> iterable = employeeRepository.findAll();
        return StreamSupport.stream(iterable.spliterator(), false)
                .collect(Collectors.toList());

    }

    @GetMapping(value = "/getJobStatus", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getJobStatus(@RequestParam("jobId") String jobId){
        JobExecution jobExecution = jobExplorer.getJobExecution(Long.parseLong(jobId));
        return new ResponseEntity(jobExecution.getStatus(), HttpStatus.OK);
    }

    @PostMapping(value = "/employees", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addEmployee(@RequestBody Employee employee) throws Exception {
        employeeService.saveEmployee(employee);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping(value = "/employees", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateEmployee(@RequestBody Employee employee) throws Exception {
        if (employeeService.updateEmployee(employee))
            return new ResponseEntity(employee, HttpStatus.OK);
        else
            return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/employees/{employeeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getEmployee(@PathVariable(value = "employeeId") String employeeId) throws Exception {
        Optional<Employee> employee = employeeService.getEmployeeDetails(Long.parseLong(employeeId));
        if(employee.isPresent())
            return new ResponseEntity(employee.get(), HttpStatus.OK);
        else
            return new ResponseEntity(null, HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/employees/{employeeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteEmployee(@PathVariable(value = "employeeId") String employeeId) throws Exception {
        try{
            employeeService.deleteEmployeeDetails(Long.parseLong(employeeId));
            return new ResponseEntity(null, HttpStatus.OK);
        } catch (EmptyResultDataAccessException ex){
            return new ResponseEntity(null, HttpStatus.NO_CONTENT);
        }

    }
}
