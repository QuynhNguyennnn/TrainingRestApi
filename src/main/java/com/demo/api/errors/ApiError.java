package com.demo.api.errors;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Api Error.
 * 
 * @author QuynhNN
 */
@Data
@AllArgsConstructor
public class ApiError {
    private String code;
    private String message;
}
