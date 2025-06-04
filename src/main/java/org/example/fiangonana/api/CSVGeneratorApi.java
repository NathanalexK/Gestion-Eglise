package org.example.fiangonana.api;

import org.example.fiangonana.dto.tresorerie.MvtCaisseAffichage;
import org.example.fiangonana.dto.tresorerie.MvtCaisseRecap;
import org.example.fiangonana.dto.tresorerie.RapportTresorerieCSV;
import org.example.fiangonana.service.MvtCaisseService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/csv")
@CrossOrigin("*")
public class CSVGeneratorApi {


    private final MvtCaisseService mvtCaisseService;

    public CSVGeneratorApi(MvtCaisseService mvtCaisseService) {
        this.mvtCaisseService = mvtCaisseService;
    }

    @GetMapping("/rapport")
    public ResponseEntity<byte[]> downloadExcel(
            @RequestParam(value = "dateMin", required = true) LocalDate dateMin,
            @RequestParam(value = "dateMax", required = true) LocalDate dateMax
    ) {
        try {
            MvtCaisseRecap recap = mvtCaisseService.getRecapAffichage(dateMin, dateMax);
            byte[] excelBytes = recap.genererExcel();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", "report.xlsx");

            return new ResponseEntity<>(excelBytes, headers, HttpStatus.OK);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/mvt-caisse")
    public ResponseEntity<byte[]> downloadExcelMvtCaisse(
            @RequestParam(value = "dateMin", required = true) LocalDate dateMin,
            @RequestParam(value = "dateMax", required = true) LocalDate dateMax
    ) {
        try {
            MvtCaisseAffichage affichage = new MvtCaisseAffichage();
            affichage.setMvtCaisses(mvtCaisseService.getMvtCaissesEntre2Dates(dateMin, dateMax));
            affichage.setDateMin(dateMin);
            affichage.setDateMax(dateMax);

            byte[] excelBytes = affichage.genererExcel();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", "toebola.xlsx");

            return new ResponseEntity<>(excelBytes, headers, HttpStatus.OK);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }}
