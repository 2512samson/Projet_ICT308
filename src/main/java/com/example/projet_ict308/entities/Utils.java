package com.example.projet_ict308.entities;

import java.util.Collections;
import java.util.List;

public class Utils {
    public static <T> List<T> shuffleList(List<T> list) {
        Collections.shuffle(list);
        return list;
    }
}
