package com.example.consorciojaahvafx.controller;

import java.util.List;

import com.example.consorciojaahvafx.enums.Premiacao;
import com.example.consorciojaahvafx.model.Consorcio;
import com.example.consorciojaahvafx.model.Grupo;
import com.example.consorciojaahvafx.repository.ConsorcioRepository;

public class ConsorcioController {
    private static ConsorcioController instance;
    private ConsorcioRepository consorcioRepository;

    public static ConsorcioController getInstance() {
        if (instance == null) {
            instance = new ConsorcioController();
        }
        return instance;
    }

    public ConsorcioController() {
        this.consorcioRepository = ConsorcioRepository.getInstance();
    }

    public Consorcio criarConsorcio() {
        try {
            Consorcio consorcio = new Consorcio();
            if (consorcioRepository == null) {
                throw new IllegalStateException("ConsorcioRepository não está inicializado.");
            }
            consorcioRepository.add(consorcio);
            return consorcio;
        } catch (Exception e) {
            System.err.println("Erro ao criar consórcio: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public void escolherPremiacao(Grupo grupo, Consorcio consorcio, Premiacao premiacao) {
        if (grupo.getParticipantes().size() >= 3) {
            switch (premiacao) {
                case Casa:
                    grupo.setNumeroParcelas(180);
                    grupo.setTaxaAdm(60000);
                    grupo.setValorTotal(300000 + grupo.getTaxaAdm());
                    consorcio.setPremiacao(Premiacao.Casa);
                    consorcio.setValorRestante(grupo.getValorTotal());
                    break;
                case Apartamento:
                    grupo.setNumeroParcelas(280);
                    grupo.setValorTotal(500000);
                    grupo.setTaxaAdm(90000);
                    consorcio.setPremiacao(Premiacao.Apartamento);
                    consorcio.setValorRestante(grupo.getValorTotal());
                    break;
                case Carro:
                    grupo.setNumeroParcelas(72);
                    grupo.setValorTotal(80000);
                    grupo.setTaxaAdm(12000);
                    consorcio.setPremiacao(Premiacao.Carro);
                    consorcio.setValorRestante(grupo.getValorTotal());
                    break;
                case Moto:
                    grupo.setNumeroParcelas(180);
                    grupo.setValorTotal(300000);
                    grupo.setTaxaAdm(60000);
                    consorcio.setPremiacao(Premiacao.Moto);
                    consorcio.setValorRestante(grupo.getValorTotal());
                    break;
                case Console:
                    grupo.setNumeroParcelas(48);
                    grupo.setValorTotal(25000);
                    grupo.setTaxaAdm(3750);
                    consorcio.setPremiacao(Premiacao.Console);
                    consorcio.setValorRestante(grupo.getValorTotal());
                    break;
                default:
                    System.out.println("Premiação Inválida.");
                    break;
            }
        } else {
            System.out.println("O grupo não possui participantes suficientes para escolher a premiação.");
        }
    }

    public Consorcio obterConsorcio(Long id) {
        return consorcioRepository.findById(id);
    }

    public List<Consorcio> listarConsorcios() {
        return consorcioRepository.findAll();
    }

    public void imprimirConsorcios() {
        for (Consorcio consorcio : consorcioRepository.findAll()) {
            System.out.println("O consórcio é formado por: " + consorcio);
        }
    }

    public void adicionarGrupo(Long id, Grupo grupo) {
        Consorcio consorcio = consorcioRepository.findById(id);
        if (consorcio != null) {
            consorcio.getGrupos().add(grupo);
            consorcioRepository.update(consorcio);
            System.out.println("Grupo adicionado com sucesso ao consórcio " + id);
        } else {
            System.out.println("Consórcio não encontrado com o ID " + id);
        }

    }

    public void atualizarConsorcio(Long id, Consorcio consorcioAtualizado) {
        Consorcio consorcio = consorcioRepository.findById(id);
        if (consorcio != null) {
            consorcio.setDataInicio(consorcioAtualizado.getDataInicio());
            consorcio.setDataSorteio(consorcioAtualizado.getDataSorteio());
            consorcio.setGrupos(consorcioAtualizado.getGrupos());
            consorcio.setValorRestante(consorcioAtualizado.getValorRestante());
            consorcio.setPremiacao(consorcioAtualizado.getPremiacao());
            consorcio.setContemplados(consorcioAtualizado.getContemplados());
            consorcio.setParcelasPagas(consorcioAtualizado.getParcelasPagas());
            consorcioRepository.update(consorcio);
            System.out.println("Consórcio atualizado com sucesso.");
        } else {
            System.out.println("Consórcio não encontrado com o ID " + id);
        }
    }

    public void removerConsorcio(Long id) {
        Consorcio consorcio = consorcioRepository.findById(id);
        if (consorcio != null) {
            consorcioRepository.remove(consorcio);
            System.out.println("Consórcio removido com sucesso.");
        } else {
            System.out.println("Consórcio não encontrado com o ID " + id);
        }
    }

}
