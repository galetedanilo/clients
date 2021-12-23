package com.galetedanilo.clients.controllers.exceptions;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError{

    @Getter
    private List<FieldMessage> fieldMessages = new ArrayList<>();

    public void addFieldMessage(String name, String message) {
        fieldMessages.add(new FieldMessage(name, message));
    }
}
