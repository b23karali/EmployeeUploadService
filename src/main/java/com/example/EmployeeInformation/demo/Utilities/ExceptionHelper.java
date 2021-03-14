package com.example.EmployeeInformation.demo.Utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHelper {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionHelper.class);

    @ExceptionHandler(value = { Exception.class })
    public ResponseEntity<Object> handleTimedOutException(Exception ex){
        logger.error("Unhandled Error: "+ex.getMessage());
        return new ResponseEntity(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ex.getMessage(), ex.toString(),
                "Unhandled Exception Occurred"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
