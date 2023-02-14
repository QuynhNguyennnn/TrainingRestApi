package com.demo.api.exceptions;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.demo.api.errors.ApiError;

/**
 * Staff Controller Advice.
 * 
 * @author QuynhNN
 */
@ControllerAdvice
public class StaffControllerAdvice {

  /**
   * validate user input.
   * 
   * @param ex name of exception
   * @return error response
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
      Map<String, Object> body = new HashMap<>();

      List<String> errors = ex.getBindingResult()
        .getFieldErrors()
        .stream()
        .map(DefaultMessageSourceResolvable::getDefaultMessage)
        .collect(Collectors.toList());

        body.put("errors", errors);

      return new ResponseEntity<>(body, HttpStatus.UNPROCESSABLE_ENTITY);
  }

  /**
   * catch run time exception. 
   * 
   * @param e name of exception
   * @return error response
   */
  @ExceptionHandler(APIException.class)
  public ResponseEntity<ApiError> handleApiException(APIException e) {
    return new ResponseEntity<>(new ApiError(e.getApiError().getCode(), e.getApiError().getMessage()), e.getStatusCode());
    
  }
}
