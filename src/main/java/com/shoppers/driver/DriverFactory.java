package com.shoppers.driver;

import com.shoppers.config.TimeoutConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

/**
 * Creates WebDriver instances based on browser configuration.
 */
public final class DriverFactory {

    public WebDriver createDriver(String browser, boolean headless) {
        WebDriver driver = switch (normalize(browser)) {
            case "firefox" -> new FirefoxDriver(buildFirefoxOptions(headless));
            case "edge" -> new EdgeDriver(buildEdgeOptions(headless));
            default -> new ChromeDriver(buildChromeOptions(headless));
        };
        configureTimeouts(driver);
        return driver;
    }

    private static String normalize(String browser) {
        return browser == null ? "chrome" : browser.trim().toLowerCase();
    }

    private static ChromeOptions buildChromeOptions(boolean headless) {
        ChromeOptions options = new ChromeOptions();
        if (headless) {
            options.addArguments("--headless=new");
        }
        options.addArguments("--start-maximized");
        return options;
    }

    private static FirefoxOptions buildFirefoxOptions(boolean headless) {
        FirefoxOptions options = new FirefoxOptions();
        if (headless) {
            options.addArguments("-headless");
        }
        return options;
    }

    private static EdgeOptions buildEdgeOptions(boolean headless) {
        EdgeOptions options = new EdgeOptions();
        if (headless) {
            options.addArguments("--headless=new");
        }
        return options;
    }

    private static void configureTimeouts(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TimeoutConfig.IMPLICIT_WAIT_SECONDS));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TimeoutConfig.PAGE_LOAD_TIMEOUT_SECONDS));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(TimeoutConfig.SCRIPT_TIMEOUT_SECONDS));
    }
}
