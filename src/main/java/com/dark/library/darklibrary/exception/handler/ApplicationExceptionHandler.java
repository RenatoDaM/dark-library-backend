package com.dark.library.darklibrary.exception.handler;

import com.dark.library.darklibrary.exception.BookConflictException;
import com.dark.library.darklibrary.exception.BookNotFoundException;
import com.dark.library.darklibrary.response.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.time.LocalDateTime;
@RestControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    ErrorResponse errorResponse;

    @ExceptionHandler(value = { BookNotFoundException.class })
    public ResponseEntity<ErrorResponse> bookNotFound(BookNotFoundException e) {
        errorResponse.setCode(404);
        errorResponse.setMessage(e.getLocalizedMessage());
        errorResponse.setTimestamp(LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(value = { BookConflictException.class })
    public ResponseEntity<ErrorResponse> bookConflict(BookConflictException e) {
        errorResponse.setCode(409);
        errorResponse.setMessage(e.getLocalizedMessage());
        errorResponse.setTimestamp(LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }
}
