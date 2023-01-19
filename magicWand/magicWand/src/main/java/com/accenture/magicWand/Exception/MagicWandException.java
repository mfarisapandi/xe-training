package com.accenture.magicWand.Exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class MagicWandException extends RuntimeException{

    public MagicWandException() {
    }
    public MagicWandException(String message) {
        super(message);
    }
}
