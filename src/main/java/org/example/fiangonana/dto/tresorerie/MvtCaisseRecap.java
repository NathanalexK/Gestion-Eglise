package org.example.fiangonana.dto.tresorerie;

import lombok.Getter;
import lombok.Setter;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.aspectj.weaver.ast.Call;
import org.example.fiangonana.util.Constante;
import org.example.fiangonana.util.DateUtils;
import org.example.fiangonana.util.NombreUtils;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MvtCaisseRecap {
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private List<MvtCaisseRecapLigne> recapEntrees = new ArrayList<>();
    private List<MvtCaisseRecapLigne> recapSortie = new ArrayList<>();
    private Double totalEntrees;
    private Double totalSortie;
    private Double soldeRestant;
    private Double soldePrecedent;

    public MvtCaisseRecap() {

    }

    public MvtCaisseRecap(List<MvtCaisseRecapLigne> lignes) {
        double entrees = 0.00;
        double sorties = 0.00;
        for(MvtCaisseRecapLigne ligne: lignes) {
            if(ligne.getType() == Constante.mouvement.ENTREE) {
                entrees += ligne.getTotal();
                recapEntrees.add(ligne);

            } else if(ligne.getType() == Constante.mouvement.SORTIE) {
                sorties += ligne.getTotal();
                recapSortie.add(ligne);
            }
        }
        setTotalEntrees(entrees);
        setTotalSortie(sorties);
    }

    public Double getSoldeRestant() {
        return this.getSoldePrecedent() + this.getTotalEntrees() - this.getTotalSortie();
    }

    public byte[] genererExcel() throws IOException {
        try(Workbook workbook = new XSSFWorkbook()) {
            Sheet feuille  = workbook.createSheet("Rapport");
            feuille.setColumnWidth(0, 4000);
            feuille.setColumnWidth(1, 22000);
            feuille.setColumnWidth(2, 6000);

            byte[] rgb = new byte[] {(byte) 220, (byte)239, (byte)250};
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
            c31.setCellValue("TOE-BOLAN'NY FIANGONANA " + DateUtils.affichageIntervalleDateMalgache(dateDebut, dateFin).toUpperCase());
            feuille.addMergedRegion(new CellRangeAddress(2, 2, 0, 2));

            // LIGNE 4
            Row ligne4 = feuille.createRow(4);
            Cell c41 = ligne4.createCell(0);
            c41.setCellStyle(cs1);
            c41.setCellValue("AMBIM-BOLA FARANY");
            Cell c43 = ligne4.createCell(2);
            c43.setCellStyle(cs2);
            c43.setCellValue(NombreUtils.affichageMonetaire(this.getSoldePrecedent()));
            feuille.addMergedRegion(new CellRangeAddress(4, 4, 0, 1));

            // en-tete table1
            Row entete1 = feuille.createRow(6);
            Cell c61 = entete1.createCell(0);
            c61.setCellStyle(enTeteStyle);
            c61.setCellValue("NOMERAO");
            Cell c62 = entete1.createCell(1);
            c62.setCellStyle(enTeteStyle);
            c62.setCellValue("VOLA NIDITRA");
            Cell c63 = entete1.createCell(2);
            c63.setCellStyle(enTeteStyle);
            c63.setCellValue("TOTALINY");

            int numLigne = 7;
            int num = 1;

            for(MvtCaisseRecapLigne recapLigne: this.getRecapEntrees()) {
                Row ligne = feuille.createRow(numLigne);
                Cell c0 = ligne.createCell(0);
                c0.setCellValue(num + "-");
                c0.setCellStyle(alignCenterStyle);
                Cell c1 = ligne.createCell(1);
                c1.setCellValue(recapLigne.getLibelle());
                Cell c2 = ligne.createCell(2);
                c2.setCellValue(NombreUtils.affichageMonetaire(recapLigne.getTotal()));
                c2.setCellStyle(alignRightStyle);

                numLigne++;
                num++;
            }

            Row footer1 = feuille.createRow(numLigne);
            Cell cf11 = footer1.createCell(0);
            cf11.setCellStyle(cs1);
            cf11.setCellValue("TOTALIN'NY VOLA NIDITRA");
            Cell cf13 = footer1.createCell(2);
            cf13.setCellStyle(cs2);
            cf13.setCellValue(NombreUtils.affichageMonetaire(this.getTotalEntrees()));

            feuille.addMergedRegion(new CellRangeAddress(numLigne, numLigne, 0, 1));
            numLigne++;
            feuille.addMergedRegion(new CellRangeAddress(numLigne, numLigne, 0, 2));
            num = 1;
            numLigne+=2;

            // en-tete table2
            Row entete2 = feuille.createRow(numLigne);
            Cell ce21 = entete2.createCell(0);
            ce21.setCellStyle(enTeteStyle);
            ce21.setCellValue("NOMERAO");
            Cell ce22 = entete2.createCell(1);
            ce22.setCellStyle(enTeteStyle);
            ce22.setCellValue("VOLA NIVOAKA");
            Cell ce23 = entete2.createCell(2);
            ce23.setCellStyle(enTeteStyle);
            ce23.setCellValue("TOTALINY");
            numLigne++;

            for(MvtCaisseRecapLigne recapLigne: this.getRecapSortie()) {
                Row ligne = feuille.createRow(numLigne);
                Cell c0 = ligne.createCell(0);
                c0.setCellValue(num + "-");
                c0.setCellStyle(alignCenterStyle);
                Cell c1 = ligne.createCell(1);
                c1.setCellValue(recapLigne.getLibelle());
                Cell c2 = ligne.createCell(2);
                c2.setCellValue(NombreUtils.affichageMonetaire(recapLigne.getTotal()));
                c2.setCellStyle(alignRightStyle);

                numLigne++;
                num++;
            }

            Row footer2 = feuille.createRow(numLigne);
            Cell cf21 = footer2.createCell(0);
            cf21.setCellStyle(cs1);
            cf21.setCellValue("TOTALIN'NY FANDANIANA");
            Cell cf23 = footer2.createCell(2);
            cf23.setCellStyle(cs2);
            cf23.setCellValue(NombreUtils.affichageMonetaire(this.getTotalSortie()));

            feuille.addMergedRegion(new CellRangeAddress(numLigne, numLigne, 0, 1));
            numLigne++;
            feuille.addMergedRegion(new CellRangeAddress(numLigne, numLigne, 0, 2));
            numLigne += 2;

            Row dernierLigne = feuille.createRow(numLigne);
            Cell cd11 = dernierLigne.createCell(0);
            cd11.setCellStyle(cs1);
            cd11.setCellValue("AMPELATANANA @ FARAN'NY KAONTY " + DateUtils.getDateMalgache(this.getDateFin()));
            Cell cd13 = dernierLigne.createCell(2);
            cd13.setCellStyle(cs2);
            cd13.setCellValue(NombreUtils.affichageMonetaire(this.getSoldeRestant()));
            feuille.addMergedRegion(new CellRangeAddress(numLigne, numLigne, 0, 1));
            numLigne++;
            feuille.addMergedRegion(new CellRangeAddress(numLigne, numLigne, 0, 2));


            // Image
//            InputStream inputStream = new FileInputStream("src/main/resources/static/assets/img/logo.png");
//
//            byte[] imageBytes = IOUtils.toByteArray(inputStream);
//            inputStream.close();
//
//            int pictureIdx = workbook.addPicture(imageBytes, Workbook.PICTURE_TYPE_PNG);
//
//            // Create a drawing patriarch to hold the image
//            CreationHelper helper = workbook.getCreationHelper();
//            Drawing<?> drawing = feuille.createDrawingPatriarch();
//
//            // Create an anchor (internally used by resize)
//            ClientAnchor anchor = helper.createClientAnchor();
//
//            // Add the image to the sheet
//            Picture picture = drawing.createPicture(anchor, pictureIdx);
//
//            // Position the image relative to a cell (e.g., column 1, row 1)
//            anchor.setCol1(3); // Start column
//            anchor.setRow1(1); // Start row
//            anchor.setCol2(3);
//            anchor.setRow2(3);
//
//            // Resize the image to fit within a specific number of columns and rows
////            picture.resize(2, 3);




            try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
                workbook.write(out);
                return out.toByteArray();
            }

        }
    }
}
