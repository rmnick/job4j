package ru.job4j.corrector;

import java.io.*;

public class Corrector {

    public static void main(String[] args) {
        String str = "fdkjfhskd dhfsdfhsdfh dsfhksdhfkhsdk fdsdjfhkhsdkf";
        try (InputStream in = new ByteArrayInputStream(str.getBytes());
             OutputStream out = new ByteArrayOutputStream()) {
            correct(in, out, abuse);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String[] abuse = {"fuck", "cunt"};

    public static void correct(InputStream in, OutputStream out, String[] abuse) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            char c;
            String str = br.readLine();
            while (str != null) {
                out.write(str.getBytes());
               System.out.println(out);
               str = br.readLine();
            }
            br.close();
        } catch (IOException e) {

        }
    }


}
