package ru.job4j.even;


import java.io.*;

public class EvenChecker {

    public boolean findEvenSimbol(InputStream in) {
        boolean result = false;
        try {
            int data = in.read();
            while (data != -1) {
                char c = (char) data;
                if (Character.isDigit(c)
                        && Integer.parseInt(String.valueOf(c)) != 0
                        && Integer.parseInt(String.valueOf(c)) % 2 == 0) {
                    result = true;
                    break;
                }
                data = in.read();
                }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean findEvenNumber(InputStream in) {
        boolean result = false;
        StringBuilder str = new StringBuilder();
        try {
            int data = in.read();
            while (data != -1) {
                char c = (char) data;
                if (Character.isDigit(c)) {
                    str.append(c);
                } else {
                    if (str.length() != 0
                            && Integer.parseInt(str.toString()) != 0
                            && Integer.parseInt(str.toString()) % 2 == 0) {
                        result = true;
                        break;
                    }
                    str = new StringBuilder();
                }
                data = in.read();
            }
        } catch (IOException e) {
                e.printStackTrace();
            }
        return result;
    }
 }
