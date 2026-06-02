package com.shoppers.hooks;

import com.shoppers.helpers.ScenarioContext;
import com.shoppers.helpers.SessionContext;
import com.shoppers.helpers.TestConfigLoader;
import com.shoppers.helpers.WebDriverSetup;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

/**
 * Cucumber hooks for scenario and suite lifecycle.
 */
public class TestSuiteHooks {

    private static final Logger LOG = LogManager.getLogger(TestSuiteHooks.class);

    @BeforeAll
    public static void beforeAll() throws IOException {
        WebDriverSetup.setupChromeDriver();
        String environment = System.getProperty("environment", "QA");
        TestConfigLoader.load(environment);
        String baseUrl = TestConfigLoader.get("base.url");
        if (baseUrl != null && !baseUrl.isBlank()) {
            System.setProperty("webdriver.base.url", baseUrl);
        }
        String headless = TestConfigLoader.get("headless", "false");
        System.setProperty("headless.mode", headless);
        LOG.info("Starting Shoppers login test suite — environment: {}, baseUrl: {}, headless: {}",
                environment, baseUrl, headless);
    }

    @AfterAll
    public static void afterAll() {
        SessionContext.clear();
        LOG.info("Completed Shoppers login test suite");
    }

    @Before(order = 0)
    public void beforeScenario(Scenario scenario) {
        WebDriverSetup.setupChromeDriver();
        System.setProperty("headless.mode", "false");
        LOG.info("Starting scenario: {}", scenario.getName());
    }

    @After
    public void afterScenario(Scenario scenario) {
        if (scenario.isFailed()) {
            LOG.warn("Scenario failed: {}", scenario.getName());
        }
        ScenarioContext.clear();
    }
}
