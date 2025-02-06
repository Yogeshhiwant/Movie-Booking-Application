package com.infy.exception;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MovieBookingCustomException {
	@ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> exceptionHandler(Exception ex){
        ErrorDetails error = new ErrorDetails("Internal Server Error", 500, LocalDateTime.now());
        return ResponseEntity.status(500).body(error);
    }

    @ExceptionHandler(MovieBookingException.class)
    public ResponseEntity<ErrorDetails> exceptionHandle(MovieBookingException movieException){
        ErrorDetails errorDetails = new ErrorDetails(movieException.getMessage(), 400, LocalDateTime.now());
        return ResponseEntity.status(400).body(errorDetails);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDetails> handleValidationException(MethodArgumentNotValidException exp){
        String errorMessage =  exp.getBindingResult().getAllErrors().get(0).getDefaultMessage();
                ErrorDetails errorInfo = new ErrorDetails(errorMessage, 400, LocalDateTime.now());
        return ResponseEntity.status(400).body(errorInfo);
    }

}
