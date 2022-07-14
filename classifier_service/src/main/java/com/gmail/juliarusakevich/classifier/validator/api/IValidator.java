package com.gmail.juliarusakevich.classifier.validator.api;


public interface IValidator<T> {

    boolean isValid(T dto);

}
