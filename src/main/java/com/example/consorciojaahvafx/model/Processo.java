package com.example.consorciojaahvafx.model;

import com.example.consorciojaahvafx.exception.ErroNoRelatorioException;
import com.example.consorciojaahvafx.exception.RelatorioEnviadoException;

import java.io.IOException;

public interface Processo {
    Relatorio sendRelatorio() throws RelatorioEnviadoException, ErroNoRelatorioException, IOException;
}
