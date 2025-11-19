package com.cwa.Todo_List.exception;

public class TodoNotFoundException extends RuntimeException {

    public TodoNotFoundException(Long id) {
        super("Todo with id " + id + " not found");
    }

    public TodoNotFoundException(String message) {
        super(message);
    }
}