package com.salesforce.automation.base;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {
    private static final Properties CONFIG = loadConfiguration();
    private static final DateTimeFormatter SCREENSHOT_TIMESTAMP = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");

    private final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    @BeforeMethod(alwaysRun = true)
    public void setUpBrowser() {
        WebDriver browser = createDriver();
        driver.set(browser);
        try {
            browser.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
            if (!Boolean.parseBoolean(setting("headless", "false"))) {
                browser.manage().window().maximize();
            }
            browser.get(setting("base.url", "https://login.salesforce.com/?locale=in"));
        } catch (WebDriverException exception) {
            closeBrowser(browser);
            throw new IllegalStateException("Could not initialize the browser for the test.", exception);
        }
    }

    @AfterMethod(alwaysRun = true)
    public void tearDownBrowser(ITestResult result) {
        WebDriver browser = driver.get();
        if (browser != null) {
            try {
                if (result.getStatus() == ITestResult.FAILURE) {
                    try {
                        result.setAttribute("failureScreenshot", captureFailureScreenshot(result.getName()));
                    } catch (IllegalStateException exception) {
                        if (result.getThrowable() != null) {
                            result.getThrowable().addSuppressed(exception);
                        }
                    }
                }
            } finally {
                closeBrowser(browser);
            }
        }
    }

    protected final WebDriver driver() {
        WebDriver browser = driver.get();
        if (browser == null) {
            throw new IllegalStateException("The browser has not been initialized for this test.");
        }
        return browser;
    }

    protected final Duration explicitWaitTimeout() {
        try {
            return Duration.ofSeconds(Long.parseLong(setting("explicit.wait.seconds", "20")));
        } catch (NumberFormatException exception) {
            throw new IllegalStateException("explicit.wait.seconds must be a positive integer.", exception);
        }
    }

    public final Path captureFailureScreenshot(String testName) {
        try {
            Path screenshotDirectory = Path.of("target", "screenshots");
            Files.createDirectories(screenshotDirectory);
            String safeTestName = testName.replaceAll("[^A-Za-z0-9._-]", "_");
            Path screenshotPath = screenshotDirectory.resolve(
                    safeTestName + "-" + LocalDateTime.now().format(SCREENSHOT_TIMESTAMP) + ".png");
            Path temporaryScreenshot = ((TakesScreenshot) driver()).getScreenshotAs(OutputType.FILE).toPath();
            return Files.copy(temporaryScreenshot, screenshotPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException | WebDriverException exception) {
            throw new IllegalStateException("Could not capture a screenshot for the failed test.", exception);
        }
    }

    private WebDriver createDriver() {
        String browserName = setting("browser", "chrome").toLowerCase(Locale.ROOT);
        boolean headless = Boolean.parseBoolean(setting("headless", "false"));
        return switch (browserName) {
            case "chrome" -> {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--window-size=1920,1080");
                if (headless) {
                    options.addArguments("--headless=new");
                }
                yield new ChromeDriver(options);
            }
            case "firefox" -> {
                FirefoxOptions options = new FirefoxOptions();
                if (headless) {
                    options.addArguments("-headless");
                }
                yield new FirefoxDriver(options);
            }
            case "edge" -> {
                EdgeOptions options = new EdgeOptions();
                options.addArguments("--window-size=1920,1080");
                if (headless) {
                    options.addArguments("--headless=new");
                }
                yield new EdgeDriver(options);
            }
            default -> throw new IllegalArgumentException(
                    "Unsupported browser '" + browserName + "'. Use chrome, firefox, or edge.");
        };
    }

    private String setting(String key, String defaultValue) {
        return System.getProperty(key, CONFIG.getProperty(key, defaultValue)).trim();
    }

    private void closeBrowser(WebDriver browser) {
        try {
            browser.quit();
        } catch (WebDriverException exception) {
            throw new IllegalStateException("Could not close the browser session.", exception);
        } finally {
            driver.remove();
        }
    }

    private static Properties loadConfiguration() {
        Properties properties = new Properties();
        try (InputStream input = BaseTest.class.getResourceAsStream("/config.properties")) {
            if (input == null) {
                throw new IllegalStateException("Could not find /config.properties on the test classpath.");
            }
            properties.load(input);
            return properties;
        } catch (IOException exception) {
            throw new IllegalStateException("Could not load the test configuration.", exception);
        }
    }
}