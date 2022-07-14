package com.gmail.juliarusakevich.user.validator.api;


public interface IValidator<T> {

    boolean isValid(T dto);

}
