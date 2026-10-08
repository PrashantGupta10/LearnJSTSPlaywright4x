package com.salesforce.automation.tests;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.salesforce.automation.base.BaseTest;
import com.salesforce.automation.listeners.TestExecutionListener;
import com.salesforce.automation.pages.LoginPage;

@Listeners(TestExecutionListener.class)
public class ValidLoginTest extends BaseTest {
    private final String username = System.getenv("SF_USERNAME");
    private final String password = System.getenv("SF_PASSWORD");

    @Test(priority = 1, groups = {"smoke", "regression"}, description = "Log in with valid Salesforce credentials")
    public void loginWithValidCredentials() {
        if (isBlank(username) || isBlank(password)) {
            throw new SkipException("Set SF_USERNAME and SF_PASSWORD to run the valid-login test.");
        }

        LoginPage loginPage = new LoginPage(driver(), explicitWaitTimeout());
        loginPage.setRememberMe(true);
        loginPage.login(username, password);
        Assert.assertTrue(loginPage.hasLeftLoginPage(), "Valid credentials should leave the Salesforce login page.");
    }

    private boolean isBlank(String value) {
        return value == null || value.isBlank();
    }
}