package com.accenture.wizard.Exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class WizardException extends RuntimeException{

    public WizardException() {
    }
    public WizardException(String message) {
        super(message);
    }
}
