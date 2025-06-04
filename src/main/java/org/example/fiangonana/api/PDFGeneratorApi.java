package org.example.fiangonana.api;

import com.itextpdf.text.DocumentException;
import jakarta.annotation.Nullable;
import org.example.fiangonana.dto.budget.BudgetPDF;
import org.example.fiangonana.dto.tresorerie.MvtCaissePDF;
import org.example.fiangonana.dto.util.DateIntervalleDTO;
import org.example.fiangonana.exception.ExceptionList;
import org.example.fiangonana.model.MvtCaisse;
import org.example.fiangonana.model.immutable.VBudgetCpl;
import org.example.fiangonana.service.BudgetService;
import org.example.fiangonana.service.MvtCaisseService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/pdf")
@CrossOrigin("*")
public class PDFGeneratorApi {

    private final MvtCaisseService mvtCaisseService;
    private final BudgetService budgetService;

    public PDFGeneratorApi(MvtCaisseService mvtCaisseService, BudgetService budgetService) {
        this.mvtCaisseService = mvtCaisseService;
        this.budgetService = budgetService;
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

    @GetMapping("/budget/details")
    public ResponseEntity<byte[]> getPDFDetailsBudget(@RequestParam("id") Integer id) throws DocumentException, ExceptionList {
        VBudgetCpl budget = budgetService.getBudgetCompletById(id);
        if(budget == null) throw new ExceptionList("Aucun budget trouvé ayant ID: " + id);
        List<MvtCaisse> mvtCaisses = mvtCaisseService.getAllByIdBudget(id);

        BudgetPDF pdf = new BudgetPDF();
        pdf.setBudget(budget);
        pdf.setMvtCaisses(mvtCaisses);
        byte[] pdfBytes = pdf.genererPDF();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "budget.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .body(pdfBytes);

//        modelAndView.addObject("budget",budget );
//        modelAndView.addObject("mc[]", mvtCaisseService.getAllByIdBudget(idBudget));
    }
}
