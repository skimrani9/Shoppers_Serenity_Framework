package com.shoppers.helpers;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Loads environment-specific properties from src/test/resources/config.
 */
public final class TestConfigLoader {

    private static final Properties PROPERTIES = new Properties();

    private TestConfigLoader() {
    }

    public static void load(String environment) throws IOException {
        String fileName = "config/" + environment + "Config.properties";
        try (InputStream input = TestConfigLoader.class.getClassLoader().getResourceAsStream(fileName)) {
            if (input == null) {
                throw new IOException("Config not found: " + fileName);
            }
            PROPERTIES.clear();
            PROPERTIES.load(input);
        }
    }

    public static String get(String key) {
        return PROPERTIES.getProperty(key);
    }

    public static String get(String key, String defaultValue) {
        return PROPERTIES.getProperty(key, defaultValue);
    }
}
