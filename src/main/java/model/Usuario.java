package model;

import lombok.Data;

@Data
public abstract class Usuario {
    private String nome;
    private long CPF;
    private String telefone;
    private String email;
    private String senha;

    public Usuario(){}
    public Usuario(String nome, long CPF, String telefone, String email, String senha) {
        this.nome = nome;
        this.CPF = CPF;
        this.telefone = telefone;
        this.email = email;
        this.senha = senha;
    }

}
