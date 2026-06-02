package com.shoppers.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

/**
 * TestNG + Cucumber entry point for Serenity BDD execution.
 * Referenced by testng.xml for parallel test runs.
 */
@CucumberOptions(
        features = {
                "classpath:features/Login",
                "classpath:features/Home",
                "classpath:features/Navigation",
                "classpath:features/Search",
                "classpath:features/Catalog",
                "classpath:features/ProductDetails",
                "classpath:features/ShoppingBag",
                "classpath:features/Auth",
                "classpath:features/Checkout",
                "classpath:features/Payment"
        },
        glue = {"com.shoppers.stepdefinitions", "com.shoppers.hooks"},
        plugin = {
                "pretty",
                "net.serenitybdd.cucumber.core.plugin.SerenityReporterParallel"
        },
        tags = "@login or @home or @navigation or @search or @catalog or @product "
                + "or @bag or @auth or @signup or @checkout or @payment"
)
public class ShoppersRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
