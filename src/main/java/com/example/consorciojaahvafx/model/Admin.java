package com.example.consorciojaahvafx.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.ArrayList;
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class Admin extends Usuario {
    private ArrayList<Grupo> gruposVistoria;
    private boolean admin;

    public Admin(){
        this.gruposVistoria = new ArrayList<>();
    }

    public Admin(String nome, String CPF, String telefone, String email, String senha, Long id) {
        super(nome, CPF, telefone, email, senha);
        this.gruposVistoria = new ArrayList<>();
    }

    public ArrayList<Grupo> getGruposVistoria() {
        return gruposVistoria;
    }

    public void setGruposVistoria(ArrayList<Grupo> gruposVistoria) {
        this.gruposVistoria = gruposVistoria;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}