package com.example.consorciojaahvafx.controller;

import com.example.consorciojaahvafx.exception.ErroNoRelatorioException;
import com.example.consorciojaahvafx.model.Relatorio;
import com.example.consorciojaahvafx.repository.IRepository;
import com.example.consorciojaahvafx.repository.RelatorioRepository;
import com.example.consorciojaahvafx.model.*;

public class RelatorioController {
    private IRepository<Relatorio> relatorioRepository;
    private static RelatorioController instance;
    public static RelatorioController getInstance(){
        if (instance == null){
            instance = new RelatorioController();
        }
        return instance;
    }

    public RelatorioController(){
        this.relatorioRepository = RelatorioRepository.getInstance();
    }
    public void salvarRelatorio(Relatorio relatorio) throws RuntimeException{
        if (relatorio != null){
            if (relatorio.getTitulo() != null){
                if (relatorio.getDadoPDF() != null){
                    if (relatorio.getData() != null){
                        relatorioRepository.add(relatorio);
                    } else{
                        throw new ErroNoRelatorioException("Data do relatório não pode ser nula");
                    }
                }else{
                    throw new ErroNoRelatorioException("Documento do relatório não pode ser nulo");
                }
            }else {
                throw new ErroNoRelatorioException("Título do relatório não pode ser nulo");
            }
        } else{
            throw new ErroNoRelatorioException("Relatório não pode ser nulo");
        }
    }
    public void listarRelatorios(){
        System.out.println("---Listando todos os Relatórios---");
        // relatorioRepository.findAll().forEach(System.out::println); -> forma de fazer usando Stream
        for (Relatorio r : relatorioRepository.findAll()) {
            System.out.println(r);
        }
    }
    public void relatoriosDevedores(Consorcio consorcio){

    }
    public void relatorioMensal(){

    }
}
