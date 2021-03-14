package com.example.EmployeeInformation.demo.web.service;

import com.example.EmployeeInformation.demo.data.entity.Employee;
import com.example.EmployeeInformation.demo.data.repository.EmployeeRepository;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    Job job;

    Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    public Long uploadEmployeeDataFromFile(MultipartFile file) throws IOException, JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        Map<String, JobParameter> maps = new HashMap<>();

        //to upload the file locally, and then pick up during batch job
        File fileToImport = new File("src/main/resources/tmpUploads/" + file.getOriginalFilename());
        OutputStream outputStream = new FileOutputStream(fileToImport);
        IOUtils.copy(file.getInputStream(), outputStream);
        outputStream.flush();
        outputStream.close();

        maps.put("fullPathFileName", new JobParameter(fileToImport.getAbsolutePath()));
        JobParameters parameters = new JobParameters(maps);
        JobExecution jobExecution = jobLauncher.run(job, parameters);

        logger.info("Received request for uploading file: " + file.getOriginalFilename() +
                " Job Execution status: " + jobExecution.getStatus());

        while (jobExecution.isRunning()) {
            logger.info("Uploading Employee data..");
        }

        return jobExecution.getJobId();
    }

    public void saveEmployee(Employee employee){
        employeeRepository.save(employee);
    }

    public boolean updateEmployee(Employee employee){
        Optional<Employee> employeeInDb= employeeRepository.findById(employee.getEmployeeId());
        if(employeeInDb.isPresent()){
            employeeRepository.save(employee);
            return true;
        }else
            return false;
    }

    public Optional<Employee> getEmployeeDetails(Long empId){
        return employeeRepository.findById(empId);
    }

    public void deleteEmployeeDetails(Long empId){
        employeeRepository.deleteById(empId);
    }

}
