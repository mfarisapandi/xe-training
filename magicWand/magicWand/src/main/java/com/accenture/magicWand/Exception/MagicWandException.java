package com.accenture.magicWand.Exception;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MagicWandException extends RuntimeException{

    public static final String ID_DOES_NOT_EXIST =
            "Wizard does not exist";

    public MagicWandException(String message) {
        super(message);
    }
}
