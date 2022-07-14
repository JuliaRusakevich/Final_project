package com.gmail.juliarusakevich.user.controller.advice;

import com.gmail.juliarusakevich.user.validator.errors.ErrorMessage;
import com.gmail.juliarusakevich.user.validator.errors.StructuredError;
import com.gmail.juliarusakevich.user.validator.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import javax.persistence.OptimisticLockException;


import static org.springframework.http.HttpStatus.BAD_REQUEST;
/*
400 - Запрос некорректен. Сервер не может обработать запрос
401 - Для выполнения запроса на данный адрес требуется передать токен авторизаци
403 - Данному токенту авторизации запрещено выполнять запроса на данный адрес
500 - Внутренняя ошибка сервера. Сервер не смог корректно обработать запрос
 */
@RestControllerAdvice
public class ErrorHandlingControllerAdvice {

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(BAD_REQUEST)
    public ErrorMessage handle(IllegalArgumentException e) {
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