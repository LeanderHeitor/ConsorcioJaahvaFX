package model;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import enums.StatusCliente;
import enums.TipoServico;
import exception.ErroNoRelatorioException;
import lombok.Data;
import lombok.ToString;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Locale;

@Data
@ToString(exclude = {"documento"})
public class Contrato implements Processo{
    private Long idContrato;
    private static Long numContratos= 0L;
    private Usuario usuarioVinculado;
    private TipoServico tipoServico; // é um enum
    private StatusCliente statusCliente; // é um enum
    private PdfDocument documento;
    private Grupo grupo;
    private boolean finalizado;


    public Contrato() {

    }
    public Contrato( Usuario usuarioVinculado, TipoServico tipoServico) {
        this.usuarioVinculado = usuarioVinculado;
        this.tipoServico = tipoServico;
        if (usuarioVinculado instanceof Cliente){
            this.statusCliente = StatusCliente.PAGANTE;
        }else{
            this.statusCliente = StatusCliente.NOTCLIENTE;
        }
        this.finalizado = false;
        this.idContrato = numContratos;
        numContratos++;
    }

    public void gerarContrato() throws ErroNoRelatorioException {
        try {
            PdfWriter writer = new PdfWriter(new FileOutputStream("Contrato" + getUsuarioVinculado().getNome() + String.valueOf(this.getIdContrato()) + ".pdf"));
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document documentExport = new Document(pdfDoc);
            PdfFont titleFont = PdfFontFactory.createFont(StandardFonts.TIMES_BOLDITALIC);
            PdfFont defaultFont = PdfFontFactory.createFont(StandardFonts.TIMES_ROMAN);

            documentExport.add(new Paragraph("Contrato de " + this.getTipoServico().toString().toLowerCase(Locale.ROOT) ).setFont(titleFont));
            documentExport.add(new Paragraph("Usuario Vinculado: " + String.valueOf(this.getUsuarioVinculado().getNome()) +" função: "+ getUsuarioVinculado().getClass().toString()).setFont(defaultFont));
            documentExport.add(new Paragraph("Status do Usuario:" + String.valueOf(this.getStatusCliente())).setFont(defaultFont));
        }catch (Exception e){
            throw new ErroNoRelatorioException("erro ao gerar contrato");
        }

    }

    @Override
    public Relatorio sendRelatorio() throws RuntimeException{
        String title = "Relatorio Contrato- " + String.valueOf(getUsuarioVinculado().getCPF()) + "-" + getUsuarioVinculado().getNome();
        Relatorio relatorio = new Relatorio("ContRel-" + String.valueOf(this.getIdContrato()));
        try {
            PdfWriter writer = new PdfWriter(new FileOutputStream("relatorioContrato" + String.valueOf(this.getIdContrato()) + ".pdf"));
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document documentExport = new Document(pdfDoc);

            PdfFont titleFont = PdfFontFactory.createFont(StandardFonts.TIMES_BOLDITALIC);
            PdfFont defaultFont = PdfFontFactory.createFont(StandardFonts.TIMES_ROMAN);

            documentExport.add(new Paragraph(title).setFont(titleFont));
            documentExport.add(new Paragraph("data do relatório:" + LocalDate.now().toString()).setFont(titleFont));

            documentExport.add(new Paragraph("Usuario Vinculado: " + String.valueOf(this.getUsuarioVinculado().getNome())).setFont(defaultFont));
            documentExport.add(new Paragraph("Tipo de Serviço: " + String.valueOf(this.getTipoServico())).setFont(defaultFont));
            documentExport.add(new Paragraph("Status do Cliente: " + String.valueOf(this.getStatusCliente())).setFont(defaultFont));
            documentExport.add(new Paragraph("Grupo: " + String.valueOf(this.getGrupo().getId())).setFont(defaultFont));
            documentExport.add(new Paragraph("ID do Contrato: " + String.valueOf(this.getIdContrato())).setFont(defaultFont));

            documentExport.close();
            relatorio.setDadoPDF(documentExport);
            return relatorio;

        } catch (IOException e) {
            throw new ErroNoRelatorioException("arquivo não encontrado ao gerar relatório");
        }
    }

}