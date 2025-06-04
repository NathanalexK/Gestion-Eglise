package org.example.fiangonana.dto.budget;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.Getter;
import lombok.Setter;
import org.example.fiangonana.dto.tresorerie.MvtCaisseLigne;
import org.example.fiangonana.model.MvtCaisse;
import org.example.fiangonana.model.immutable.VBudgetCpl;
import org.example.fiangonana.util.DateUtils;
import org.example.fiangonana.util.NombreUtils;

import java.io.ByteArrayOutputStream;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class BudgetPDF {
    private VBudgetCpl budget;
    private List<MvtCaisse> mvtCaisses = new ArrayList<>();


    public byte[] genererPDF() throws DocumentException {
        Document document = new Document(PageSize.A4, 20,20,20,20);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, baos);

        document.open();
        Font headerFont = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD);
        Font titleFont = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD);


//        PdfPTable headerTable = new PdfPTable(2);
//        headerTable.setWidthPercentage(100);
//        headerTable.setWidths(new int[]{80, 20});

//        headerTable.a

        Paragraph p1 = new Paragraph("EKAR Masina Misely Itaosy", headerFont);
        p1.setAlignment(Element.ALIGN_LEFT);

//        Paragraph p2 = new Paragraph("Daty: " + DateUtils.getDateMalgache(LocalDate.now()));


//        Image logo = Image.getInstance("src/main/resources/static/assets/img/logo.png");
//        logo.scaleToFit(80, 80);
//        logo.setAlignment(Element.ALIGN_RIGHT);

        PdfPCell p1Cell = new PdfPCell();
        document.add(p1);

        document.add(new Paragraph("Distrika: ITAOSY"));
        document.add(new Paragraph("Paroasy: Masina Misely Itaosy"));

        Paragraph espacement = new Paragraph();
        espacement.setSpacingBefore(20);
        document.add(espacement);
        document.add(new Paragraph("Budget: %s".formatted(budget.getLibelle())));
        document.add(new Paragraph("Date prévu: du %s au %s ".formatted(DateUtils.getFormatParDefaut(budget.getDateDebut()), DateUtils.getFormatParDefaut(budget.getDateFin()))));
        document.add(new Paragraph("Montant du budget: %s".formatted(NombreUtils.affichageMonetaire(budget.getMontant()))));
        document.add(new Paragraph("Arreté le: %s".formatted(budget.getDateArret() != null ? DateUtils.getFormatParDefaut(budget.getDateArret()) : "-")));
//        document.add(new );
//        Paragraph p2Cell = new PdfPCell();
//        document.add(p2);
//        document.setRectangle.NO_BORDER);
//        PdfPCell logoCell = new PdfPCell(logo);
//        logoCell.setBorder(Rectangle.NO_BORDER);

//        headerTable.addCell(p1Cell);
//        headerTable.addCell(logoCell);
//        document.add(new Paragraph());
//        document.add(p1Cell);
//        document.add(logo);

        // Titre
//        String apresTitre = "";
//        if(dateDebut != null) apresTitre += " nanomboka ny " + DateUtils.getDateMalgache(dateDebut);
//        if(dateFin != null) apresTitre += " hatramin'ny " + DateUtils.getDateMalgache(dateFin);
//
//        Paragraph title = new Paragraph("Toe-bolan'ny fiangonana" + apresTitre, titleFont);
//        title.setSpacingBefore(20);
//        title.setAlignment(Element.ALIGN_CENTER);
//        document.add(title);

        // Période
//        Font periodFont = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL);
//        Paragraph period = new Paragraph("Période : " + this.getDateDebut() + " au " + this.getDateFin(), periodFont);
//        period.setAlignment(Element.ALIGN_CENTER);
//        period.setSpacingAfter(20);
//        document.add(period);

        Paragraph espacement2 = new Paragraph();
        espacement2.setSpacingBefore(20);
        document.add(espacement2);

        // Tableau des lignes
        PdfPTable table = new PdfPTable(3); // 3 colonnes
        table.setWidthPercentage(100);
        table.setSpacingBefore(5f);
        table.setWidths(new int[]{20, 50, 30});

        // Entêtes
        addTableHeader(table,  "Date", "Libellé", "Montant");

        Font cellFont = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL);
        Font footerFont = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD);



        // Lignes
        double total = 0.00;
        int i = 0;
        for (MvtCaisse ligne : mvtCaisses) {
            PdfPCell dateCell = new PdfPCell(new Paragraph(ligne.getDate() != null ? ligne.getDate().format(DateTimeFormatter.ofPattern("dd / MM / yyyy")): "", cellFont));
//            PdfPCell codeCell = new PdfPCell(new Paragraph(ligne.getCode(), cellFont));
            PdfPCell libelleCell = new PdfPCell(new Paragraph(ligne.getLibelle(), cellFont));
//            PdfPCell entreeCell = new PdfPCell(new Paragraph(NombreUtils.affichageMonetaire(ligne.getEntree()), cellFont));
            PdfPCell sortieCell = new PdfPCell(new Paragraph(NombreUtils.affichageMonetaire(ligne.getSortie()), cellFont));
//            PdfPCell soldesCell = new PdfPCell(new Paragraph(NombreUtils.affichageMonetaire(ligne.getSoldes()), cellFont));

            dateCell.setBorderWidthBottom(0);
//            codeCell.setBorderWidthBottom(0);
            libelleCell.setBorderWidthBottom(0);
//            entreeCell.setBorderWidthBottom(0);
            sortieCell.setBorderWidthBottom(0);
//            soldesCell.setBorderWidthBottom(0);

            dateCell.setBorderWidthTop(0);
//            codeCell.setBorderWidthTop(0);
            libelleCell.setBorderWidthTop(0);
//            entreeCell.setBorderWidthTop(0);
            sortieCell.setBorderWidthTop(0);
            total += ligne.getSortie();
//            soldesCell.setBorderWidthTop(0);

//            dateCell.set



            if(i == mvtCaisses.size() - 1) {
                dateCell.setBorderWidthBottom(1);
//                codeCell.setBorderWidthBottom(1);
                libelleCell.setBorderWidthBottom(1);
//                entreeCell.setBorderWidthBottom(1);
                sortieCell.setBorderWidthBottom(1);
//                soldesCell.setBorderWidthBottom(1);
//                dateCell.setBorder(Rectangle.BOX);
//                codeCell.setBorder(Rectangle.BOX);
//                libelleCell.setBorder(Rectangle.BOX);
//                entreeCell.setBorder(Rectangle.BOX);
//                sortieCell.setBorder(Rectangle.BOX);
//                soldesCell.setBorder(Rectangle.BOX);

//                dateCell.setBorderWidthBottom(0);
//                codeCell.setBorderWidthBottom(0);
//                libelleCell.setBorderWidthBottom(0);
//                entreeCell.setBorderWidthBottom(0);
//                sortieCell.setBorderWidthBottom(0);
//                soldesCell.setBorderWidthBottom(0);
            }



//            entreeCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            sortieCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//            soldesCell.setHorizontalAlignment(Element.ALIGN_RIGHT);


            table.addCell(dateCell);
//            table.addCell(codeCell);
            table.addCell(libelleCell);
//            table.addCell(entreeCell);
            table.addCell(sortieCell);
//            table.addCell(soldesCell);


            i++;
//            table.addCell(String.valueOf(ligne.getDate()));
//            table.addCell(ligne.getCode());
//            table.addCell(ligne.getLibelle());
//            table.addCell(NombreUtils.affichageMonetaire(ligne.getEntree()));
//            table.addCell(NombreUtils.affichageMonetaire(ligne.getSortie()));
//            table.addCell(NombreUtils.affichageMonetaire(ligne.getSoldes()));
        }

        for(int j = 0; j < 1; j++) {
            PdfPCell skipCell = new PdfPCell(new Phrase(""));
            skipCell.setBorder(Rectangle.NO_BORDER);
            table.addCell(skipCell);
        }
        table.addCell(new PdfPCell(new Paragraph("TOTAL", footerFont)));
//        table.addCell(new PdfPCell(new Paragraph(NombreUtils.affichageMonetaire(this.getTotalEntree()), footerFont)));
        table.addCell(new PdfPCell(new Paragraph(NombreUtils.affichageMonetaire(total), footerFont)));
//        table.addCell(new PdfPCell(new Paragraph(NombreUtils.affichageMonetaire(this.getTotalEntree() - this.getTotalSortie()), footerFont)));

        document.add(table);
        document.close();

        return baos.toByteArray();
    }

    private void addTableHeader(PdfPTable table, String... headers) {
        Font headFont = new Font(Font.FontFamily.HELVETICA, 11, Font.BOLD);
        for (String header : headers) {
            PdfPCell cell = new PdfPCell(new Phrase(header, headFont));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
        }
    }
}
