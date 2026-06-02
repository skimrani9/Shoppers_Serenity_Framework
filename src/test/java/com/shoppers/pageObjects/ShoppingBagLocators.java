package com.shoppers.pageObjects;

/**
 * Locators for Shopping Bag (Module 6).
 */
public final class ShoppingBagLocators {

    public static final String BAG_HEADER = "//*[contains(text(),'My Shopping Bag')]";
    public static final String EMPTY_BAG_MESSAGE = "//*[contains(@name,'empty-shopping-bag-image') or contains(text(),'empty')]";
    public static final String WANNA_SHOP_BUTTON = "//button[contains(.,'Wanna Shop') or contains(.,'Click Here')]";
    public static final String PROCEED_TO_CHECKOUT = "//button[contains(.,'Proceed To Checkout') or contains(.,'PROCEED TO CHECKOUT')]";
    public static final String REMOVE_BUTTON = "//button[contains(.,'Remove')]";
    public static final String CONFIRM_MODAL_YES = "//button[contains(.,'Yes') or contains(.,'Confirm')]";
    public static final String QTY_DROPDOWN = "//*[contains(text(),'Qty:')]/following::*[contains(@class,'dropdown')][1]";

    private ShoppingBagLocators() {
    }
}
