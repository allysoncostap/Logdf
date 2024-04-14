package org.allysoncp.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
public class Chamado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    private String descricao;

    @Enumerated(EnumType.STRING)
    private Filas filas;

    public Chamado() {
        this.date = LocalDateTime.now();
    }

    public Chamado(Integer id, Status status, LocalDateTime date, Usuario usuario, String descricao, Filas filas) {
        this.id = id;
        this.status = status;
        this.date =date ;
        this.usuario = usuario;
        this.descricao = descricao;
        this.filas = filas;
    }

    // Método para formatar a data
    public String getDataFormatada() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss " +
                "dd/MM/yyyy");
        return date.format(formatter);
    }

    // Getters e setters...

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

    public String getDate() {
        // Verifique se this.date não é nulo antes de chamar o método format()
        if (this.date != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss yyyy-MM-dd ");
            return this.date.format(formatter);
        } else {
            // Trate o caso em que this.date é nulo
            return "Data não disponível";
        }
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Filas getFilas() {
        return filas;
    }

    public void setFilas(Filas filas) {
        this.filas = filas;
    }
}