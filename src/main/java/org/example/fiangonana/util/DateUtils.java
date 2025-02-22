package org.example.fiangonana.util;

import org.example.fiangonana.dto.util.DateIntervalleDTO;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateUtils {
    public static String getDateMalgache(LocalDate localDate) {
        if(localDate == null) {
            System.out.println("Date is null");
            return "";
        }

        return localDate.format(DateTimeFormatter.ofPattern("dd MMMM yyyy", new Locale("mg")));
    }

    public static String getFormatParDefaut(LocalDate localDate) {
        if(localDate == null) {
            return "";
        }

        return localDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public static LocalDate[] getIntervalleMois(LocalDate date) {
        YearMonth mois = YearMonth.from(date);
        LocalDate debutMois = mois.atDay(1);
        LocalDate finMois = mois.atEndOfMonth();
        return new LocalDate[]{debutMois, finMois};
    }
}
