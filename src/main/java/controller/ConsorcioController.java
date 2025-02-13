package controller;

import enums.Premiacao;
import model.Consorcio;
import model.Grupo;
import repository.ConsorcioRepository;

public class ConsorcioController {
    private ConsorcioRepository consorcioRepository;

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

    public void escolherPremiacao(Grupo grupo, Consorcio consorcio){
        
            if(grupo.getParticipantes().size() >= 3){
                switch (consorcio.getPremiacao()) {
                    case Casa:
                        grupo.setNumeroParcelas(180);
                        grupo.setTaxaAdm(60000);
                        grupo.setValorTotal(300000 + grupo.getTaxaAdm());
                        consorcio.setPremiacao(Premiacao.Casa);
                        consorcio.setValorRestante(grupo.getValorTotal());
                        grupo.setContemplacao(consorcio);

                        break;
                    case Apartamento:
                        grupo.setNumeroParcelas(280);
                        grupo.setValorTotal(500000);
                        grupo.setTaxaAdm(90000);
                        consorcio.setPremiacao(Premiacao.Apartamento);
                        consorcio.setValorRestante(grupo.getValorTotal());
                        grupo.setContemplacao(consorcio);
                        break;
                    case Carro:
                        grupo.setNumeroParcelas(72);
                        grupo.setValorTotal(80000);
                        grupo.setTaxaAdm(12000);
                        consorcio.setPremiacao(Premiacao.Carro);
                        consorcio.setValorRestante(grupo.getValorTotal());
                        grupo.setContemplacao(consorcio);
                        break;
                    case Moto:
                        grupo.setNumeroParcelas(180);
                        grupo.setValorTotal(300000);
                        grupo.setTaxaAdm(60000);
                        consorcio.setPremiacao(Premiacao.Moto);
                        consorcio.setValorRestante(grupo.getValorTotal());
                        grupo.setContemplacao(consorcio);
                        break;
                    case Console:
                        grupo.setNumeroParcelas(48);
                        grupo.setValorTotal(25000);
                        grupo.setTaxaAdm(3750);
                        consorcio.setPremiacao(Premiacao.Console);
                        consorcio.setValorRestante(grupo.getValorTotal());
                        grupo.setContemplacao(consorcio);
                        break;
                
                    default:
                        System.out.println("Premiação Inválida.");
                        break;
                }

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



}
