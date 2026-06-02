package com.shoppers.helpers;

import org.openqa.selenium.WebElement;

import java.util.Objects;

/**
 * Lightweight assertion helpers for UI validations.
 */
public final class AssertionsUtils {

    private AssertionsUtils() {
    }

    public static void assertTextEquals(String actual, String expected, String message) {
        if (!Objects.equals(actual, expected)) {
            throw new AssertionError(message + " — expected: '" + expected + "', actual: '" + actual + "'");
        }
    }

    public static void assertElementDisplayed(WebElement element, String message) {
        if (element == null || !element.isDisplayed()) {
            throw new AssertionError(message);
        }
    }

    public static void assertTrue(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError(message);
        }
    }
}
