package org.example.fiangonana.api;

import com.itextpdf.text.DocumentException;
import jakarta.annotation.Nullable;
import org.example.fiangonana.dto.tresorerie.MvtCaissePDF;
import org.example.fiangonana.dto.util.DateIntervalleDTO;
import org.example.fiangonana.service.MvtCaisseService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/pdf")
@CrossOrigin("*")
public class PDFGeneratorApi {

    private final MvtCaisseService mvtCaisseService;

    public PDFGeneratorApi(MvtCaisseService mvtCaisseService) {
        this.mvtCaisseService = mvtCaisseService;
    }

    @GetMapping("/tresorerie/date")
    public ResponseEntity<byte[]> getPDFTresorerieEntre2Dates(
            @RequestParam(value = "dateMin", required = false) LocalDate dateMin,
            @RequestParam(value = "dateMax", required = false) LocalDate dateMax

    ) throws DocumentException, IOException {
//        System.out.println(dateIntervalle);
        DateIntervalleDTO dateIntervalle = new DateIntervalleDTO(dateMin, dateMax);

        MvtCaissePDF mvtCaissePDF = new MvtCaissePDF();
//        if(dateIntervalle == null) dateIntervalle = new DateIntervalleDTO();
        mvtCaissePDF.setDateDebut(dateIntervalle.getDateMin());
        mvtCaissePDF.setDateFin(dateIntervalle.getDateMax());
        mvtCaissePDF.setLignes(mvtCaisseService.getMvtCaissesEntre2Dates(dateIntervalle.getDateMin(), dateIntervalle.getDateMax()));
        byte[] pdfBytes = mvtCaissePDF.genererPDF();


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "document.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .body(pdfBytes);
    }
}
