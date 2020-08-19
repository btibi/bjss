package com.bjss.exception;

public class CvNotFoundException extends RuntimeException {
    public CvNotFoundException(Long id) {
        super("Could not find CV " + id);
    }
}
