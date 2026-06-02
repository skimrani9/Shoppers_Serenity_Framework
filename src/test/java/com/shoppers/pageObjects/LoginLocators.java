package com.shoppers.pageObjects;

/**
 * XPath locators for Shoppers login flow (provided for automation).
 */
public final class LoginLocators {

    public static final String SIGN_IN_HEADER = "//div[contains(text(),\"Sign In\")]";
    public static final String USERNAME_INPUT = "//input[@name=\"username\"]";
    public static final String PASSWORD_INPUT = "//input[@name=\"password\"]";
    /** Submit button on sign-in form (scoped after password field; supports SIGN IN label on UI). */
    public static final String SIGN_IN_BUTTON =
            "//input[@name=\"password\"]/following::span[contains(text(),\"Sign In\") or contains(text(),\"SIGN IN\")][1]";
    public static final String LOGIN_ERROR = "//div[contains(text(),\"Error: Username does not exist.\")]";
    public static final String SIGN_OUT = "//div[contains(text(),\"Sign Out\")]";

    private LoginLocators() {
    }
}
