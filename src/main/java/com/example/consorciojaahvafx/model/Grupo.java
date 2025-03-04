package com.example.consorciojaahvafx.model;

import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.HashMap;

@Data
@ToString(exclude = {"lances", "verificaContrato"})
public class Grupo {
    private Long id;  // corrigido para minúsculo (convenção de nomenclatura Java)
    private int numeroParcelas;
    private double valorTotal;
    private Admin supervisor;
    private double taxaAdm;
    private Consorcio contemplacao;
    private double valorArrecadadoAtualizado;
    private ArrayList<Cliente> participantes;
    private boolean grupoAtivo;
    private static final int MIN_PARTICIPANTES = 3;
    // lista de lances dados pelos clientes por fora das parcelas pagas
    private HashMap<Long, Double> lances;
        //TODO esse método se encaixa melhor na classe de consorcio
    private HashMap<Contrato, Boolean> verificaContrato;

    public Grupo(Admin supervisor, int numeroParcelas, double valorTotal, double taxaAdm) {
        this.id = null;
        this.supervisor = supervisor;
        this.numeroParcelas = numeroParcelas;
        this.valorTotal = valorTotal;
        this.taxaAdm = taxaAdm;
        this.grupoAtivo = false;
        this.participantes = new ArrayList<>();
        this.lances = new HashMap<Long, Double>();
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


    public HashMap<Long, Double> getLances() {
        return lances;
    }

    public void addLance(long cpfCliente, double valor) {
        lances.put(cpfCliente, valor);
    }

    public void cancelarLance(long cpfCliente) {
        if (lances.containsKey(cpfCliente)) {
            lances.remove(cpfCliente);
        } else {
            throw new IllegalArgumentException("Cliente não possui um lance neste grupo.");
        }
    }

    public void reembolsarLance(double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("O valor do reembolso dese ser positivo.");
        }
        this.valorTotal = valor;
        System.out.println("Reembolso de " + valor + " realizado com sucesso.");
    }

    public void cancelarLance(HashMap<Long, Double> lances) {
    }

}
