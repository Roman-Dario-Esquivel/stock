package com.stocktienda.stock.service.implementations;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.stocktienda.stock.models.Report;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PdfService {

    @Autowired
    private ReportService reportService;

    public boolean generatePdf(String filePath) {
        try {
            List<Report> products = reportService.informe();

            LocalDate fechaActual = LocalDate.now();
            int dia = fechaActual.getDayOfMonth();
            int mes = fechaActual.getMonthValue();
            int año = fechaActual.getYear();
            String fechaConcatenada = dia + "/" + mes + "/" + año + " ";

            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();

            Paragraph title = new Paragraph("Reporte de Productos " + fechaConcatenada);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            PdfPTable table = new PdfPTable(9); // Número de columnas
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(10f);

            // Encabezados de la tabla
            table.addCell(new PdfPCell(new Paragraph("Codigo")));
            table.addCell(new PdfPCell(new Paragraph("Descripción")));
            table.addCell(new PdfPCell(new Paragraph("Disponible")));
            table.addCell(new PdfPCell(new Paragraph("Stock")));
            table.addCell(new PdfPCell(new Paragraph("cantidad de Dañados")));
            table.addCell(new PdfPCell(new Paragraph("Vendido")));
            table.addCell(new PdfPCell(new Paragraph("Reservado")));
            table.addCell(new PdfPCell(new Paragraph("Precio")));
            table.addCell(new PdfPCell(new Paragraph("Descripcion de dañado")));

            // Datos de la tabla
            for (Report product : products) {
                table.addCell(String.valueOf(product.getIdProduct()));
                table.addCell(product.getDescription());
                table.addCell(String.valueOf(product.getAvailable()));
                table.addCell(String.valueOf(product.getStock()));
                table.addCell(String.valueOf(product.getLow()));
                table.addCell(String.valueOf(product.getSold()));
                table.addCell(String.valueOf(product.getReserve()));
                table.addCell(String.valueOf(product.getPrice()));
                table.addCell(new PdfPCell(new Paragraph(product.getDamaged())));
            }

            document.add(table);
            document.close();
            return true; // Retornar true si el PDF se generó exitosamente
        } catch (DocumentException | IOException e) {
            // Manejar la excepción y retornar false
            System.err.println("Error al generar el PDF: " + e.getMessage());
            return false;
        }
    }

    public void generatePdf(ByteArrayOutputStream out) throws DocumentException, IOException {
        List<Report> products = reportService.informe();

        LocalDate fechaActual = LocalDate.now();

        int dia = fechaActual.getDayOfMonth();
        int mes = fechaActual.getMonthValue();
        int año = fechaActual.getYear();

        String fechaConcatenada = dia + "/" + mes + "/" + año + " ";

        Document document = new Document();
        PdfWriter.getInstance(document, out);
        document.open();

        Paragraph title = new Paragraph("Reporte de Productos" + fechaConcatenada);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);
        // Agregar un espacio después del título
        document.add(new Paragraph("\n"));

        PdfPTable table = new PdfPTable(9); // Número de columnas
        table.setWidthPercentage(100);
        table.setSpacingBefore(10f);
        table.setSpacingAfter(10f);

        // Encabezados de la tabla
        table.addCell(new PdfPCell(new Paragraph("Codigo")));
        table.addCell(new PdfPCell(new Paragraph("Descripción")));
        table.addCell(new PdfPCell(new Paragraph("Disponible")));
        table.addCell(new PdfPCell(new Paragraph("Stock")));
        table.addCell(new PdfPCell(new Paragraph("cantidad de Dañados")));
        table.addCell(new PdfPCell(new Paragraph("Vendido")));
        table.addCell(new PdfPCell(new Paragraph("Reservado")));
        table.addCell(new PdfPCell(new Paragraph("Precio")));
        table.addCell(new PdfPCell(new Paragraph("Descripcion de dañado")));

        // Datos de la tabla
        for (Report product : products) {
            table.addCell(String.valueOf(product.getIdProduct()));
            table.addCell(product.getDescription());
            table.addCell(String.valueOf(product.getAvailable()));
            table.addCell(String.valueOf(product.getStock()));
            table.addCell(String.valueOf(product.getLow()));
            table.addCell(String.valueOf(product.getSold()));
            table.addCell(String.valueOf(product.getReserve()));
            table.addCell(String.valueOf(product.getPrice()));
            table.addCell(new PdfPCell(new Paragraph(product.getDamaged())));
        }

        document.add(table);
        document.close();
    }

}
