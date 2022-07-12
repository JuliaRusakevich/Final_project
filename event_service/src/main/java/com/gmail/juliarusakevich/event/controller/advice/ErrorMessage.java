package com.gmail.juliarusakevich.event.controller.advice;
/*
401	Для выполнения запроса на данный адрес требуется передать токен авторизации
403	Данному токенту авторизации запрещено выполнять запроса на данный адрес
500	Внутренняя ошибка сервера. Сервер не смог корректно обработать запрос
 */

public class ErrorMessage {
    public static final String MESSAGE_INTERNAL_SERVER_ERROR =
            "Сервер не смог корректно обработать запрос. Пожалуйста обратитесь к администратору";
    public static final String MESSAGE_BAD_REQUEST =
            "Запрос некорректен. Сервер не может обработать запрос";

    private ErrorMessage() {
    }
}
