package com.example.EmployeeInformation.demo.Utilities;

import java.util.Date;

public class ErrorResponse {
    private Date timeStamp;
    private Integer status;
    private String error;
    private String message;

    public ErrorResponse() {
    }

    public ErrorResponse(Integer status, String error, String exception, String message) {
        this.status = status;
        this.error = error;
        this.message = message;
        this.timeStamp = new Date();
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}
