package com.gmail.juliarusakevich.event.validator.api;


import com.gmail.juliarusakevich.event.validator.ValidationResult;

public interface IValidator<T> {

    ValidationResult isValid(T object);

}
