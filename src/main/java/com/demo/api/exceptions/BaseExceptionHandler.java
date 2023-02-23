package com.demo.api.exceptions;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import static com.demo.api.constants.ErrorCode.FAILED_STRING_TO_INTEGER;
import static com.demo.api.constants.ErrorMessage.FAILED_CHANGING_STRING_TO_INTEGER;
import com.demo.api.errors.ApiError;

/**
 * Staff Controller Advice.
 * 
 * @author QuynhNN
 */
@ControllerAdvice
public class BaseExceptionHandler {

  @Autowired
  private MessageSource messageSource;

  /**
   * Validate user input.
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
   * Handler api exception.
   * 
   * @param e name of exception
   * @return error response
   */
  @ExceptionHandler(ApiException.class)
  public ResponseEntity<ApiError> handleApiException(ApiException e) {
    return new ResponseEntity<>(new ApiError(e.getApiError().getCode(), e.getApiError().getMessage()),
        e.getStatusCode());
  }

  /**
   * Handler string input in integer field exception.
   * 
   * @param e name of exception
   * @return error message
   */
  @ExceptionHandler(HttpMessageNotReadableException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<ApiError> handlerHttpException(HttpMessageNotReadableException e) {
    return new ResponseEntity<>(new ApiError(FAILED_STRING_TO_INTEGER,
        messageSource.getMessage(FAILED_CHANGING_STRING_TO_INTEGER, null, null, Locale.ENGLISH)),
        HttpStatus.BAD_REQUEST);
  }
}
