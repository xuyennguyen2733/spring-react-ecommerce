package com.ecommerce.xn_ecom.exceptions;

import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ecommerce.xn_ecom.payload.APIResponse;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

@RestControllerAdvice
public class MyGlobalExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity< Map<String, String>> myMethodArgumentNotValidException(MethodArgumentNotValidException e) { 
    Map<String, String> response = new HashMap<>();
    e.getBindingResult().getAllErrors().forEach(err -> {
      String fieldName = ((FieldError)err).getField();
      String message = err.getDefaultMessage();
      response.put(fieldName, message);
    });

    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  } 

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<APIResponse> myResourceNotFoundException(ResourceNotFoundException e) { 
    String message = e.getMessage();
    APIResponse apiResponse = new APIResponse(message, false);
    return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND );
  }

  @ExceptionHandler(APIException.class)
  public ResponseEntity<APIResponse> myAPIException(APIException e) { 
    String message = e.getMessage();
    APIResponse apiResponse = new APIResponse(message, false);
    return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
  }
}
