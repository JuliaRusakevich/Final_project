package com.gmail.juliarusakevich.classifier.dto.errors;


import java.util.ArrayList;
import java.util.List;

public class StructuredError {

    private final String logref;
    private final List<ErrorMessage> errors;


    public StructuredError(List<ErrorMessage> errors) {
        this.logref = "structured_error";
        this.errors = errors;
    }

    public String getLogref() {
        return logref;
    }

    public List<ErrorMessage> getErrors() {
        return errors;
    }


}
