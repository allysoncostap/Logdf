package org.allysoncp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;
@Entity
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;
    private String email;
    private String telefone;
@JsonIgnore
    @OneToMany(mappedBy = "usuario")
    private List<Chamado> chamados;

    private String edificio;
    private String sala;
    private int andar;
    private boolean ativo;


    private Filas filas;

    public Usuario() {
    }

    public Usuario(Integer id , String nome, String email, String telefone, String edificio, String sala, int andar, boolean ativo, Filas filas) {

        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.edificio = edificio;
        this.sala = sala;
        this.andar = andar;
        this.ativo = ativo;
        this.filas = filas;
    }

    public Usuario(String nome, String email, String telefone, String edificio, String sala, int andar, boolean ativo, Filas filas) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.edificio = edificio;
        this.sala = sala;
        this.andar = andar;
        this.ativo = ativo;
        this.filas = filas;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public List<Chamado> getChamados() {
        return chamados;
    }

    public void setChamados(List<Chamado> chamados) {
        this.chamados = chamados;
    }

    public String getEdificio() {
        return edificio;
    }

    public void setEdificio(String edificio) {
        this.edificio = edificio;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    public int getAndar() {
        return andar;
    }

    public void setAndar(int andar) {
        this.andar = andar;
    }

    public boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Filas getFilas() {
        return filas;
    }

    public void setFilas(Filas filas) {
        this.filas = filas;
    }



}