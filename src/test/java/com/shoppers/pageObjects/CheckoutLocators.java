package com.shoppers.pageObjects;

/**
 * Locators for Checkout & Shipping (Module 8).
 */
public final class CheckoutLocators {

    public static final String SHIPPING_ADDRESS_HEADER = "//*[contains(text(),'Shipping Address')]";
    public static final String FIRST_NAME = "//input[@name='firstName']";
    public static final String LAST_NAME = "//input[@name='lastName']";
    public static final String EMAIL = "//input[@name='email']";
    public static final String ADDRESS_LINE1 = "//input[@name='addressLine1']";
    public static final String ZIP_CODE = "//input[@name='zipCode']";
    public static final String CITY = "//input[@name='city']";
    public static final String PHONE = "//input[@name='phoneNumber']";
    public static final String STATE_DROPDOWN = "//*[@name='stateCode']";
    public static final String CONTINUE_BUTTON = "//button[contains(.,'CONTINUE')]";
    public static final String SHIPPING_OPTIONS_HEADER = "//*[contains(text(),'Shipping Options')]";
    public static final String FREE_SHIPPING_RADIO = "//label[contains(.,'Everyday Free')]";
    public static final String PLACE_ORDER_BUTTON = "//button[contains(.,'PLACE ORDER')]";
    public static final String SHIPPING_SUMMARY = "//*[contains(@class,'SummaryCard') or contains(text(),'Edit')]";

    private CheckoutLocators() {
    }
}
