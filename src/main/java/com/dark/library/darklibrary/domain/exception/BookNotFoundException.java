package com.dark.library.darklibrary.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BookNotFoundException extends Exception{
    public BookNotFoundException() {
        super();
    }
    public BookNotFoundException(String msg){
        super(msg);
    }

    public BookNotFoundException(String msg, Throwable cause){
        super(msg, cause);
    }
}
