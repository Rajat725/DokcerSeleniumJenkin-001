package com.qa.utils;

import com.qa.constants.FrameworkConstants;
import com.qa.enums.PropertyConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

public final class ReadPropertyFile {
    private ReadPropertyFile() {
        // Private constructor to prevent instantiation
    }

    private static final Properties properties = new Properties();
    private static final Map<String, String> map = new HashMap<>();

    static {
        try (InputStream configFileAsStream = FrameworkConstants.getConfigFileAsStream()) {
            if (configFileAsStream == null) {
                throw new RuntimeException("Error: Properties file not found in classpath or project directory.");
            }
            properties.load(configFileAsStream);
            properties.forEach((key, value) -> map.put(String.valueOf(key), String.valueOf(value)));
        } catch (IOException e) {
            throw new RuntimeException("Error: Unable to load properties file.", e);
        }
    }

    public static String getValue(PropertyConfig propertyConfig) {
        String key = propertyConfig.toString().toLowerCase();
        String value = System.getProperties().containsKey(key)?System.getProperty(key):map.get(key);
        if (value == null) {
            throw new RuntimeException("Error: Invalid or missing key - " + key);
        }
        return value;
    }
}
