package com.gmail.juliarusakevich.classifier.validator.api;


import com.gmail.juliarusakevich.classifier.dto.errors.ErrorMessage;
import com.gmail.juliarusakevich.classifier.validator.ValidationResult;

import java.util.List;

public interface IValidator<T> {

    ValidationResult isValid(T dto);

}
