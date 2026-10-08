package com.salesforce.automation.pages;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.mockito.Mockito;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginPageTest {
    private static final String USERNAME_XPATH = "//input[@id='username']";
    private static final String PASSWORD_XPATH = "//input[@id='password']";
    private static final String LOGIN_BUTTON_XPATH = "//input[@id='Login']";
    private static final String REMEMBER_ME_XPATH = "//input[@id='rememberUn']";
    private static final String LOGIN_ERROR_XPATH = "//div[@id='error']";
    private static final Duration SHORT_WAIT = Duration.ofMillis(1);

    private WebDriver driver;
    private Map<String, WebElement> elements;

    @BeforeMethod
    public void setUp() {
        driver = Mockito.mock(WebDriver.class);
        elements = new HashMap<>();
        when(driver.findElement(any(By.class))).thenAnswer(invocation -> {
            By locator = invocation.getArgument(0);
            return elementFor(locator.toString());
        });
    }

    @Test
    public void constructorRejectsNullDriver() {
        NullPointerException exception = expectException(NullPointerException.class,
                () -> new LoginPage(null, SHORT_WAIT));

        assertEquals(exception.getMessage(), "WebDriver must not be null");
    }

    @Test
    public void constructorRejectsNullWaitTimeout() {
        NullPointerException exception = expectException(NullPointerException.class,
                () -> new LoginPage(driver, null));

        assertEquals(exception.getMessage(), "Wait timeout must not be null");
    }

    @Test
    public void constructorWaitsForUsernameUsingDefaultTimeout() {
        LoginPage loginPage = new LoginPage(driver);

        assertTrue(loginPage != null);
        verify(driver).findElement(By.xpath(USERNAME_XPATH));
    }

    @Test
    public void constructorWrapsFailureWhenLoginFormDoesNotLoad() {
        when(elementFor(By.xpath(USERNAME_XPATH).toString()).isDisplayed()).thenReturn(false);

        IllegalStateException exception = expectException(IllegalStateException.class,
                () -> new LoginPage(driver, SHORT_WAIT));

        assertEquals(exception.getMessage(), "Salesforce login form did not load.");
    }

    @Test
    public void setRememberMeClicksWhenSelectionMustChange() {
        LoginPage loginPage = new LoginPage(driver, SHORT_WAIT);
        WebElement rememberMe = elementFor(By.xpath(REMEMBER_ME_XPATH).toString());

        loginPage.setRememberMe(true);

        verify(rememberMe).click();
    }

    @Test
    public void setRememberMeDoesNotClickWhenSelectionAlreadyMatches() {
        LoginPage loginPage = new LoginPage(driver, SHORT_WAIT);
        WebElement rememberMe = elementFor(By.xpath(REMEMBER_ME_XPATH).toString());

        loginPage.setRememberMe(false);

        verify(rememberMe, never()).click();
    }

    @Test
    public void setRememberMeWrapsWebDriverFailure() {
        LoginPage loginPage = new LoginPage(driver, SHORT_WAIT);
        when(elementFor(By.xpath(REMEMBER_ME_XPATH).toString()).isDisplayed()).thenReturn(false);

        IllegalStateException exception = expectException(IllegalStateException.class,
                () -> loginPage.setRememberMe(true));

        assertEquals(exception.getMessage(), "Could not update the Remember me option.");
    }

    @Test
    public void loginClearsAndSubmitsCredentials() {
        LoginPage loginPage = new LoginPage(driver, SHORT_WAIT);
        WebElement username = elementFor(By.xpath(USERNAME_XPATH).toString());
        WebElement password = elementFor(By.xpath(PASSWORD_XPATH).toString());
        WebElement loginButton = elementFor(By.xpath(LOGIN_BUTTON_XPATH).toString());

        loginPage.login("test-user", "test-password");

        verify(username).clear();
        verify(username).sendKeys("test-user");
        verify(password).sendKeys("test-password");
        verify(loginButton).click();
    }

    @Test
    public void loginRejectsNullUsername() {
        LoginPage loginPage = new LoginPage(driver, SHORT_WAIT);

        NullPointerException exception = expectException(NullPointerException.class,
                () -> loginPage.login(null, "test-password"));

        assertEquals(exception.getMessage(), "Username must not be null");
    }

    @Test
    public void loginWrapsWebDriverFailure() {
        LoginPage loginPage = new LoginPage(driver, SHORT_WAIT);
        WebElement username = elementFor(By.xpath(USERNAME_XPATH).toString());
        doThrow(new WebDriverException("Browser disconnected")).when(username).clear();

        IllegalStateException exception = expectException(IllegalStateException.class,
                () -> loginPage.login("test-user", "test-password"));

        assertEquals(exception.getMessage(), "Could not submit the Salesforce login form.");
    }

    @Test
    public void getLoginErrorMessageTrimsWhitespace() {
        LoginPage loginPage = new LoginPage(driver, SHORT_WAIT);
        when(elementFor(By.xpath(LOGIN_ERROR_XPATH).toString()).getText())
                .thenReturn("  Invalid username or password.  ");

        assertEquals(loginPage.getLoginErrorMessage(), "Invalid username or password.");
    }

    @Test
    public void getLoginErrorMessageWrapsMissingMessage() {
        LoginPage loginPage = new LoginPage(driver, SHORT_WAIT);
        when(elementFor(By.xpath(LOGIN_ERROR_XPATH).toString()).isDisplayed()).thenReturn(false);

        IllegalStateException exception = expectException(IllegalStateException.class,
                loginPage::getLoginErrorMessage);

        assertEquals(exception.getMessage(), "Salesforce did not display a login error.");
    }

    @Test
    public void hasLeftLoginPageReturnsTrueForAnotherUrl() {
        LoginPage loginPage = new LoginPage(driver, SHORT_WAIT);
        when(driver.getCurrentUrl()).thenReturn("https://example.test/home");

        assertTrue(loginPage.hasLeftLoginPage());
    }

    @Test
    public void hasLeftLoginPageThrowsWhenUrlStaysOnLoginPage() {
        LoginPage loginPage = new LoginPage(driver, SHORT_WAIT);
        when(driver.getCurrentUrl()).thenReturn("https://login.salesforce.com/timeout");

        IllegalStateException exception = expectException(IllegalStateException.class,
                loginPage::hasLeftLoginPage);

        assertEquals(exception.getMessage(), "Authentication did not leave the Salesforce login page.");
    }

    @Test
    public void hasLeftLoginPageWrapsWebDriverFailure() {
        LoginPage loginPage = new LoginPage(driver, SHORT_WAIT);
        when(driver.getCurrentUrl()).thenThrow(new WebDriverException("Browser disconnected"));

        IllegalStateException exception = expectException(IllegalStateException.class,
                loginPage::hasLeftLoginPage);

        assertEquals(exception.getMessage(), "Authentication did not leave the Salesforce login page.");
    }

    private <T extends Throwable> T expectException(Class<T> exceptionType, Runnable action) {
        try {
            action.run();
        } catch (Throwable exception) {
            if (exceptionType.isInstance(exception)) {
                return exceptionType.cast(exception);
            }
            throw new AssertionError("Unexpected exception type: " + exception.getClass().getName(), exception);
        }
        throw new AssertionError("Expected exception type: " + exceptionType.getName());
    }

    private WebElement elementFor(String locator) {
        return elements.computeIfAbsent(locator, ignored -> {
            WebElement element = Mockito.mock(WebElement.class);
            when(element.isDisplayed()).thenReturn(true);
            when(element.isEnabled()).thenReturn(true);
            when(element.isSelected()).thenReturn(false);
            return element;
        });
    }
}
