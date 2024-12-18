package com.qa.listeners;

import com.qa.reports.ExtentLogger;
import com.qa.reports.Report;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.*;

public class TestListener implements ITestListener, ISuiteListener {
    private static final Logger logger= LoggerFactory.getLogger(TestListener.class);
    @Override
    public void onStart(ISuite suite) {
        Report.initReport();
    }

    @Override
    public void onFinish(ISuite suite) {
        Report.flushReport();
    }

    @Override
    public void onTestStart(ITestResult result) {
        logger.info("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        logger.info("Started Test case {}",result.getMethod().getMethodName().toUpperCase());
        Report.createTestCase(result.getMethod().getMethodName().toUpperCase());
//        Report.addAuthors(result.getMethod().getConstructorOrMethod().getMethod()
//                .getAnnotation(FrameworkAnnotation.class).authors());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        logger.info("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        logger.info("Status for {} :: Passed ##### ", result.getMethod().getMethodName());
        ExtentLogger.pass("-------Passed:: " + result.getMethod().getMethodName() + "--------");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logger.info("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        logger.info("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        logger.error("Status for {} :: Failed ##### ", result.getMethod().getMethodName());
        ExtentLogger.fail("Failed ::" + result.getMethod().getMethodName().toUpperCase());
        ExtentLogger.fail("Reason :: " + result.getThrowable().getLocalizedMessage());
        logger.error("Reason for {} :: Failed is {} ##### ", result.getMethod().getMethodName(),result.getThrowable().getLocalizedMessage());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logger.info("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        logger.info("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        ExtentLogger.skip("Skipped ::" + result.getMethod().getMethodName());
        logger.error("Status for {} :: Skipped ##### ", result.getMethod().getMethodName());

        ExtentLogger.fail("Reason :: " + result.getThrowable().getLocalizedMessage());
    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {
        ITestListener.super.onFinish(context);
    }
}
