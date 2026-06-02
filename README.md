# Shoppers Serenity Framework

Serenity BDD + Cucumber + TestNG automation framework for the **Shoppers** ecommerce web application.

**Current scope:** Login (UI) automation.

## Documentation

- `docs/README.md`: setup, prerequisites, common run commands
- `docs/PROJECT_STRUCTURE.md`: project structure (what goes where)
- `docs/FRAMEWORK_GUIDE.md`: framework standards and how to add new test cases

## Tech stack

- **Language**: Java 21
- **BDD**: Cucumber
- **Framework**: Serenity BDD
- **Runner**: TestNG (via `AbstractTestNGCucumberTests`)
- **Build tool**: Maven
- **Browser**: Google Chrome

## Project structure (high level)

See `docs/PROJECT_STRUCTURE.md`. Key files/folders:

- **Features**: `src/test/resources/features/`
- **Step definitions**: `src/test/java/com/shoppers/stepdefinitions/`
- **Page objects & locators**: `src/test/java/com/shoppers/pageObjects/`
- **Environment config**: `src/test/resources/config/` (e.g. `DevConfig.properties`)
- **Test data**: `src/test/resources/TestData/`

## Prerequisites

- JDK 21+
- Maven 3.9+
- Google Chrome installed

ChromeDriver is automatically downloaded/configured before tests run (WebDriverManager).

## Configuration

### Environment config

Update these files as per your environment:

- `src/test/resources/config/DevConfig.properties`
- `src/test/resources/config/QAConfig.properties`
- `src/test/resources/config/ProdConfig.properties`

Important properties:

- **base URL**: `base.url=http://172.17.172.217:3000`
- **headless**: `headless=false` (headed browser)
- **login path**: `login.path=/signin`

### Test data

Login test data:

- `src/test/resources/TestData/login_test_data.csv`

The positive login scenario uses the valid credentials configured in:

- `src/test/resources/features/Login/login.feature`
- `src/test/resources/TestData/login_test_data.csv`

## How to run tests

### Recommended (PowerShell)

Run the whole login feature (`@login`):

```powershell
.\run_login_feature.ps1
```

Run only the positive scenario (`@positive`):

```powershell
.\run_login_positive.ps1
```

Run all login tests (default tag `@login`) with parameters:

```powershell
.\local_run.ps1 -Environment Dev -Tags "@login" -Browser chrome
```

### Recommended (CMD / batch)

Run the whole login feature:

```bat
run_login_feature.bat
```

Run only the positive scenario:

```bat
run_login_positive.bat
```

### Maven (PowerShell-safe commands)

PowerShell can mis-parse `-D...` arguments when they contain spaces. In PowerShell, **quote the entire `-D` argument**:

Run all login scenarios:

```powershell
mvn clean verify "-Denvironment=Dev" "-Dtags=@login"
```

Run only positive login:

```powershell
mvn clean verify "-Denvironment=Dev" "-Dtags=@login and @positive"
```

## Run tests feature-wise (pattern)

This project currently includes a login feature:

- `src/test/resources/features/Login/login.feature`

Feature-wise execution is done via **tags**:

- Use `@login` to run the full login feature file
- Use `@positive` / `@negative` to run subsets

If you add a new feature folder (example `features/Checkout/checkout.feature`), follow the same pattern:

1. Add a tag like `@checkout` at top of the feature file
2. Add a script like `run_checkout_feature.ps1` that calls `local_run.ps1 -Tags "@checkout"`

## Reports

After execution, Serenity generates:

- **Interactive report**: `target/site/serenity/index.html`

## Headed vs Headless

- Default is **headed** (visible browser).
- To run headless, use:

```powershell
mvn clean verify "-Denvironment=Dev" "-Dtags=@login" "-Dheadless.mode=true"
```

## Troubleshooting

### Browser does not open / ChromeDriver errors

If you see `Could not instantiate class org.openqa.selenium.chrome.ChromeDriver`:

- Confirm Chrome is installed (commonly: `C:\Program Files\Google\Chrome\Application\chrome.exe`)
- Run a clean build to re-download the driver:

```powershell
mvn clean test-compile
```

### PowerShell tag command fails with “Unknown lifecycle phase”

Don’t use `-Dcucumber.filter.tags=...` directly in PowerShell.

Use `-Dtags=...` and quote the whole argument, or use the provided scripts:

- `run_login_feature.ps1`
- `run_login_positive.ps1`
- `local_run.ps1`
