package com.ichin23.salbum.controllers.exceptions;

import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

public record ExceptionDTO(
        String message,
        String details,
        LocalDateTime date
) {
    public ExceptionDTO(String message, String details){
        this(
                message,
                details,
                LocalDateTime.now()
        );
    }
}
