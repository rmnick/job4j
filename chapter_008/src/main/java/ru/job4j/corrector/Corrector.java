package ru.job4j.corrector;

import java.io.*;

public class Corrector {

    public void correct(InputStream in, OutputStream out, String[] abuse) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
            String str = br.readLine();
            while (str != null) {
                for (String badWord : abuse) {
                    str = str.replaceAll(String.format("\\b%s\\b", badWord), "");
                }
                out.write(str.getBytes());
               str = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

