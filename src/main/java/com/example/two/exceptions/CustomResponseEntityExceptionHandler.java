package com.example.two.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public final ResponseEntity<Object> handleUserException(UserIdException ex, WebRequest request){
        UserIdExceptionResponse exceptionResponse = new UserIdExceptionResponse(ex.getMessage());
        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleProductException(ProductNotFoundException ex, WebRequest request){
        ProductNotFoundExceptionResponse exceptionResponse = new ProductNotFoundExceptionResponse(ex.getMessage());
        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value={ApiRequestException.class})
    public ResponseEntity<Object> handleApiRequestException(ApiRequestException e){
        HttpStatus badRequest=HttpStatus.BAD_REQUEST;

        ApiException apiException= new ApiException(
                e.getMessage(),
                e,
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(apiException,badRequest);
    }
}


