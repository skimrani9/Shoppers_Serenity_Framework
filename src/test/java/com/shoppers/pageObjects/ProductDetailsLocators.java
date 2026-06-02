package com.shoppers.pageObjects;

/**
 * Locators for Product Details page (Module 5).
 */
public final class ProductDetailsLocators {

    public static final String DETAILS_PATH_FRAGMENT = "/products/details";
    public static final String PRODUCT_ID_PARAM = "product_id=";
    public static final String PRODUCT_IMAGE =
            "//a[contains(@href,'/products/details')]/following::img[@title][1] | " +
            "//img[@title and contains(@style,'height')]";
    public static final String PRODUCT_IMAGE_BY_NAME =
            "//img[@title='%s' or @alt='%s']";
    public static final String QTY_LABEL = "//*[contains(text(),'Qty:')]";
    public static final String QTY_INCREASE_BUTTON =
            "//button[contains(.,'ADD TO BAG')]/preceding::button[contains(@class,'MuiButton-outlined')][1]";
    public static final String QTY_DECREASE_BUTTON =
            "//button[contains(.,'ADD TO BAG')]/preceding::button[contains(@class,'MuiButton-outlined')][2]";
    public static final String ADD_TO_BAG_BUTTON = "//button[contains(.,'ADD TO BAG')]";
    public static final String PROCEED_TO_BAG_BUTTON = "//button[contains(.,'PROCEED TO BAG')]";
    public static final String BREADCRUMB_NAV = "//*[@aria-label='breadcrumb']";
    public static final String BREADCRUMB_HOME = "//*[@aria-label='breadcrumb']//a[contains(.,'Home')]";
    public static final String BREADCRUMB_PRODUCTS = "//*[@aria-label='breadcrumb']//a[contains(.,'Products')]";
    public static final String INCLUSIVE_TAXES_TEXT = "//*[contains(text(),'inclusive of all taxes')]";
    public static final String BAG_BADGE_COUNT = "//span[contains(@class,'MuiBadge-badge')]";

    public static String productNameText(String name) {
        return String.format("//*[contains(text(),'%s')]", name);
    }

    public static String productPriceText(String price) {
        return String.format("//*[contains(text(),'$ %s') or contains(text(),'$%s')]", price, price);
    }

    private ProductDetailsLocators() {
    }
}
