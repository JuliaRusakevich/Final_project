package com.gmail.juliarusakevich.classifier.controller.advice;

import com.gmail.juliarusakevich.classifier.dto.errors.ErrorMessage;
import com.gmail.juliarusakevich.classifier.dto.errors.StructuredError;
import com.gmail.juliarusakevich.classifier.validator.ValidationException;
import org.hibernate.StaleObjectStateException;
import org.postgresql.util.PSQLException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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
    //StaleObjectStateException при оптимистической блокировке
    @ExceptionHandler(StaleObjectStateException.class)
    @ResponseStatus(BAD_REQUEST)
    public ErrorMessage handle(StaleObjectStateException e){
        return new ErrorMessage(e.getLocalizedMessage());
    }

   @ExceptionHandler(ValidationException.class)
    @ResponseStatus(BAD_REQUEST)
    public StructuredError handle(ValidationException e) {
        return new StructuredError(e.getErrorMessages());
   }

}

