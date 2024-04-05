package org.allysoncp.repositories.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends  StandardError {


    private List<FildMessage> errors = new ArrayList<>();

    public ValidationError() {
        super();
    }

    public ValidationError(Long timestamp, Integer status, String error) {
        super(timestamp, status, error);
    }

    public List<FildMessage> getErros() {
        return errors;
    }

    public void addErros(String fildName, String message) {
        this.errors.add(new FildMessage(fildName, message));
    }
}