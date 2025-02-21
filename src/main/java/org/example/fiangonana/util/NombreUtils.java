package org.example.fiangonana.util;

public class NombreUtils {

    public static boolean comparerDouble(double d1, double d2) {
        return comparerDouble(d1, d2, 1e-6);
    }

    public static boolean comparerDouble(double d1, double d2, double precision) {
        return Math.abs(d1 - d2) < precision;
    }
}
