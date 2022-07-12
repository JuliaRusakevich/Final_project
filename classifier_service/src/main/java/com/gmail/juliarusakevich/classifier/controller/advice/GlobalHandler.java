package com.gmail.juliarusakevich.classifier.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> handle(Throwable e) {
        return Map.of(
                "logref", "error",
                "message", ErrorMessage.MESSAGE_INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)//400 Bad Request
    public Map<String, Object> handle(IllegalArgumentException e) {
        return Map.of(
                "logref", "error",
                "message", ErrorMessage.MESSAGE_BAD_REQUEST
        );
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
