package com.gmail.juliarusakevich.user.validator;


import com.gmail.juliarusakevich.user.validator.errors.ErrorMessage;

import java.util.List;

public class ValidationException extends RuntimeException {

    private final List<ErrorMessage> errorMessages;

    public ValidationException(List<ErrorMessage> errorMessages) {
        this.errorMessages = errorMessages;
    }


    public List<ErrorMessage> getErrorMessages() {
        return errorMessages;
    }
}
