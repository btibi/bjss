package com.bjss.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.bjss.exception.CvNotFoundException;

@ControllerAdvice
public class CvNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler({CvNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String employeeNotFoundHandler(CvNotFoundException ex) {
        return ex.getMessage();
    }
}
