package com.shoppers.stepdefinitions;

import com.shoppers.pageObjects.NavigationPage;
import com.shoppers.pageObjects.ProductCatalogPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Steps;

/**
 * Navigation & Category Tabs — E2E UI steps (Module 2).
 */
public class NavigationUiSteps {

    @Steps
    NavigationPage navigationPage;

    @Steps
    ProductCatalogPage productCatalogPage;

    @Given("the shopper opens the homepage for desktop navigation")
    public void theShopperOpensTheHomepageForDesktopNavigation() {
        navigationPage.openHomePageForDesktopNavigation();
    }

    @Then("navbar gender tabs should be displayed")
    public void navbarGenderTabsShouldBeDisplayed() {
        navigationPage.verifyNavbarTabsDisplayed();
    }

    @When("the shopper hovers the {string} navbar tab")
    public void theShopperHoversTheNavbarTab(String tabLabel) {
        navigationPage.hoverNavbarTab(tabLabel);
    }

    @Then("the category mega-menu should be displayed")
    public void theCategoryMegaMenuShouldBeDisplayed() {
        navigationPage.verifyMegaMenuDisplayed();
    }

    @When("the shopper selects the first brand from the mega-menu")
    public void theShopperSelectsTheFirstBrandFromTheMegaMenu() {
        navigationPage.clickFirstBrandInMegaMenu();
    }

    @Then("the shopper should land on a filtered product catalog")
    public void theShopperShouldLandOnAFilteredProductCatalog() {
        productCatalogPage.verifyProductCatalogPageDisplayed();
        productCatalogPage.verifyUrlContainsBrandFilter();
    }

    @Then("Sign In should be visible in the navbar")
    public void signInShouldBeVisibleInTheNavbar() {
        navigationPage.verifySignInLinkVisible();
    }

    @Then("the shopping bag icon should be visible in the navbar")
    public void theShoppingBagIconShouldBeVisibleInTheNavbar() {
        navigationPage.verifyBagIconVisible();
    }

    @When("the shopper opens the mobile navigation menu")
    public void theShopperOpensTheMobileNavigationMenu() {
        navigationPage.openMobileNavigationMenu();
    }

    @Then("the mobile navigation drawer should be displayed")
    public void theMobileNavigationDrawerShouldBeDisplayed() {
        navigationPage.verifyMobileDrawerDisplayed();
    }
}
