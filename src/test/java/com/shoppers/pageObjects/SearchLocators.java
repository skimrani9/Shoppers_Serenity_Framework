package com.shoppers.pageObjects;

/**
 * Locators for navbar search bar and autocomplete (Module 3).
 */
public final class SearchLocators {

    public static final String SEARCH_INPUT = "//input[@id='free-solo']";
    public static final String SEARCH_LABEL =
            "//label[contains(text(),'Search for products, brands and more')]";
    public static final String SEARCH_SUGGESTIONS_LIST = "//ul[@role='listbox']";
    public static final String SEARCH_SUGGESTION_OPTION = "//ul[@role='listbox']//li";
    public static final String MOBILE_SEARCH_OPEN_BUTTON =
            "//header//button[contains(@class,'MuiIconButton-root') and .//*[name()='svg']" +
            " and not(@aria-label='show more') and not(@aria-label='open drawer')][1]";
    public static final String MOBILE_SEARCH_BACK_BUTTON =
            "//header//button[@edge='end']//*[name()='svg']/ancestor::button[1]";

    public static String suggestionOptionByKeyword(String keyword) {
        String lower = keyword.toLowerCase();
        return String.format(
                "//ul[@role='listbox']//li[contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), '%s')]",
                lower);
    }

    private SearchLocators() {
    }
}
