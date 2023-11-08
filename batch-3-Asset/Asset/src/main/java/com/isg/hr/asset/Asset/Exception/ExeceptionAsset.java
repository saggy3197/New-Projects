package com.isg.hr.asset.Asset.Exception;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


import com.isg.hr.asset.Asset.ResponseBean.AssetResponseBean;




@ControllerAdvice
public class ExeceptionAsset extends ResponseEntityExceptionHandler{

    
    @Autowired
    public AssetResponseBean response;

    @Override
    @Nullable
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
            HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        // TODO Auto-generated method stub
        ErrorModel error = new ErrorModel(HttpStatus.BAD_REQUEST,null,"validationerror",ex.getMessage().split(";")[5]);
        return super.handleHttpRequestMethodNotSupported(ex, headers, status, request);
    }

    @Override
    @Nullable
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        // TODO Auto-generated method stub
        ErrorModel error = new ErrorModel(HttpStatus.BAD_REQUEST, null, "Method Not Supported",ex.getMessage());    
        return super.handleMethodArgumentNotValid(ex, headers, status, request);
    }
    


}
