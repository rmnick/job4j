package ru.job4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    private final Properties values = new Properties();

    public Config() {
        try (InputStream in = Config.class.getClassLoader().getResourceAsStream("app.properties")) {
            values.load(in);
        } catch (IOException e) {
           e.printStackTrace();
        }
    }

    public String get(final String key) {
        return this.values.getProperty(key);
    }
}