package com.shoppers.helpers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Downloads and configures ChromeDriver before Serenity opens the browser.
 */
public final class WebDriverSetup {

    private static final Logger LOG = LogManager.getLogger(WebDriverSetup.class);
    private static volatile boolean initialized;

    static {
        setupChromeDriver();
    }

    private WebDriverSetup() {
    }

    public static synchronized void setupChromeDriver() {
        if (initialized) {
            return;
        }
        WebDriverManager chromeManager = WebDriverManager.chromedriver();
        chromeManager.setup();

        String driverPath = System.getProperty("webdriver.chrome.driver");
        if (driverPath == null || driverPath.isBlank()) {
            driverPath = chromeManager.getDownloadedDriverPath();
        }
        if (driverPath == null || driverPath.isBlank()) {
            throw new IllegalStateException(
                    "ChromeDriver path is empty after WebDriverManager setup. "
                            + "Install Google Chrome and run: mvn clean test-compile");
        }

        System.setProperty("webdriver.chrome.driver", driverPath);
        System.setProperty("webdriver.driver", "chrome");
        System.setProperty("headless.mode", "false");

        LOG.info("ChromeDriver ready: {}", driverPath);
        initialized = true;
    }
}
