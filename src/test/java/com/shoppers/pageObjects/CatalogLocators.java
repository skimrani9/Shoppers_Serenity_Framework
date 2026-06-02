package com.shoppers.pageObjects;

/**
 * Locators for Product Catalog & Filtering (Module 4).
 */
public final class CatalogLocators {

    public static final String PRODUCT_GRID_LINK = "//a[contains(@href,'/products/details')]";
    public static final String CLEAR_ALL_BUTTON = "//button[contains(.,'CLEAR ALL')]";
    public static final String FILTER_CHIP = "//*[contains(@class,'MuiChip-root')]";
    public static final String PAGINATION = "//nav[contains(@class,'MuiPagination-root')]";
    public static final String PAGINATION_PAGE_TWO = "//nav[contains(@class,'MuiPagination-root')]//button[text()='2']";
    public static final String SORT_DROPDOWN = "//*[contains(text(),'Sort by:')]";
    public static final String NO_MATCHES = "//*[contains(text(),'No matches found')]";

    public static String genderRadioLabel(String genderLabel) {
        return String.format("//label[contains(.,'%s')]", genderLabel);
    }

    public static String brandCheckboxLabel(String brandName) {
        return String.format("//label[contains(.,'%s')]", brandName);
    }

    private CatalogLocators() {
    }
}
