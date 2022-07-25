package com.gmail.juliarusakevich.users.controller.advices;


import com.gmail.juliarusakevich.users.validator.ValidationException;
import com.gmail.juliarusakevich.users.dto.errors.ErrorMessage;

import com.gmail.juliarusakevich.users.dto.errors.StructuredError;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.OptimisticLockException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;


@RestControllerAdvice
public class GlobalHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(BAD_REQUEST)
    public ErrorMessage handle(IllegalArgumentException e) {
        return new ErrorMessage(
                e.getMessage()
        );
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(BAD_REQUEST)
    public ErrorMessage handle(UsernameNotFoundException e) {
        return new ErrorMessage(
                e.getMessage()
        );
    }

    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(BAD_REQUEST)
    public ErrorMessage handle(AuthenticationException e) {
        return new ErrorMessage(
                e.getMessage()
        );
    }

    @ExceptionHandler(OptimisticLockException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorMessage handle(OptimisticLockException e) {
        return new ErrorMessage(
                e.getMessage()
        );
    }

    //HttpMessageNotReadableException - если enum неверно передан
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(BAD_REQUEST)
    public ErrorMessage handle(HttpMessageNotReadableException e) {
        return new ErrorMessage(
              e.getMessage()

        );
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(BAD_REQUEST)
    public StructuredError handle(ValidationException e) {
        return new StructuredError(e.getErrorMessages());
    }

}
