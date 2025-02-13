package model;

import com.itextpdf.layout.Document;

import enums.Premiacao;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.HashMap;

@Data
@ToString(exclude = {"lances", "verificaContrato"})
public class Grupo /*implements Processo*/ {
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
    private HashMap<Cliente, Double> lances;
    private HashMap<Contrato, Boolean> verificaContrato;

    public Grupo(Admin supervisor, int numeroParcelas, double valorTotal, double taxaAdm) {
        this.id = null;
        this.supervisor = supervisor;
        this.numeroParcelas = numeroParcelas;
        this.valorTotal = valorTotal;
        this.taxaAdm = taxaAdm;
        this.grupoAtivo = false;
        this.participantes = new ArrayList<>();
        this.lances = new HashMap<>();
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

    // @Override
    // public Document sendRelatorio() {
    //     // Implementação do método
    // }
}
