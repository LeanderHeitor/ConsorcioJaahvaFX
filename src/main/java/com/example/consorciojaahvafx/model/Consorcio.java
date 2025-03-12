package com.example.consorciojaahvafx.model;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.example.consorciojaahvafx.enums.Premiacao;
import lombok.Data;
import lombok.ToString;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

@Data
@ToString(exclude = {"grupos", "contemplados", "parcelasPagas"})
public class Consorcio implements Processo {
    private Long id;
    private static Long numConsorcios = 0L;
    private LocalDate dataInicio;
    private LocalDate dataSorteio;
    private ArrayList<Grupo> grupos;
    private double valorRestante;
    private Premiacao premiacao;
    // armazena quais clientes foram contemplados e quando.
    private HashMap<Cliente, LocalDate> contemplados;
    // armazena quantas parcelas cada cliente de cada grupo pagou até o momento
    private HashMap<Cliente, Integer> parcelasPagas;

    public Consorcio() {
        this.valorRestante = 0.0;
        this.dataSorteio = null;
        this.grupos = new ArrayList<>();
        this.dataInicio = LocalDate.now();
        this.premiacao = null;
        this.contemplados = new HashMap<>();
        this.parcelasPagas = new HashMap<>();
        numConsorcios++;
        this.id = numConsorcios;
    }

    public Cliente sorteio() {
        Cliente sorteado = null;
        return sorteado;
    }

    public void avaliarLance() {}

    @Override
    public Relatorio sendRelatorio() throws IOException {
        Relatorio relatorio = new Relatorio();
        String title = "Relatorio Consorcio- " + String.valueOf(this.getId());
        try {
            PdfWriter writer = new PdfWriter(title + ".pdf");
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document documentExport = new Document(pdfDoc);
            PdfFont titleFont = PdfFontFactory.createFont(StandardFonts.TIMES_BOLDITALIC);
            PdfFont defaultFont = PdfFontFactory.createFont(StandardFonts.TIMES_ROMAN);
            documentExport.add(new Paragraph(title).setFont(titleFont));
            documentExport.add(new Paragraph("data do relatório:" + LocalDate.now().toString()).setFont(titleFont));
            documentExport.add(new Paragraph("ID do Consorcio: " + String.valueOf(this.getId())).setFont(defaultFont));
            documentExport.add(new Paragraph("Premiação: " + String.valueOf(this.getPremiacao())).setFont(defaultFont));
            documentExport.add(new Paragraph("Data de Inicio: " + String.valueOf(this.getDataInicio())).setFont(defaultFont));
            documentExport.add(new Paragraph("Data de Sorteio: " + String.valueOf(this.getDataSorteio())).setFont(defaultFont));
            documentExport.add(new Paragraph("Valor Restante: " + String.valueOf(this.getValorRestante())).setFont(defaultFont));
            documentExport.add(new Paragraph("Contemplados: ").setFont(defaultFont));
            for (Cliente c : contemplados.keySet()) {
                documentExport.add(new Paragraph("Cliente Contemplado: " + c.getNome() + " Data: " + contemplados.get(c)).setFont(defaultFont));
                documentExport.add(new Paragraph("Parcelas Pagas: " + parcelasPagas.get(c)).setFont(defaultFont));
            }

            for (Grupo g : grupos) {
                documentExport.add(new Paragraph("Grupo: " + g.getId()).setFont(defaultFont));
                documentExport.add(new Paragraph("Clientes: ").setFont(defaultFont));
                for (Cliente c : g.getParticipantes()) {
                    documentExport.add(new Paragraph("Cliente: " + c.getNome()).setFont(defaultFont));
                }
            }

            documentExport.close();
            relatorio.setDadoPDF(documentExport);
            return relatorio;

        } catch (Exception e) {
            throw new IOException("arquivo não encontrado ao gerar relatório");
        }
    }
}
