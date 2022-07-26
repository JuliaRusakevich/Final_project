package com.gmail.juliarusakevich.classifier.controller.advice;

import com.gmail.juliarusakevich.classifier.dto.errors.ErrorMessage;
import com.gmail.juliarusakevich.classifier.dto.errors.StructuredError;
import com.gmail.juliarusakevich.classifier.validator.ValidationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class GlobalHandler {

    public static final String MESSAGE_INTERNAL_SERVER_ERROR =
            "Сервер не смог корректно обработать запрос. Пожалуйста обратитесь к администратору";
    public static final String MESSAGE_BAD_REQUEST =
            "Запрос некорректен. Сервер не может обработать запрос";

    /*
        @ExceptionHandler
        @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
        public Map<String, Object> handle(Throwable e) {
            return Map.of(
                    "logref", "error",
                    "message", MESSAGE_INTERNAL_SERVER_ERROR
            );
        }

     */
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(BAD_REQUEST)
    public ErrorMessage handle(IllegalArgumentException e) {
        return new ErrorMessage(e.getMessage());
    }

   @ExceptionHandler(ValidationException.class)
    @ResponseStatus(BAD_REQUEST)
    public StructuredError handle(ValidationException e) {
        return new StructuredError(e.getErrorMessages());
   }

}
//StaleObjectStateException
