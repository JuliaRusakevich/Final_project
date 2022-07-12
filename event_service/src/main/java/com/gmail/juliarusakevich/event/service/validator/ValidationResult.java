package com.gmail.juliarusakevich.event.service.validator;

import java.util.ArrayList;
import java.util.List;

public final class ValidationResult {

    private final List<java.lang.Error> errors = new ArrayList<>();

    public List<java.lang.Error> getErrors() {
        return errors;
    }

    public void add(java.lang.Error error) {
        this.errors.add(error);
    }

    public boolean isValid() {
        return errors.isEmpty();
    }
}
