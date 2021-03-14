# EmployeeUploadService

# Demo Link : https://drive.google.com/file/d/11uYITzTTmxYHInNGu9FF9eVXJnxx_R6k/view?usp=sharing![image](https://user-images.githubusercontent.com/12246711/111082348-c5979400-852d-11eb-83ab-0363e86d87a3.png)

Employee uploading from Flat File and CRUD operations

APIs:

1. API to accept uploading of data as a flat file with line separated data
   URI : POST /api/employee?action=upload
   Response: JobId
   
2. API to query current Job Running Status
   GET /api/getJobStatus?jobId={jobID}
   Response: Current Status of Job
   
3. API to get all employee details present
   GET /api/getAll
   Response: All the employees present in Database

4. Create Employee
   POST /api/employees
   RequestBody : {
                      "employeeName" : "TestUser1",
                      "age" : "22"
                  }
   
   Response: 201 Created
   
5. Get Employee Details
   GET /api/employees/{employeeId}
   Response: {        
                      "employeeId" : Long
                      "employeeName" : String,
                      "age" : Integer
                  }
   200 Success / 204 Resource Not Found
   
6. Update Employee
   PUT /api/employees
   RequestBody : {
                      "employeeId" : Long
                      "employeeName" : String,
                      "age" : Integer
                  }
   
   Response: 200 OK
   
7. Delete Employee
   DELETE /api/employees/{employeeId}
   Response: 200 OK / 204 Resource Not Found
