package com.demo.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class HttpExceptionHandle extends ApiException {
    
    public HttpExceptionHandle(String code, String mess) {
        super(code, mess, HttpStatus.BAD_REQUEST);
    }
}
