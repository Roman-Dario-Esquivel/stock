package com.stocktienda.stock.controllers;

import com.itextpdf.text.DocumentException;
import com.stocktienda.stock.service.implementations.PdfService;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/pdf")
public class PdfController {

    @Autowired
    private PdfService pdfService;

    @GetMapping("/generate")
    public ResponseEntity<?> generatePdf() throws IOException {

        String currentDirectory = System.getProperty("user.dir");

        // Crear la ruta para la carpeta "informes" dentro del directorio actual
        String folderPath = currentDirectory + File.separator + "informes";
        Path folder = Paths.get(folderPath);

        // Crear la carpeta "informes" si no existe
        if (!Files.exists(folder)) {
            Files.createDirectories(folder);  // Crea los directorios si no existen
        }

        LocalDate fechaActual = LocalDate.now();
        int dia = fechaActual.getDayOfMonth();
        int mes = fechaActual.getMonthValue();
        int año = fechaActual.getYear();
        String fechaConcatenada = dia + "-" + mes + "-" + año;

        // Crear el nombre del archivo PDF con la fecha
        String filePath = folderPath + File.separator + "informe_" + fechaConcatenada + ".pdf";
        boolean isGenerated = pdfService.generatePdf(filePath);
        if (isGenerated) {
            String repuesta = "PDF generado y guardado en: " + filePath;
            return new ResponseEntity<>(repuesta, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al generar el PDF.");
        }

    }

    @GetMapping("/download")
    public ResponseEntity<InputStreamResource> downloadPdf() throws DocumentException, IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        pdfService.generatePdf(out);

        LocalDate fechaActual = LocalDate.now();
        int dia = fechaActual.getDayOfMonth();
        int mes = fechaActual.getMonthValue();
        int año = fechaActual.getYear();
        String fechaConcatenada = dia + "/" + mes + "/" + año;

        String fileName = "informe_" + fechaConcatenada + ".pdf";

        ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=" + fileName);

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(in));
    }

}
