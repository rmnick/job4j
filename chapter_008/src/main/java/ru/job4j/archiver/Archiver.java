package ru.job4j.archiver;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Archiver {

    public static void main(String[] args) {
        Archiver arch = new Archiver();
        String path = null;
        String[] str = null;
        String name = null;
        List<File> base = new ArrayList<>();

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-d":
                    path = args[i + 1];
                    break;
                case "-e":
                    str = args[i + 1].split(",");
                    break;
                case "-o":
                    name = args[i + 1];
                    break;
                    default:
                        break;
            }
        }

        if (path != null && str != null && name != null) {
            arch.collect(path, str, base);
            arch.archive(base, path, name);
        }
    }

    /**
     * search and collect all files with correct extension
     * @param path
     * @param extensions
     * @param base
     */
    public void collect(String path, String[] extensions, List<File> base) {
        File dir = new File(path);
        File[] files = dir.listFiles();
        for (File f : files) {
            if (!f.isDirectory()) {
                for (String ext : extensions) {
                    if (f.getName().endsWith("." + ext)) {
                        base.add(f);
                    }
                }
            } else {
                collect(f.getPath(), extensions, base);
            }
        }
    }

    /**
     * create an archive whit files(keeping the structure)
     * @param files
     * @param path
     * @param name
     */
    public void archive(List<File> files, String path, String name) {
        BufferedInputStream in = null;
        BufferedOutputStream out = null;
        try {
            FileOutputStream fos = new FileOutputStream(path + name);
            ZipOutputStream zos = new ZipOutputStream(fos);
            out = new BufferedOutputStream(zos);
            for (File f : files) {
                in = new BufferedInputStream(new FileInputStream(f));
                zos.putNextEntry(new ZipEntry(f.getPath()));
                int c;
                while ((c = in.read()) != -1) {
                    out.write(c);
                }
                out.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
