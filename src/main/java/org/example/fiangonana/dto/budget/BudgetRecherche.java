package org.example.fiangonana.dto.budget;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.Getter;
import lombok.Setter;
import org.example.fiangonana.dto.tresorerie.MvtCaisseLigne;
import org.example.fiangonana.model.immutable.VBudgetCpl;
import org.example.fiangonana.util.DateUtils;
import org.example.fiangonana.util.NombreUtils;

import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class BudgetRecherche {
    private String libelle;
    private Integer isArret;
    private LocalDate dateMin;
    private LocalDate dateMax;

    private List<VBudgetCpl> budgets = new ArrayList<>();




}
