package com.example.Seniority.Boost2.exception;

import org.hibernate.cfg.annotations.HCANNHelper;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;


@RestControllerAdvice
public class CustomizedResponseEntityExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<ErrorInfo> handleNotFoundException(NotFoundException ex, WebRequest request) {
            String messageDefault = "An error occurred when processing the text";
            String message = ex.getMessage() == "No message available" ? messageDefault : ex.getMessage();
                ErrorInfo exceptionResponse = new ErrorInfo(HttpStatus.NOT_FOUND.value(), message,
                ((ServletWebRequest)request).getRequest().getRequestURI(), true);
//                request.getDescription(false), true);
        return new ResponseEntity<ErrorInfo>(exceptionResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(BadRequestException.class)
    public final ResponseEntity<ErrorInfo> handleBadReqException(BadRequestException ex, WebRequest request) {
        String messageDefault = "An error occurred when processing the text";
        String message = ex.getMessage() == "No message available"  ? messageDefault : ex.getMessage();
        ErrorInfo exceptionResponse = new ErrorInfo(HttpStatus.BAD_REQUEST.value(), message,
                ((ServletWebRequest)request).getRequest().getRequestURI(), true);
//                request.getDescription(false), true);
        return new ResponseEntity<ErrorInfo>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
