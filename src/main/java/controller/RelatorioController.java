package controller;

import exception.ErroNoRelatorioException;
import model.Relatorio;
import repository.IRepository;
import repository.RelatorioRepository;
import model.*;

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
    public void relatoriosDevedores(Consorcio consorcio){

    }
    public void relatorioMensal(){

    }
}
