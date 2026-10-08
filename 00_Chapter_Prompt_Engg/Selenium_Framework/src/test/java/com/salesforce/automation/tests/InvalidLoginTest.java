package com.salesforce.automation.tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.salesforce.automation.base.BaseTest;
import com.salesforce.automation.listeners.TestExecutionListener;
import com.salesforce.automation.pages.LoginPage;

@Listeners(TestExecutionListener.class)
public class InvalidLoginTest extends BaseTest {

    @Test(priority = 2, groups = "regression", description = "Reject invalid Salesforce credentials")
    public void loginWithInvalidCredentialsShowsError() {
        LoginPage loginPage = new LoginPage(driver(), explicitWaitTimeout());
        loginPage.setRememberMe(false);
        loginPage.login("admin", "admin");

        String errorMessage = loginPage.getLoginErrorMessage();
        Assert.assertFalse(errorMessage.isBlank(), "Invalid credentials should display a login error.");
    }
}