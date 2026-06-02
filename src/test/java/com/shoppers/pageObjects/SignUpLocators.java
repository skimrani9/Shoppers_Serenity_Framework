package com.shoppers.pageObjects;

/**
 * Locators for Sign Up (Module 7).
 */
public final class SignUpLocators {

    public static final String FIRST_NAME = "//input[@name='firstName']";
    public static final String LAST_NAME = "//input[@name='lastName']";
    public static final String USERNAME = "//input[@name='username']";
    public static final String EMAIL = "//input[@name='email']";
    public static final String PASSWORD = "//input[@name='password']";
    public static final String CONFIRM_PASSWORD = "//input[@name='confirmPassword']";
    public static final String TERMS_CHECKBOX = "//input[@name='termsCheckbox'] | //input[@type='checkbox']";
    public static final String SIGN_UP_BUTTON = "//button[@type='submit' and contains(.,'Sign Up')]";
    public static final String SIGN_UP_LINK = "//a[contains(@href,'/signup') or contains(.,'Sign up')]";
    public static final String SIGN_IN_LINK = "//a[contains(@href,'/signin') or contains(.,'Sign in')]";

    private SignUpLocators() {
    }
}
