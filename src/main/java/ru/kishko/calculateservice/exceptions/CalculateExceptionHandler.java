package ru.kishko.calculateservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CalculateExceptionHandler {

    @ExceptionHandler(value = {InputException.class})
    public ResponseEntity<Object> handleProjectNotFoundException(InputException inputException) {

        CalculateException calculateException = new CalculateException(
                inputException.getMessage(),
                inputException.getCause(),
                HttpStatus.BAD_REQUEST
        );

        return new ResponseEntity<>(calculateException, calculateException.getHttpStatus());

    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<Object> handleProjectMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException) {

        CalculateException calculateException = new CalculateException(
                methodArgumentNotValidException.getMessage(),
                methodArgumentNotValidException.getCause(),
                HttpStatus.BAD_REQUEST
        );

        return new ResponseEntity<>(calculateException, calculateException.getHttpStatus());

    }

    @ExceptionHandler(value = {DateException.class})
    public ResponseEntity<Object> handleDateException(DateException dateException) {

        CalculateException calculateException = new CalculateException(
                dateException.getMessage(),
                dateException.getCause(),
                HttpStatus.BAD_REQUEST
        );

        return new ResponseEntity<>(calculateException, calculateException.getHttpStatus());

    }

}
