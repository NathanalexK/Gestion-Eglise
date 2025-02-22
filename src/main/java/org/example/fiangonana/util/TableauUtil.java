package org.example.fiangonana.util;

import java.util.List;
import java.util.function.Function;

public class TableauUtil {

    public static <T> Object[][] toTableau2D(List<T> data, Function<T, ?>... getterFunctions) {
        Object[][] tab = new Object[data.size()][];
        int i = 0;
        for(T item: data) {
            tab[i] = new Object[getterFunctions.length];
            for(int j = 0; j < getterFunctions.length; j++) {
                tab[i][j] = getterFunctions[i].apply(item);
            }
        }
        return tab;
    }
}
