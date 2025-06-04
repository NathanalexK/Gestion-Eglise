package org.example.fiangonana.dto.tresorerie;

import lombok.Getter;
import lombok.Setter;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.fiangonana.util.DateUtils;
import org.example.fiangonana.util.NombreUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MvtCaisseAffichage {
    private LocalDate dateMin;
    private LocalDate dateMax;
    private List<MvtCaisseLigne> mvtCaisses = new ArrayList<>();
    private Double entree = null;
    private Double sortie = null;
    private Double soldePrecedent = null;

    public MvtCaisseAffichage() {

    }

    public Double getEntree() {
        if(this.entree != null) return this.entree;
        double total = 0.00;

        for(MvtCaisseLigne ligne: this.mvtCaisses) {
            if(ligne.isCompteSolde()) continue;
            total += ligne.getEntree();
        }

        setEntree(total);
        return this.entree;
    }

    public Double getSortie() {
        if(this.sortie != null) return this.sortie;
        double total = 0.00;

        for(MvtCaisseLigne ligne: this.mvtCaisses) {
            if(ligne.isCompteSolde()) continue;
            total += ligne.getSortie();
        }
        setSortie(total);
        return this.sortie;
    }

    public Double getSoldePrecedent() {
        if(this.soldePrecedent != null) return this.soldePrecedent;

        for(MvtCaisseLigne ligne: this.mvtCaisses) {
            if(ligne.isCompteSolde()) {
                setSoldePrecedent(ligne.getSoldes());
                return this.soldePrecedent;
            }
        }
        setSoldePrecedent(0.00);
        return this.soldePrecedent;

    }

    public Double getSolde() {
//        if(this.s)
        return this.getSoldePrecedent() + this.getEntree() - this.getSortie();
    }

    public Double getTotal() {
        return this.getEntree() - this.getSortie();
    }

    public byte[] genererExcel() throws IOException {
        try(Workbook workbook = new XSSFWorkbook()) {
            Sheet feuille = workbook.createSheet("Toe-bola");
            feuille.setColumnWidth(0, 3000);
            feuille.setColumnWidth(1, 2500);
            feuille.setColumnWidth(2, 12000);
            feuille.setColumnWidth(3, 4000);
            feuille.setColumnWidth(4, 4000);
            feuille.setColumnWidth(5, 5000);
            feuille.setColumnWidth(6, 5000);

            byte[] rgb = new byte[] {(byte) 247, (byte)177, (byte)118};
            Color bgColor = new XSSFColor(rgb, null);

            Font bold = workbook.createFont();
            bold.setBold(true);
            CellStyle boldStyle = workbook.createCellStyle();
            boldStyle.setFont(bold);

            CellStyle centerBoldStyle = workbook.createCellStyle();
            centerBoldStyle.setFont(bold);
            centerBoldStyle.setAlignment(HorizontalAlignment.CENTER);

            CellStyle cs1 = workbook.createCellStyle();
            cs1.setFont(bold);
            cs1.setFillForegroundColor(bgColor);
            cs1.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            CellStyle cs2 = workbook.createCellStyle();
            cs2.setFont(bold);
            cs2.setFillForegroundColor(bgColor);
            cs2.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            cs2.setAlignment(HorizontalAlignment.RIGHT);

            CellStyle enTeteStyle = workbook.createCellStyle();
            enTeteStyle.setFont(bold);
            enTeteStyle.setFillForegroundColor(bgColor);
            enTeteStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            enTeteStyle.setAlignment(HorizontalAlignment.CENTER);
            enTeteStyle.setBorderBottom(BorderStyle.THIN);

            CellStyle footerStyle = workbook.createCellStyle();
            footerStyle.setFont(bold);
            footerStyle.setFillForegroundColor(bgColor);
            footerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            footerStyle.setAlignment(HorizontalAlignment.CENTER);
            footerStyle.setBorderTop(BorderStyle.THIN);

            CellStyle alignCenterStyle = workbook.createCellStyle();
            alignCenterStyle.setAlignment(HorizontalAlignment.CENTER);

            CellStyle alignRightStyle = workbook.createCellStyle();
            alignRightStyle.setAlignment(HorizontalAlignment.RIGHT);

            // LIGNE 1
            Row ligne1 = feuille.createRow(0);
            Cell c11 = ligne1.createCell(0);
            c11.setCellStyle(boldStyle);
            c11.setCellValue("DISTRIKA");
            ligne1.createCell(1).setCellValue(": ITAOSY");

            // LIGNE 2
            Row ligne2 = feuille.createRow(1);
            Cell c21 = ligne2.createCell(0);
            c21.setCellStyle(boldStyle);
            c21.setCellValue("Paroasy");
            ligne2.createCell(1).setCellValue(": MASINA MISELY ITAOSY");

            // LIGNE 3
            Row ligne3 = feuille.createRow(2);
            Cell c31 = ligne3.createCell(0);
            c31.setCellStyle(centerBoldStyle);
            c31.setCellValue("TOE-BOLAN'NY FIANGONANA " + DateUtils.affichageIntervalleDateMalgache(dateMin, dateMax).toUpperCase());
            feuille.addMergedRegion(new CellRangeAddress(2, 2, 0, 2));


            Row entete1 = feuille.createRow(4);
            Cell c51 = entete1.createCell(0);
            c51.setCellStyle(enTeteStyle);
            c51.setCellValue("Date");
            Cell c52 = entete1.createCell(1);
            c52.setCellStyle(enTeteStyle);
            c52.setCellValue("Compte");
            Cell c53 = entete1.createCell(2);
            c53.setCellStyle(enTeteStyle);
            c53.setCellValue("Libellé");
            Cell c54 = entete1.createCell(3);
            c54.setCellStyle(enTeteStyle);
            c54.setCellValue("Entree");
            Cell c55 = entete1.createCell(4);
            c55.setCellStyle(enTeteStyle);
            c55.setCellValue("Sortie");
            Cell c56 = entete1.createCell(5);
            c56.setCellStyle(enTeteStyle);
            c56.setCellValue("Solde");
            Cell c57 = entete1.createCell(6);
            c57.setCellStyle(enTeteStyle);
            c57.setCellValue("Observation");

            int numLigne = 5;
            for(MvtCaisseLigne mvt: this.getMvtCaisses()){
                Row ligne = feuille.createRow(numLigne);
                Cell c0 = ligne.createCell(0);
                c0.setCellValue(DateUtils.getFormatParDefaut(mvt.getDate()));
                Cell c1 = ligne.createCell(1);
                c1.setCellStyle(alignCenterStyle);
                c1.setCellValue(mvt.getCode());
                Cell c2 = ligne.createCell(2);
                c2.setCellValue(mvt.getLibelle());
                Cell c3 = ligne.createCell(3);
                c3.setCellStyle(alignRightStyle);
                c3.setCellValue(!NombreUtils.comparerDouble(mvt.getEntree(), 0.00) ? NombreUtils.affichageMonetaire(mvt.getEntree()): "");
                Cell c4 = ligne.createCell(4);
                c4.setCellStyle(alignRightStyle);
                c4.setCellValue(!NombreUtils.comparerDouble(mvt.getSortie(), 0.00) ? NombreUtils.affichageMonetaire(mvt.getSortie()): "");
                Cell c5 = ligne.createCell(5);
                c5.setCellStyle(alignRightStyle);
                c5.setCellValue(NombreUtils.affichageMonetaire(mvt.getSoldes()));
                Cell c6 = ligne.createCell(6);
                numLigne++;
//                c6.setCellValue(mvt.get);
            }
            Row footer = feuille.createRow(numLigne);
            Cell cTotalLib = footer.createCell(0);
            cTotalLib.setCellStyle(alignCenterStyle);
            cTotalLib.setCellValue("TOTAL");
            Cell cEntree = footer.createCell(3);
            cEntree.setCellStyle(alignRightStyle);
            cEntree.setCellValue(NombreUtils.affichageMonetaire(this.getEntree()));
            Cell cSortie = footer.createCell(4);
            cSortie.setCellStyle(alignRightStyle);
            cSortie.setCellValue(NombreUtils.affichageMonetaire(this.getSortie()));
            Cell cSolde = footer.createCell(5);
            cSolde.setCellStyle(alignRightStyle);
            cSolde.setCellValue(NombreUtils.affichageMonetaire(this.getSolde()));
            feuille.addMergedRegion(new CellRangeAddress(numLigne, numLigne, 0, 2));

            try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
                workbook.write(out);
                return out.toByteArray();
            }

        }

    }


    //    public MvtCaisseAffichageDTO(){
//    }
//
//    public MvtCaisseAffichageDTO() {
//
//    }
//
//    public Double getEntree() {
//        if(entree != null) return entree;
//
//
//    }
}
