package com.gmail.juliarusakevich.classifier.controller.advice;

import com.gmail.juliarusakevich.classifier.validator.ValidationException;
import com.gmail.juliarusakevich.classifier.validator.errors.StructuredError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

import static org.springframework.http.HttpStatus.BAD_REQUEST;


@RestControllerAdvice
public class GlobalHandler {

    public static final String MESSAGE_INTERNAL_SERVER_ERROR =
            "Сервер не смог корректно обработать запрос. Пожалуйста обратитесь к администратору";
    public static final String MESSAGE_BAD_REQUEST =
            "Запрос некорректен. Сервер не может обработать запрос";

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> handle(Throwable e) {
        return Map.of(
                "logref", "error",
                "message", MESSAGE_INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler
    @ResponseStatus(BAD_REQUEST)//400 Bad Request
    public Map<String, Object> handle(IllegalArgumentException e) {
        return Map.of(
                "logref", "error",
                "message",MESSAGE_BAD_REQUEST
        );
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
