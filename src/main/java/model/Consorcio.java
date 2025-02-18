package model;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;

import enums.Premiacao;
import lombok.Data;
import lombok.ToString;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

@Data
@ToString(exclude = {"grupos", "contemplados", "parcelasPagas"})
public class Consorcio /*implements Processo*/{
    private Long id;
    private static Long numConsorcios= 0L;
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
        this.valorRestante = valorRestante;
        this.dataSorteio = dataSorteio;
        this.grupos = new ArrayList<>();
        this.dataInicio= LocalDate.now();
        this.premiacao = premiacao;
        numConsorcios++;
        this.id=numConsorcios;
    }


    public Cliente sorteio(){
       Cliente sorteado= null;


        return sorteado;
    }

    public void avaliarLance(){}

    // @Override
    // public Document sendRelatorio() throws IOException {
    //     String title= "Relatorio Consorcio- " + String.valueOf(this.getIdConsorcio());
    //     try {
    //         PdfWriter writer = new PdfWriter(title + ".pdf");
    //         PdfDocument pdfDoc = new PdfDocument(writer);
    //         Document documentExport = new Document(pdfDoc);
    //         documentExport.close();
    //         return documentExport;

    //     }catch(Exception e){
    //         throw new IOException("arquivo não encontrado ao gerar relatório");
    //     }

    // }
}
