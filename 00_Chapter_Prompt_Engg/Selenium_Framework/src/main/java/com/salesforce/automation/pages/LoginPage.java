package com.salesforce.automation.pages;

import java.time.Duration;
import java.util.Objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(xpath = "//input[@id='username']")
    private WebElement username;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement password;

    @FindBy(xpath = "//input[@id='Login']")
    private WebElement loginButton;

    @FindBy(xpath = "//input[@id='rememberUn']")
    private WebElement rememberMe;

    @FindBy(xpath = "//div[@id='error']")
    private WebElement loginError;

    public LoginPage(WebDriver driver) {
        this(driver, Duration.ofSeconds(20));
    }

    public LoginPage(WebDriver driver, Duration waitTimeout) {
        this.driver = Objects.requireNonNull(driver, "WebDriver must not be null");
        this.wait = new WebDriverWait(driver, Objects.requireNonNull(waitTimeout, "Wait timeout must not be null"));
        PageFactory.initElements(driver, this);
        try {
            wait.until(ExpectedConditions.visibilityOf(username));
        } catch (WebDriverException exception) {
            throw new IllegalStateException("Salesforce login form did not load.", exception);
        }
    }

    public void setRememberMe(boolean selected) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(rememberMe));
            if (rememberMe.isSelected() != selected) {
                rememberMe.click();
            }
        } catch (WebDriverException exception) {
            throw new IllegalStateException("Could not update the Remember me option.", exception);
        }
    }

    public void login(String user, String pass) {
        try {
            wait.until(ExpectedConditions.visibilityOf(username)).clear();
            username.sendKeys(Objects.requireNonNull(user, "Username must not be null"));
            wait.until(ExpectedConditions.visibilityOf(password))
                    .sendKeys(Objects.requireNonNull(pass, "Password must not be null"));
            wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
        } catch (WebDriverException exception) {
            throw new IllegalStateException("Could not submit the Salesforce login form.", exception);
        }
    }

    public String getLoginErrorMessage() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(loginError)).getText().trim();
        } catch (WebDriverException exception) {
            throw new IllegalStateException("Salesforce did not display a login error.", exception);
        }
    }

    public boolean hasLeftLoginPage() {
        try {
            wait.until(ExpectedConditions.not(ExpectedConditions.urlContains("login.salesforce.com")));
            return !driver.getCurrentUrl().contains("login.salesforce.com");
        } catch (WebDriverException exception) {
            throw new IllegalStateException("Authentication did not leave the Salesforce login page.", exception);
        }
    }
}