package com.example.consorciojaahvafx.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
@EqualsAndHashCode(callSuper = true)
@Data
public class Cliente extends Usuario {

    private ArrayList<Grupo> consorciosAtivos;
    private ArrayList<Contrato> historicoContratos;

    public Cliente(String nome, String CPF, String telefone, String email, String senha, long id) {
        super(nome, CPF, telefone, email, senha, id);
        this.consorciosAtivos = new ArrayList<>();
        this.historicoContratos = new ArrayList<>();
    }

    public void darLance(Grupo grupo, Double valor) { }

}                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 