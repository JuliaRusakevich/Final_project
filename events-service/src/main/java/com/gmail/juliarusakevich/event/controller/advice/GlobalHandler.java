package com.gmail.juliarusakevich.event.controller.advice;


import com.gmail.juliarusakevich.event.validator.ValidationException;
import com.gmail.juliarusakevich.event.dto.errors.ErrorMessage;
import com.gmail.juliarusakevich.event.dto.errors.StructuredError;
import org.postgresql.util.PSQLException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import javax.persistence.EntityNotFoundException;
import javax.persistence.OptimisticLockException;


import java.util.Objects;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class GlobalHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(BAD_REQUEST)
    public ErrorMessage handle(IllegalArgumentException e) {
        return new ErrorMessage(e.getMessage());
    }

    //HttpMessageNotReadableException
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(BAD_REQUEST)
    public ErrorMessage handle(HttpMessageNotReadableException e) {
        return new ErrorMessage(e.getMessage());
    }
    @ExceptionHandler(OptimisticLockException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorMessage handle(OptimisticLockException e){
        return new ErrorMessage(
                e.getMessage()
        );
    }

    @ExceptionHandler(PSQLException.class)
    @ResponseStatus(BAD_REQUEST)
    public ErrorMessage handle(PSQLException e){
        return new ErrorMessage(Objects.requireNonNull(e.getServerErrorMessage()).getDetail());
    }

    //EntityNotFoundException
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(BAD_REQUEST)
    public ErrorMessage handle(EntityNotFoundException e) {
        return new ErrorMessage(e.getMessage());
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(BAD_REQUEST)
    public StructuredError handle(ValidationException e) {
        return new StructuredError(e.getErrorMessages());
    }

}

/*
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<Map<String, Object>> handle(IllegalArgumentException e) {
        List<Map<String, Object>> data = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("logref", "error");
        map.put("message", e.getMessage());
        data.add(map);
        return data;
    }
 */
