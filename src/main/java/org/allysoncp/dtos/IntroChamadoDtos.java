package org.allysoncp.dtos;

import org.allysoncp.entity.Chamado;
import org.allysoncp.entity.Filas;
import org.allysoncp.entity.Status;

import java.io.Serializable;

public class IntroChamadoDtos implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private Status status;
    private Filas filas;

    // Alterado o tipo para manter a informação do tempo
    private String date;

    public IntroChamadoDtos() {
    }

    public IntroChamadoDtos(Chamado obj) {
        this.id= obj.getId();
        this.date = obj.getDate();
        this.filas = obj.getFilas();
        this.status = obj.getStatus();

    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Filas getFilas() {
        return filas;
    }

    public void setFilas(Filas filas) {
        this.filas = filas;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
