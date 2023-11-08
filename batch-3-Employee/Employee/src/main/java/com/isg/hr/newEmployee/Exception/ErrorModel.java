package com.isg.hr.newEmployee.Exception;



import java.sql.Date;


import org.springframework.http.HttpStatus;

public class ErrorModel {
    private HttpStatus httpStatus;
    private Date date;
    private String message;
    private String details;

    public ErrorModel(HttpStatus httpStatus, Date date, String message, String details) {
        this.httpStatus = httpStatus;
        this.date = date;
        this.message = message;
        this.details = details;
    }


    public HttpStatus getHttpStatus() {
        return httpStatus;
    }


    public Date getDate() {
        return date;
    }


    public String getMessage() {
        return message;
    }


    public String getDetails() {
        return details;
    }


  


    
    
}
