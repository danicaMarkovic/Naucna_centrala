package com.example.nCentrala.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FileExceptions extends RuntimeException {
    public FileExceptions(String message) {
        super(message);
    }

    public FileExceptions(String message, Throwable cause) {
        super(message, cause);
    }
}