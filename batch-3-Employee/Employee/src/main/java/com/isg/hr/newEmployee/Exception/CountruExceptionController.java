package com.isg.hr.newEmployee.Exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.isg.hr.newEmployee.Responce.ResponseBean;

@ControllerAdvice
public class CountruExceptionController extends ResponseEntityExceptionHandler  {
     @Autowired
    public ResponseBean response;

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
            org.springframework.http.HttpHeaders headers, HttpStatusCode status, WebRequest request) {
       ErrorModel error = new ErrorModel(HttpStatus.BAD_REQUEST, null, "validation error",
                               ex.getMessage().split(";")[5]);

            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            org.springframework.http.HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ErrorModel error = new ErrorModel(HttpStatus.BAD_REQUEST, null, "Method not Supported, not exist",
                ex.getMessage());
              return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    
}
