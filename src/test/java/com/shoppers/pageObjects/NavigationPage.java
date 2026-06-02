package com.shoppers.pageObjects;

import com.shoppers.config.TimeoutConfig;
import com.shoppers.helpers.TestConfigLoader;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.core.pages.PageObject;

import java.time.Duration;

/**
 * Navbar navigation — gender tabs, mega-menu, mobile menu, bag shortcut.
 */
public class NavigationPage extends PageObject {

    @Step("Open homepage for desktop navigation")
    public void openHomePageForDesktopNavigation() {
        String baseUrl = TestConfigLoader.get("base.url");
        openUrl(baseUrl.endsWith("/") ? baseUrl : baseUrl + "/");
        getDriver().manage().window().setSize(new org.openqa.selenium.Dimension(1200, 900));
        waitABit(2000);
        $(NavigationLocators.NAV_TABS).withTimeoutOf(Duration.ofSeconds(TimeoutConfig.HOME_PAGE_LOAD_SECONDS))
                .waitUntilVisible();
    }

    @Step("Verify navbar gender tabs are displayed")
    public void verifyNavbarTabsDisplayed() {
        $(NavigationLocators.NAV_TABS).waitUntilVisible();
        $(String.format(NavigationLocators.TAB_BY_LABEL, "MEN")).waitUntilVisible();
        $(String.format(NavigationLocators.TAB_BY_LABEL, "WOMEN")).waitUntilVisible();
    }

    @Step("Hover navbar tab {0}")
    public void hoverNavbarTab(String tabLabel) {
        org.openqa.selenium.WebElement tab = getDriver().findElement(
                org.openqa.selenium.By.xpath(String.format(NavigationLocators.TAB_BY_LABEL, tabLabel)));
        new org.openqa.selenium.interactions.Actions(getDriver()).moveToElement(tab).perform();
        waitABit(800);
    }

    @Step("Verify mega-menu panel is displayed")
    public void verifyMegaMenuDisplayed() {
        $(NavigationLocators.MEGA_MENU_TOP_BRANDS).waitUntilVisible();
    }

    @Step("Click first brand link in mega-menu")
    public void clickFirstBrandInMegaMenu() {
        $(NavigationLocators.MEGA_MENU_FIRST_LINK).waitUntilClickable().click();
        waitABit(1000);
    }

    @Step("Verify Sign In link is visible in navbar")
    public void verifySignInLinkVisible() {
        $(NavigationLocators.SIGN_IN_HEADER).waitUntilVisible();
    }

    @Step("Verify shopping bag icon is visible in navbar")
    public void verifyBagIconVisible() {
        $(NavigationLocators.BAG_ICON).waitUntilVisible();
    }

    @Step("Open mobile navigation menu")
    public void openMobileNavigationMenu() {
        getDriver().manage().window().setSize(new org.openqa.selenium.Dimension(375, 812));
        waitABit(1000);
        openHomePageForMobile();
        if ($(NavigationLocators.MOBILE_MENU_BUTTON).isPresent()) {
            $(NavigationLocators.MOBILE_MENU_BUTTON).waitUntilClickable().click();
            waitABit(500);
        }
    }

    @Step("Open homepage at mobile viewport")
    public void openHomePageForMobile() {
        String baseUrl = TestConfigLoader.get("base.url");
        openUrl(baseUrl.endsWith("/") ? baseUrl : baseUrl + "/");
        getDriver().manage().window().setSize(new org.openqa.selenium.Dimension(375, 812));
        waitABit(1500);
    }

    @Step("Verify mobile navigation drawer is displayed")
    public void verifyMobileDrawerDisplayed() {
        $(NavigationLocators.MOBILE_DRAWER).waitUntilVisible();
    }
}
