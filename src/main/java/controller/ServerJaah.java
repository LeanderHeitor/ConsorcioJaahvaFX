package controller;

import Screen.ScreenManager;
import lombok.Data;

@Data
public class ServerJaah {
    //essa classe é a Facade
    private ServerJaah instance;
    private ScreenManager screenManager;
    private UsuarioController usuarioController;
    private PagamentoController pagamentoController;
    private ConsorcioController consorcioController;
    private GrupoController grupoController;
    private RelatorioController relatorioController;
    private ContratoController contratoController;
    private static ServerJaah serverJaah;

    public static ServerJaah getInstance(){
        if(serverJaah == null){
            serverJaah = new ServerJaah();
        }
        return serverJaah;
    }
}
