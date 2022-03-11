package com.example.Seniority.Boost2.exception;

import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class CustomizedResponseEntityExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<ErrorInfo> handleNotFoundException(NotFoundException ex, WebRequest request) {
        String message = ex.getMessage();
        ErrorInfo exceptionResponse = new ErrorInfo(HttpStatus.NOT_FOUND.value(), message,
        ((ServletWebRequest)request).getRequest().getRequestURI(), true);
//               ESTA LINEA ES PARA DEVOLVER URL request.getDescription(false), true);
        return new ResponseEntity<ErrorInfo>(exceptionResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(BadRequestException.class)
    public final ResponseEntity<ErrorInfo> handleBadReqException(BadRequestException ex, WebRequest request) {
        String message;
//        System.out.println(ex.getMessage());
        if(ex.getMessage().isEmpty() || ex.getMessage() == "No message available") {
            message = "An error occurred when processing the text";
        }else{
            message = ex.getMessage();
        }
        ErrorInfo exceptionResponse = new ErrorInfo(HttpStatus.BAD_REQUEST.value(), message,
                ((ServletWebRequest)request).getRequest().getRequestURI(), true);
        return new ResponseEntity<ErrorInfo>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

//    modificando aca abajo las excepciones que vienen por defecto
    @ExceptionHandler(NumberFormatException.class)
    public final ResponseEntity<ErrorInfo> numberFormatException(WebRequest request, NumberFormatException ex) {
        String message = "Error: " + ex.getMessage();
//        String message = "An error occurred when processing the text";
        ErrorInfo exceptionResponse = new ErrorInfo(HttpStatus.UNPROCESSABLE_ENTITY.value(), message,
                ((ServletWebRequest)request).getRequest().getRequestURI(), true);
        return new ResponseEntity<ErrorInfo>(exceptionResponse, HttpStatus.UNPROCESSABLE_ENTITY);
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public final ResponseEntity<ErrorInfo> ilegalArgumentException(WebRequest request, IllegalArgumentException ex) {
        String message = "Error: " + ex.getMessage();
        ErrorInfo exceptionResponse = new ErrorInfo(HttpStatus.BAD_REQUEST.value(), message,
                ((ServletWebRequest)request).getRequest().getRequestURI(), true);
        return new ResponseEntity<ErrorInfo>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
