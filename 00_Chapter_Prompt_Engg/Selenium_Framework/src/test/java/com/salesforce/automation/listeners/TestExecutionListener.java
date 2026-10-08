package com.salesforce.automation.listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.salesforce.automation.base.BaseTest;

public class TestExecutionListener implements ITestListener {
    @Override
    public void onTestStart(ITestResult result) {
        Reporter.log("START: " + result.getName(), true);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        Reporter.log("PASS: " + result.getName(), true);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Reporter.log("FAIL: " + result.getName() + " - " + result.getThrowable().getMessage(), true);
        Object screenshot = result.getAttribute("failureScreenshot");
        if (screenshot != null) {
            Reporter.log("Screenshot: " + screenshot, true);
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        Reporter.log("SKIP: " + result.getName(), true);
    }
}