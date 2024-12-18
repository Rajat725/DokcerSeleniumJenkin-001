package com.qa.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class ResourceLoader {
    public static final Logger logger = LoggerFactory.getLogger(ResourceLoader.class);

    public static InputStream getAsStream(String path) {
        // Attempt to load resource from classpath
        InputStream resourceAsStream = ResourceLoader.class.getClassLoader().getResourceAsStream(path);
        if (Objects.nonNull(resourceAsStream)) {
            logger.info("Reading resource from classpath: {}", path);
            return resourceAsStream;
        }

        // Attempt to load resource from file system
        try {
            logger.info("Reading resource from project directory: {}", path);
            return Files.newInputStream(Path.of(path));
        } catch (IOException e) {
            logger.error("Failed to load resource from path: {}", path, e);
            throw new RuntimeException("Resource not found in classpath or project directory: " + path, e);
        }
    }
}
