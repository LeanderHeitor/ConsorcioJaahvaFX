package com.example.consorciojaahvafx.model;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Grupo {
    private String nome;
    private Long id;  
    private int numeroParcelas;
    private double valorTotal;
    private Admin supervisor;
    private double taxaAdm;
    private double valorArrecadadoAtualizado;
    private ArrayList<Cliente> participantes;
    private boolean grupoAtivo;
    private static final int MIN_PARTICIPANTES = 3;
    
    private Consorcio contemplacao;

    public Grupo(Admin supervisor, int numeroParcelas, double valorTotal, double taxaAdm) {
        this.id = null;
        this.supervisor = supervisor;
        this.numeroParcelas = numeroParcelas;
        this.valorTotal = valorTotal;
        this.taxaAdm = taxaAdm;
        this.grupoAtivo = false;
        this.participantes = new ArrayList<>();
    }

    public Grupo(Admin admin, Consorcio consorcio) {
        this.supervisor = admin;
        this.contemplacao = consorcio;
        this.participantes =  new ArrayList<>();
    }

    public void adicionarParticipante(Cliente cliente) {
        this.participantes.add(cliente);
        verificarAtivacaoGrupo();
    }

    public void removerParticipante(Cliente cliente) {
        if (this.participantes.contains(cliente)) {
            this.participantes.remove(cliente);
        } else {
            throw new IllegalArgumentException("Cliente não encontrado no grupo.");
        }
    }

    private void verificarAtivacaoGrupo() {
        if (this.participantes.size() >= MIN_PARTICIPANTES) {
            this.grupoAtivo = true;
        }
    }

    public boolean isGrupoAtivo() {
        return grupoAtivo;
    }

    public ArrayList<Cliente> getParticipantes() {
        return participantes;
    }


    

    

    // public void cancelarLance(long cpfCliente) {
    //     if (lances.containsKey(cpfCliente)) {
    //         lances.remove(cpfCliente);
    //     } else {
    //         throw new IllegalArgumentException("Cliente não possui um lance neste grupo.");
    //     }
    // }

    public void reembolsarLance(double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("O valor do reembolso dese ser positivo.");
        }
        this.valorTotal = valor;
        System.out.println("Reembolso de " + valor + " realizado com sucesso.");
    }

    

}
