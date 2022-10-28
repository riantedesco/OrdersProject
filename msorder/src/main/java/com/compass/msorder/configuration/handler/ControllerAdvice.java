package com.compass.msorder.configuration.handler;

import com.compass.msorder.exception.InactiveProductException;
import com.compass.msorder.exception.InvalidAttributeException;
import com.compass.msorder.exception.NotFoundAttributeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@org.springframework.web.bind.annotation.ControllerAdvice(basePackages = "com.compass.msorder.controller")
public class ControllerAdvice {

    @ResponseBody
    @ExceptionHandler(InvalidAttributeException.class)
    public ResponseEntity<MessageExceptionHandler> invalidAttributeException(InvalidAttributeException invalidAttributeException) {
        MessageExceptionHandler error = new MessageExceptionHandler(
                new Date(), HttpStatus.BAD_REQUEST.value(), "There's data in the form that are not correct or not allowed");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @ExceptionHandler(NotFoundAttributeException.class)
    public ResponseEntity<MessageExceptionHandler> notFoundAttributeException(NotFoundAttributeException notFoundAttributeException) {
        MessageExceptionHandler error = new MessageExceptionHandler(
                new Date(), HttpStatus.BAD_REQUEST.value(), "There's data in the form that doesn't exists in the database");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @ExceptionHandler(InactiveProductException.class)
    public ResponseEntity<MessageExceptionHandler> inactiveProductException(InactiveProductException inactiveProductException) {
        MessageExceptionHandler error = new MessageExceptionHandler(
                new Date(), HttpStatus.BAD_REQUEST.value(), "Inactive products in the form");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}