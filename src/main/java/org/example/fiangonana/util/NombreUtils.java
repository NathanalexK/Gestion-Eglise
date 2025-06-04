package org.example.fiangonana.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class NombreUtils {

    public static boolean comparerDouble(double d1, double d2) {
        return comparerDouble(d1, d2, 1e-6);
    }

    public static boolean comparerDouble(double d1, double d2, double precision) {
        return Math.abs(d1 - d2) < precision;
    }

    public static String affichageMonetaire(Double d) {
        if(d == null) return null;

        // Créer un formatteur avec le modèle de format
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");

        // Définir la locale pour avoir la virgule comme séparateur décimal
        decimalFormat.setDecimalSeparatorAlwaysShown(true);

        // Appliquer le format
        return decimalFormat.format(d).replace(',', ' ').replace('.', ',');
    }

    public static Double getDouble(BigDecimal b) {
        return b != null ? b.doubleValue() : null;
    }

//    public static Double bigDecimalToDou
}
