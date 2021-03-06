package com.gmail.juliarusakevich.user.validator;

import java.util.ArrayList;
import java.util.List;

public final class ValidationResult {

    private final List<Error> errors = new ArrayList<>();

    public List<Error> getErrors() {
        return errors;
    }

    public void add(Error error) {
        this.errors.add(error);
    }

    public boolean isValid() {
        return errors.isEmpty();
    }
}
