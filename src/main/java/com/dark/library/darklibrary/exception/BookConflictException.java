package com.dark.library.darklibrary.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BookConflictException extends Exception{
    public BookConflictException() {
        super();
    }

    public BookConflictException (String msg) {
        super(msg);
    }

    public BookConflictException (String msg, Throwable cause) {
        super(msg, cause);
    }
}
