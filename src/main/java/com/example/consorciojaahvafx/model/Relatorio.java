package com.example.consorciojaahvafx.model;

import com.itextpdf.layout.Document;
import lombok.Data;

import java.time.LocalDate;

@Data
public class Relatorio {
    private String titulo;
    private LocalDate data;
    private Document dadoPDF;
    private static Long numeroRelatorios= 0L;
    private Long codigo= 0L;


    public Relatorio(){
        this.data= LocalDate.now();
        codigo= numeroRelatorios;
        numeroRelatorios++;
    }
    public Relatorio(String titulo) {
        this.titulo = titulo;
        this.data= LocalDate.now();
        this.dadoPDF= null;
        codigo= numeroRelatorios;
        numeroRelatorios++;
    }
}
