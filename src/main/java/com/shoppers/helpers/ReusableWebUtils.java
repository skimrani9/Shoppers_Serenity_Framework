package com.shoppers.helpers;

import com.shoppers.config.TimeoutConfig;
import com.shoppers.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Core Selenium interaction utilities shared across page objects and steps.
 */
public class ReusableWebUtils {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public ReusableWebUtils() {
        this.driver = DriverManager.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(TimeoutConfig.EXPLICIT_WAIT_SECONDS));
    }

    public ReusableWebUtils(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(TimeoutConfig.EXPLICIT_WAIT_SECONDS));
    }

    public void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    public void type(By locator, String text) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.clear();
        element.sendKeys(text);
    }

    public String getText(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
    }

    public boolean isDisplayed(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
    }

    public void navigateTo(String url) {
        driver.get(url);
    }
}
