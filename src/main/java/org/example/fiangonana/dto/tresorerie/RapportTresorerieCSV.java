package org.example.fiangonana.dto.tresorerie;

import lombok.Getter;
import lombok.Setter;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.fiangonana.util.DateUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;

@Getter
@Setter
public class RapportTresorerieCSV {
    private LocalDate dateMin;
    private LocalDate dateMax;
    private MvtCaisseRecap recap;

    public byte[] genererExcel() throws IOException {
        try(Workbook workbook = new XSSFWorkbook()) {
            Sheet feuille  = workbook.createSheet("Rapport");
            feuille.setColumnWidth(0, 4000);
            feuille.setColumnWidth(1, 24000);
            feuille.setColumnWidth(2, 6000);

            Font bold = workbook.createFont();
            bold.setBold(true);
            CellStyle boldStyle = workbook.createCellStyle();
            boldStyle.setFont(bold);

            CellStyle centerBoldStyle = workbook.createCellStyle();
            centerBoldStyle.setFont(bold);
            centerBoldStyle.setAlignment(HorizontalAlignment.CENTER);

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
            c31.setCellValue("TOE-BOLAN'NY FIANGONANA NY " + DateUtils.affichageIntervalleDateMalgache(dateMin, dateMax));

            feuille.addMergedRegion(new CellRangeAddress(2, 2, 0, 2));

            try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
                workbook.write(out);
                return out.toByteArray();
            }

        }
    }

    public byte[] genererCSV() throws IOException {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Report");

            // Set Column Widths
            sheet.setColumnWidth(0, 4000);
            sheet.setColumnWidth(1, 25000);
            sheet.setColumnWidth(2, 6000);

            // Create Header Row
            Row headerRow = sheet.createRow(0);
            Cell headerCell1 = headerRow.createCell(0);
            headerCell1.setCellValue("Category");

            Cell headerCell2 = headerRow.createCell(1);
            headerCell2.setCellValue("Description");

            // Apply Bold Styling
            CellStyle headerStyle = workbook.createCellStyle();
            Font font = workbook.createFont();
            font.setBold(true);
            headerStyle.setFont(font);
            headerCell1.setCellStyle(headerStyle);
            headerCell2.setCellStyle(headerStyle);

            // Create Data Rows with Rowspan
            Row dataRow1 = sheet.createRow(1);
            dataRow1.createCell(0).setCellValue("Electronics");
            dataRow1.createCell(1).setCellValue("Laptop with Intel Core i7");

            Row dataRow2 = sheet.createRow(2);
            dataRow2.createCell(1).setCellValue("Smartphone with 128GB storage");

            // Merge Rowspan (Category Column)
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 0, 0));

            // Insert Image in Row 3
//            insertImage(workbook, sheet, "/static/images/sample.jpg", 3, 0);

            // Convert Workbook to Byte Array
            try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
                workbook.write(out);
                return out.toByteArray();
            }
        }
    }

}
