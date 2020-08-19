package com.bjss.controller.advice;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.bjss.exception.CvNotFoundException;

public class CvNotFoundAdviceTest {
    @Test
    public void employeeNotFoundHandlerConvertErrorMsg() {
        String errorMsg = new CvNotFoundAdvice().employeeNotFoundHandler(new CvNotFoundException(1L));

        assertEquals("Could not find CV 1", errorMsg);
    }
}