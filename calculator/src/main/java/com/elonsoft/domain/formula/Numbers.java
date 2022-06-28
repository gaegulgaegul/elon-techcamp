package com.elonsoft.domain.formula;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 치환된 숫자 정보를 담는 클래스
 */
public class Numbers {

    private Map<String, Double> numbers;

    private int index;

    public Numbers() {
        this.numbers = new HashMap<>();
        this.index = 0;
    }

    /**
     * 숫자 정보를 담고 해당 숫자의 key를 반환한다.
     * @param stringNumber
     * @return
     */
    public String set(String stringNumber) {
        if (stringNumber.length() == 0) {
            return null;
        }
        String key = String.valueOf(index++);
        numbers.put(key, Double.valueOf(stringNumber));
        return key;
    }

    /**
     * 키에 해당하는 숫자 정보 반환
     * @param key
     * @return
     */
    public Double get(String key) {
        return numbers.get(key);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Numbers numbers1 = (Numbers) o;
        return index == numbers1.index && Objects.equals(numbers, numbers1.numbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numbers, index);
    }
}
