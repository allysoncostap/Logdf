package org.allysoncp.repositories.exceptions;

import java.io.Serializable;

public class FildMessage implements Serializable {



    private  static final long serialVersionUID = 1L;

    private String fieldNome;
    private String message;

    public FildMessage() {
    }

    public FildMessage(String fieldNome, String message) {
        this.fieldNome = fieldNome;
        this.message = message;
    }

    public String getFieldNome() {
        return fieldNome;
    }

    public void setFieldNome(String fieldNome) {
        this.fieldNome = fieldNome;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
