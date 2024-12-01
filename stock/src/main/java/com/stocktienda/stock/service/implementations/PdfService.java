package com.stocktienda.stock.service.implementations;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.stocktienda.stock.models.Report;
import com.stocktienda.stock.models.Reservation;
import com.stocktienda.stock.service.interfaces.IReservationService;
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
    @Autowired
    private IReservationService reservationService;

    public boolean generatePdf(String filePath) {
        try {
            List<Report> products = reportService.informe();
            List<Reservation> reservations = reservationService.AllReservationActive();

            LocalDate fechaActual = LocalDate.now();
            int dia = fechaActual.getDayOfMonth();
            int mes = fechaActual.getMonthValue();
            int año = fechaActual.getYear();
            String fechaConcatenada = dia + "/" + mes + "/" + año + " ";

            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();

            Font font = new Font(Font.FontFamily.HELVETICA, 20, Font.BOLD | Font.UNDERLINE, BaseColor.BLACK);
            Paragraph title = new Paragraph("Reporte de Productosy reservas hasta " + fechaConcatenada, font);
            title.setAlignment(Element.ALIGN_CENTER);
            title.setSpacingBefore(20f);
            title.setSpacingAfter(10f);
            document.add(title);

            Font fontsub = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD, BaseColor.BLACK);
            Paragraph subtitle1 = new Paragraph("Reporte de Productos", fontsub);
            subtitle1.setAlignment(Element.ALIGN_CENTER);
            subtitle1.setSpacingBefore(20f);
            subtitle1.setSpacingAfter(10f);
            document.add(subtitle1);

            PdfPTable table = new PdfPTable(10); // Número de columnas
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(10f);

            table.addCell(new PdfPCell(new Paragraph("Codigo")));
            table.addCell(new PdfPCell(new Paragraph("Descripción")));
            table.addCell(new PdfPCell(new Paragraph("Disponible")));
            table.addCell(new PdfPCell(new Paragraph("Stock")));
            table.addCell(new PdfPCell(new Paragraph("cantidad de Dañados")));
            table.addCell(new PdfPCell(new Paragraph("Vendido")));
            table.addCell(new PdfPCell(new Paragraph("Reservado")));
            table.addCell(new PdfPCell(new Paragraph("Precio efectivo")));
            table.addCell(new PdfPCell(new Paragraph("Precio de tarjeta")));
            table.addCell(new PdfPCell(new Paragraph("Descripcion de dañado")));

            for (Report product : products) {
                table.addCell(String.valueOf(product.getIdProduct()));
                table.addCell(product.getDescription());
                table.addCell(String.valueOf(product.getAvailable()));
                table.addCell(String.valueOf(product.getStock()));
                table.addCell(String.valueOf(product.getLow()));
                table.addCell(String.valueOf(product.getSold()));
                table.addCell(String.valueOf(product.getReserve()));
                table.addCell(String.valueOf(product.getPrice()));
                table.addCell(String.valueOf(product.getCard()));
                table.addCell(new PdfPCell(new Paragraph(product.getDamaged())));
            }

            document.add(table);

            Paragraph subtitle2 = new Paragraph("Reporte de Reservas", fontsub);
            subtitle2.setAlignment(Element.ALIGN_CENTER);
            subtitle2.setSpacingBefore(20f);
            subtitle2.setSpacingAfter(10f);
            document.add(subtitle2);

            PdfPTable table2 = new PdfPTable(8); // Número de columnas
            table2.setWidthPercentage(100);
            table2.setSpacingBefore(10f);
            table2.setSpacingAfter(10f);

            // Encabezados de la tabla
            table2.addCell(new PdfPCell(new Paragraph("Producto")));
            table2.addCell(new PdfPCell(new Paragraph("Monto de reserva")));
            table2.addCell(new PdfPCell(new Paragraph("Depositado")));
            table2.addCell(new PdfPCell(new Paragraph("Saldo")));
            table2.addCell(new PdfPCell(new Paragraph("cantidad de productos")));
            table2.addCell(new PdfPCell(new Paragraph("Fecha de creaccion de reserva")));
            table2.addCell(new PdfPCell(new Paragraph("Nombre de cliente")));
            table2.addCell(new PdfPCell(new Paragraph("numero de ")));

            // Datos de la tabla
            for (Reservation reserva : reservations) {
                table2.addCell(reserva.getProduct().getDescription());
                table2.addCell(String.valueOf(reserva.getPrice()));
                table2.addCell(String.valueOf(reserva.getDeposit()));
                table2.addCell(String.valueOf(reserva.getBalance()));
                table2.addCell(String.valueOf(reserva.getQuantity()));
                table2.addCell(String.valueOf(reserva.getFecha()));
                table2.addCell(String.valueOf(reserva.getCustomer().getName()));
                table2.addCell(String.valueOf(reserva.getCustomer().getNumberMobile()));
            }
            document.add(table2);

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
