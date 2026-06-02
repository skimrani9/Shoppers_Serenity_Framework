package com.shoppers.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

/**
 * TestNG + Cucumber entry point for Serenity BDD execution.
 * Referenced by testng.xml for parallel test runs.
 */
@CucumberOptions(
        features = "classpath:features/Login",
        glue = {"com.shoppers.stepdefinitions", "com.shoppers.hooks"},
        plugin = {
                "pretty",
                "net.serenitybdd.cucumber.core.plugin.SerenityReporterParallel"
        },
        tags = "@login"
)
public class ShoppersRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
