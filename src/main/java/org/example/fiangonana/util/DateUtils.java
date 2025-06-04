package org.example.fiangonana.util;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
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

    public static LocalDate[] getIntervalleMois(int mm, int aaaa) {
        YearMonth mois = YearMonth.of(aaaa, mm);
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

    public static String affichageIntervalleDateFrancais(LocalDate dmin, LocalDate dmax) {
        Locale fr = new Locale("fr");
        String str = "";
        if(dmin != null) {
            str += " du " + dmin.format(DateTimeFormatter.ofPattern("dd MMMM yyyy", fr));
        }
        if(dmax != null) {
            str += " au " + dmax.format(DateTimeFormatter.ofPattern("dd MMMM yyyy", fr));
        }
        return str;
    }

    public static LocalDate getMoisPrecedent(LocalDate date) {
        int mois, annee;
        if(date.getMonthValue() == 1) {
            mois = 12;
            annee = date.getYear() - 1;
        } else {
            mois = date.getMonthValue() - 1;
            annee = date.getYear();
        }
        return LocalDate.of(annee, mois, 1);
    }


    public static String convertirMoisEnString(int mois) {
        String[] moisNoms = {
                "Janvier", "Février", "Mars", "Avril", "Mai", "Juin",
                "Juillet", "Août", "Septembre", "Octobre", "Novembre", "Décembre"
        };

        if (mois >= 1 && mois <= 12) {
            return moisNoms[mois - 1];
        } else {
            throw new IllegalArgumentException("Le mois doit être entre 1 et 12.");
        }
    }

    public static List<MoisIntervalleDate> get12DerniersMois(int mois, int annee) {
        List<MoisIntervalleDate> liste = new ArrayList<>();

        // On commence à partir du mois/année donnés et on recule de 11 mois
        YearMonth current = YearMonth.of(annee, mois);

        for (int i = 11; i >= 0; i--) {
            YearMonth ym = current.minusMonths(i);
            LocalDate debut = ym.atDay(1);
            LocalDate fin = ym.atEndOfMonth();
            String nom = ym.getMonth().getDisplayName(TextStyle.FULL, Locale.FRENCH);
            liste.add(new MoisIntervalleDate(ym.getMonthValue(), ym.getYear(),  nom, debut, fin));
        }

        return liste;
    }


}
