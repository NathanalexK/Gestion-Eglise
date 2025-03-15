package org.example.fiangonana.util;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Objects;

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

    public static String affichageIntervalleDateMalgache(LocalDate dmin, LocalDate dmax) {
        Locale mg = new Locale("mg");
        String str = "ny ?1 hatramin'ny ?2";
        if(Objects.equals(dmin.getYear(), dmax.getYear())) {
            return str
                    .replace("?1", dmin.format(DateTimeFormatter.ofPattern("dd MMMM", mg)))
                    .replace("?2", dmax.format(DateTimeFormatter.ofPattern("dd MMMM yyyy", mg)));
        }
        return str
                .replace("?1", getDateMalgache(dmin))
                .replace("?2", getDateMalgache(dmax));
    }
}
