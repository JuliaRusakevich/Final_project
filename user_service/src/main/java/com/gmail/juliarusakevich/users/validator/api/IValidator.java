package com.gmail.juliarusakevich.users.validator.api;


public interface IValidator<T> {
    boolean isValid(T object);


}
