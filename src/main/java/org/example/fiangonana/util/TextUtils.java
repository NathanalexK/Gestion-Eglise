package org.example.fiangonana.util;

public class TextUtils {

    public static String retrecirTexte(String texte, int nbChar) {
        int textlen = texte.length();
        if(textlen <= nbChar) {
            return texte;
        }
        else {
            return texte.substring(0, nbChar-1).concat("...");
        }
    }
}
