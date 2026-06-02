package com.shoppers.pageObjects;

/**
 * XPath/CSS locators for Home & Discovery UI (Module 1).
 */
public final class HomeDiscoveryLocators {

    public static final String HOME_LOADING_TEXT =
            "//*[contains(text(),'Loading home page data')]";
    public static final String TOP_BRANDS_HEADING =
            "//*[contains(text(),'#Shop Top Brands')]";
    public static final String TOP_CATEGORIES_HEADING =
            "//*[contains(text(),'#Shop Top Categories')]";
    public static final String CAROUSEL_CONTAINER =
            "//div[contains(@class,'swiper-container')]";
    public static final String CAROUSEL_IMAGES =
            "//div[contains(@class,'swiper-container')]//img";
    public static final String BRAND_TILES =
            "//*[contains(text(),'#Shop Top Brands')]/following::img[@title]";
    public static final String CATEGORY_TILES =
            "//*[contains(text(),'#Shop Top Categories')]/following::img[@title]";
    public static final String HOME_MENU_ICONS =
            "//img[contains(@alt,'circular-icon')]";
    public static final String SEARCH_INPUT =
            "//input[@id='free-solo']";
    public static final String SEARCH_LABEL =
            "//label[contains(text(),'Search for products, brands and more')]";
    public static final String SEARCH_SUGGESTIONS_LIST =
            "//ul[@role='listbox']";
    public static final String SEARCH_SUGGESTION_OPTION =
            "//ul[@role='listbox']//li";

    public static String brandTileByTitle(String brandTitle) {
        return String.format(
                "//*[contains(text(),'#Shop Top Brands')]/following::img[@title='%s']/ancestor::a[1]",
                brandTitle);
    }

    public static String categoryTileByTitle(String categoryTitle) {
        return String.format(
                "//*[contains(text(),'#Shop Top Categories')]/following::img[@title='%s']/ancestor::a[1]",
                categoryTitle);
    }

    private HomeDiscoveryLocators() {
    }
}
