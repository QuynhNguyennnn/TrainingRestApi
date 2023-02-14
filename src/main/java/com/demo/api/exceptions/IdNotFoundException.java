package com.demo.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Id Not Found Exception.
 * 
 * @author QuynhNN
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class IdNotFoundException extends APIException {

    public IdNotFoundException() {
        super("id_not_found", "Couldn't find id!", HttpStatus.NOT_FOUND);
    }

    public IdNotFoundException(String code, String mess){
        super(code, mess, HttpStatus.NOT_FOUND);
    }

}
