package com.demo.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Id Not Found Exception.
 * 
 * @author QuynhNN
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class IdNotFoundException extends ApiException {

    /**
     * Constructor. 
     * 
     * @param code code of error
     * @param mess message of error
     */
    public IdNotFoundException(String code, String mess){
        super(code, mess, HttpStatus.NOT_FOUND);
    }

    /**
     * Another constructor no parameters.
     * 
     */
    public IdNotFoundException() {
        super("id_not_found", "Couldn't find id!", HttpStatus.NOT_FOUND);
    }

}
