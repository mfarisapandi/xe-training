package com.accenture.order.Exception;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class OrderException extends RuntimeException{

    public static final String ERROR_GETTING_LIST =
            "Error getting list";

    public static final String ID_DOES_NOT_EXIST =
            "Order ID does not exist";

    public OrderException(String message) {
        super(message);
    }

}
