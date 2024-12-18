package com.qa.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.qa.constants.FrameworkConstants;

import java.util.Objects;

public class Report {
    static ExtentReports extentReport;
    public static void initReport()
    {
        if(Objects.isNull(extentReport)) {
            extentReport = new ExtentReports();
            ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(FrameworkConstants.getReportPath());
            extentReport.attachReporter(extentSparkReporter);
            extentSparkReporter.config().setReportName("Tests Results");
            extentSparkReporter.config().setDocumentTitle("Results");
            extentSparkReporter.config().setTheme(Theme.STANDARD);
            extentReport.setSystemInfo("Tester", "Levant");
            extentReport.setSystemInfo("OS", "Windows");
            extentReport.setSystemInfo("Type", "Automation");
        }
    }
    public static void flushReport()
    {
        if(Objects.nonNull(extentReport))
        {
        extentReport.flush();
        ExtentManager.unloadExtentTests();
//        try {
//            Desktop.getDesktop().browse(new File(FrameworkConstants.getReportPath()).toURI());
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }
    }

    public static ExtentTest createTestCase(String testCaseName)
    {
        ExtentManager.setExtentTest(extentReport.createTest(testCaseName));
        return ExtentManager.getExtentTest();
    }

//    public static void addAuthors(String[] authors)
//    {
//        for (String author:
//             authors) {
//            ExtentManager.getExtentTest().assignAuthor(author);
//        }
//
//    }
}
