package com.example.consorciojaahvafx.model;

import lombok.Data;

@Data
public abstract class Usuario {
    private String nome;
    private String CPF;
    private String telefone;
    private String email;
    private String senha;
    private Long id;

    public Usuario(){}
    public Usuario(String nome, String CPF, String telefone, String email, String senha) {
        this.nome = nome;
        this.CPF = CPF;
        this.telefone = telefone;
        this.email = email;
        this.senha = senha;
        this.id = null;
    }

    
}
