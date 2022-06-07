package com.elonsoft.domain.formula;

import java.util.HashMap;

public class Numbers extends HashMap<String, Double> {

    private int index;

    public String set(String stringNumber) {
        if (stringNumber.length() == 0) {
            return null;
        }
        String key = String.valueOf(index++);
        this.put(key, Double.valueOf(stringNumber));
        return key;
    }
}
