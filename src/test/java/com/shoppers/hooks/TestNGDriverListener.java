package com.shoppers.hooks;

import com.shoppers.helpers.WebDriverSetup;
import org.testng.ISuite;
import org.testng.ISuiteListener;

/**
 * Runs ChromeDriver setup before any TestNG / Cucumber tests start.
 */
public class TestNGDriverListener implements ISuiteListener {

    @Override
    public void onStart(ISuite suite) {
        WebDriverSetup.setupChromeDriver();
        System.setProperty("headless.mode", "false");
    }
}
