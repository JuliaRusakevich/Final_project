package com.gmail.juliarusakevich.users.validator.api;

import com.gmail.juliarusakevich.users.validator.ValidationResult;
import org.springframework.stereotype.Component;

@Component
public interface IValidator<T> {
    ValidationResult isValid(T object);


}
