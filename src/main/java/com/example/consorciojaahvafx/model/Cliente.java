package com.example.consorciojaahvafx.model;

import lombok.Data;
import lombok.ToString;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class Cliente extends Usuario {

    private ArrayList<Grupo> consorciosAtivos;
    private ArrayList<Contrato> historicoContratos;

    public Cliente(String nome, String CPF, String telefone, String email, String senha) {
        super(nome, CPF, telefone, email, senha);
        this.consorciosAtivos = new ArrayList<>();
        this.historicoContratos = new ArrayList<>();
    }

    public void darLance(Grupo grupo, Double valor) { }

    public ArrayList<Grupo> getConsorciosAtivos() {
        return consorciosAtivos;
    }

    public void setConsorciosAtivos(ArrayList<Grupo> consorciosAtivos) {
        this.consorciosAtivos = consorciosAtivos;
    }

    public ArrayList<Contrato> getHistoricoContratos() {
        return historicoContratos;
    }

    public void setHistoricoContratos(ArrayList<Contrato> historicoContratos) {
        this.historicoContratos = historicoContratos;
    }
}