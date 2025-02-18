package model;

import exception.ErroNoRelatorioException;
import exception.RelatorioEnviadoException;

import java.io.IOException;

public interface Processo {
    Relatorio sendRelatorio() throws RelatorioEnviadoException, ErroNoRelatorioException, IOException;
}
