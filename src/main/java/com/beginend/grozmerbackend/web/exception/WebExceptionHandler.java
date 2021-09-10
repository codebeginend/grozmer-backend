package com.beginend.grozmerbackend.web.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

@ControllerAdvice
public class WebExceptionHandler extends ExceptionHandlerExceptionResolver {

    @ExceptionHandler(value = WebNotFoundException.class)
    public ResponseEntity notFoundExceptionHandler(WebNotFoundException exception){
        return new ResponseEntity(new ExceptionResponse(exception.getMessage(), HttpStatus.NOT_FOUND.value()), HttpStatus.NOT_FOUND);
    }

    @Data
    @AllArgsConstructor
    class ExceptionResponse{
        private String message;
        private int code;
    }
}
