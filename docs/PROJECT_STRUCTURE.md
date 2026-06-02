# Shoppers Serenity Framework — Project Structure

Serenity BDD + Cucumber + TestNG automation framework focused on **login** for the Shoppers ecommerce application.

## Root layout

```
Shoppers_Serenity_Framework/
├── pom.xml
├── testng.xml
├── global.properties
├── local_run.bat
├── docs/
└── src/
    ├── main/java/com/shoppers/     # Reusable framework code
    └── test/
        ├── java/com/shoppers/
        │   ├── runner/              ShoppersRunner
        │   ├── hooks/               TestSuiteHooks
        │   ├── pageObjects/         LoginPage
        │   ├── stepdefinitions/     LoginSteps
        │   └── helpers/             TestConfigLoader
        └── resources/
            ├── features/Login/      login.feature
            ├── config/              Dev, QA, Prod
            └── TestData/            login_test_data.csv
```

## `src/test` — login scope only

| Component | Purpose |
|-----------|---------|
| `features/Login/login.feature` | Gherkin login scenarios (`@login`) |
| `LoginPage.java` | Serenity page object for login UI |
| `LoginSteps.java` | Cucumber glue for login steps |
| `login_test_data.csv` | Data-driven login test data |

## Running tests

```bat
local_run.bat
```

Or:

```bat
mvn clean verify -Denvironment=QA -Dcucumber.filter.tags=@login
```

Serenity HTML report: `target/site/serenity/index.html`
