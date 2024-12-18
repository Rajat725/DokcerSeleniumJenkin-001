package com.qa.constants;

import com.qa.utils.ResourceLoader;

import java.io.InputStream;

public class FrameworkConstants {

    private static final String CONFIG_FILE_PATH = "configurations//config.properties";
    private static final String REPORT_FILE_PATH = "Results";// Assuming "Results" is relative to the project directory

    private static final String Test_Data_Path="test-data//TestData.json";
    // Method to get InputStream for the config file
    public static InputStream getConfigFileAsStream() {
        return ResourceLoader.getAsStream(CONFIG_FILE_PATH);
    }

    public static InputStream getTestDataStream() {
        return ResourceLoader.getAsStream(Test_Data_Path);
    }

    public static String getReportPath() {
        return REPORT_FILE_PATH;
    }
}
