package com.shoppers.pageObjects;

/**
 * Locators for navbar navigation and category tabs (Module 2).
 */
public final class NavigationLocators {

    public static final String NAV_TABS = "//*[@aria-label='simple-tabs']";
    public static final String TAB_BY_LABEL = "//*[@id[starts-with(.,'simple-tab-')]]//span[contains(text(),'%s')]";
    public static final String MEGA_MENU_TOP_BRANDS = "//*[contains(text(),'Top Brands')]";
    public static final String MEGA_MENU_FIRST_LINK =
            "//*[contains(text(),'Top Brands')]/following::a[contains(@href,'/products?q=')][1]";
    public static final String SIGN_IN_HEADER = "//div[contains(text(),'Sign In')]";
    public static final String BAG_ICON = "//*[contains(@class,'MuiBadge-root')]//*[name()='svg']";
    public static final String MOBILE_MENU_BUTTON = "//header//button[contains(@class,'MuiIconButton-root')][1]";
    public static final String MOBILE_DRAWER = "//*[contains(@class,'MuiDrawer-paper')]";
    public static final String SHOPPERS_LOGO = "//*[contains(text(),'Shoppers')]";

    private NavigationLocators() {
    }
}
