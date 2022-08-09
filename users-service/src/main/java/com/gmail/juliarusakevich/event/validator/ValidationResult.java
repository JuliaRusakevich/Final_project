package com.gmail.juliarusakevich.event.validator;

import com.gmail.juliarusakevich.event.dto.errors.ErrorMessage;

import java.util.ArrayList;
import java.util.List;

public final class ValidationResult {

    private final List<ErrorMessage> errors = new ArrayList<>();

    public List<ErrorMessage> getErrors() {
        return errors;
    }

    public void add(ErrorMessage error) {
        this.errors.add(error);
    }

    public boolean isValid() {
        return errors.isEmpty();
    }
}
