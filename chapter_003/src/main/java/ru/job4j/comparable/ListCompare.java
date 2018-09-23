package ru.job4j.comparable;

import java.util.Arrays;
import java.util.Comparator;

public class ListCompare implements Comparator<String> {
    @Override
    public int compare(String left, String right) {
        int result = 0;
        int length = left.length() <= right.length() ? left.length() : right.length();
        for (int i = 0; i < length; i++) {
            if (result == 0) {
                result = Character.compare(left.charAt(i), right.charAt(i));
            } else {
                break;
            }
        }
        return result != 0 ? result : Integer.compare(left.length(), right.length());
    }
}
