# Shoppers Serenity Framework — Guide for Test Automation

This document covers the **Shoppers Serenity BDD Framework**: architecture, standards, functional test coverage mapped to the Shoppers e-commerce app, and step-by-step instructions for adding new automated test cases.

**Related application docs** (in the e-commerce repo):

- [Application Functional Overview](../../dummy-ecommerce-microservices/docs/FUNCTIONAL_OVERVIEW.md)
- [Application Code Walkthrough](../../dummy-ecommerce-microservices/docs/README.md)

---

## 1. Framework Overview

| Item | Detail |
|------|--------|
| **Name** | Shoppers Serenity Framework |
| **Purpose** | UI test automation for the Shoppers e-commerce React application |
| **Stack** | Serenity BDD 5.3.8 · Cucumber 7.34 · TestNG 7.10 · Selenium 4.41 · Java 21 |
| **Pattern** | BDD (Gherkin) + Page Object Model + Serenity `@Step` reporting |
| **Browser** | Google Chrome (WebDriverManager auto-downloads ChromeDriver) |
| **Current scope** | **Login module only** — foundation for expanding to catalog, cart, checkout, etc. |

### Architecture

```
┌─────────────────────────────────────────────────────────────────┐
│  Gherkin Features (.feature)     ← Business-readable scenarios   │
└────────────────────────────┬────────────────────────────────────┘
                             │
┌────────────────────────────▼────────────────────────────────────┐
│  Step Definitions (*Steps.java)  ← Cucumber glue, @Steps inject  │
└────────────────────────────┬────────────────────────────────────┘
                             │
┌────────────────────────────▼────────────────────────────────────┐
│  Page Objects (*Page.java)       ← UI actions + verifications    │
│  Locators (*Locators.java)       ← XPath / selectors (separate)  │
└────────────────────────────┬────────────────────────────────────┘
                             │
┌────────────────────────────▼────────────────────────────────────┐
│  Serenity PageObject + WebDriver  ← Browser automation           │
└────────────────────────────┬────────────────────────────────────┘
                             │
┌────────────────────────────▼────────────────────────────────────┐
│  Shoppers React UI (base.url)    ← Application under test        │
└─────────────────────────────────────────────────────────────────┘

Supporting layer (src/main/java):
  TestConfigLoader · WebDriverSetup · ScenarioContext · SessionContext
  ReusableWebUtils · AssertionsUtils · FakerData · Excel/CSV helpers
```

### Execution flow

1. **TestNG** loads `testng.xml` → runs `ShoppersRunner`
2. **Cucumber** picks features matching tags (default `@login`)
3. **TestSuiteHooks `@BeforeAll`** — ChromeDriver setup, load env config
4. **Scenario runs** — steps call Page Objects via `@Steps`
5. **Serenity** captures steps, screenshots on failure
6. **`mvn verify`** → Serenity aggregate report at `target/site/serenity/index.html`

---

## 2. Project Structure

```
Shoppers_Serenity_Framework/
├── pom.xml                          # Maven deps, Serenity/Cucumber plugins
├── testng.xml                       # TestNG suite (ShoppersRunner)
├── local_run.ps1 / local_run.bat    # Main run scripts
├── run_login_positive.ps1           # Positive login only
├── docs/
│   ├── README.md                    # Quick start
│   ├── PROJECT_STRUCTURE.md         # Layout summary
│   └── FRAMEWORK_GUIDE.md           # This document
└── src/
    ├── main/java/com/shoppers/       # Reusable framework utilities
    │   ├── config/TimeoutConfig.java
    │   ├── constants/Constants.java
    │   ├── driver/DriverManager.java, DriverFactory.java
    │   ├── excelhelpers/CsvUtil, ExcelReader, ExcelWriter
    │   ├── helpers/ScenarioContext, SessionContext, FakerData,
    │   │            ReusableWebUtils, AssertionsUtils, DateUtils
    │   └── servicehelpers/ServiceResponse.java
    └── test/
        ├── java/com/shoppers/
        │   ├── runner/ShoppersRunner.java
        │   ├── hooks/TestSuiteHooks.java, TestNGDriverListener.java
        │   ├── pageObjects/HomePage, LoginPage, LoginLocators
        │   ├── stepdefinitions/LoginSteps.java
        │   └── helpers/TestConfigLoader, WebDriverSetup
        └── resources/
            ├── features/Login/login.feature
            ├── config/DevConfig, QAConfig, ProdConfig.properties
            ├── TestData/login_test_data.csv
            ├── serenity.conf, serenity.properties
            ├── cucumber.properties, log4j2.xml
```

### Layer responsibilities

| Layer | Location | Responsibility |
|-------|----------|----------------|
| **Features** | `src/test/resources/features/<Module>/` | Gherkin scenarios; tags; Examples tables |
| **Step definitions** | `src/test/java/.../stepdefinitions/` | Map Gherkin steps to Java; inject Page Objects with `@Steps` |
| **Page objects** | `src/test/java/.../pageObjects/` | UI interactions and assertions; `@Step` for Serenity reports |
| **Locators** | `src/test/java/.../pageObjects/*Locators.java` | XPath/CSS constants; no logic |
| **Hooks** | `src/test/java/.../hooks/` | Suite/scenario setup and teardown |
| **Config** | `src/test/resources/config/*Config.properties` | Per-environment URLs and browser settings |
| **Test data** | `src/test/resources/TestData/` | CSV/Excel for data-driven scenarios |
| **Framework utils** | `src/main/java/com/shoppers/` | Shared helpers (not test-specific) |

---

## 3. Framework Standards & Guidelines

### 3.1 Naming conventions

| Artifact | Convention | Example |
|----------|------------|---------|
| Feature file | `<module>.feature` in folder matching module | `features/Login/login.feature` |
| Feature title | `Feature: Shoppers <module> <action>` | `Feature: Shoppers user login` |
| Scenario | Descriptive business language | `Scenario: Successful login with valid credentials` |
| Tags | `@<module>` + `@positive` / `@negative` / `@smoke` | `@login @positive` |
| Step class | `<Module>Steps.java` | `LoginSteps.java` |
| Page object | `<Page>Page.java` | `LoginPage.java`, `ProductCatalogPage.java` |
| Locators class | `<Page>Locators.java` | `LoginLocators.java` |
| Test data file | `<module>_test_data.csv` | `login_test_data.csv` |

### 3.2 Page Object Model rules

1. **One page object per screen or major UI section** (Home, Login, Product Catalog, Shopping Bag, Checkout).
2. **Locators live in a separate `*Locators.java` class** — never hard-code XPath inside step definitions.
3. **Every user action or verification is a `@Step` method** — ensures readable Serenity reports.
4. **Page objects extend `net.serenitybdd.core.pages.PageObject`** and use `$(locator)` for elements.
5. **Use `waitUntilVisible()` / `waitUntilClickable()`** before interact — do not use raw `Thread.sleep`.
6. **Assertions belong in page objects** (e.g. `verifyDashboardDisplayed()`), not in step definitions.
7. **Step definitions stay thin** — delegate all UI logic to page objects.

**Good step definition:**

```java
@When("the shopper logs in with username {string} and password {string}")
public void theShopperLogsIn(String username, String password) {
    homePage.clickSignInFromHeader();
    loginPage.waitForSignInForm();
    loginPage.login(username, password);
}
```

**Avoid:** Selenium calls, locators, or assertions directly in step classes.

### 3.3 Gherkin (BDD) standards

| Rule | Detail |
|------|--------|
| **Given** | Preconditions / system state (e.g. on homepage) |
| **When** | User action (e.g. logs in, adds to bag) |
| **Then** | Observable outcome (e.g. dashboard visible, error shown) |
| **Background** | Shared Given steps for all scenarios in a feature |
| **Scenario Outline** | Use for data-driven tests with `Examples` table |
| **Language** | Business-facing: "the shopper", not "the user clicks button" |
| **No implementation detail** | Do not mention XPath, IDs, or class names in features |
| **One assertion focus per Then** | Prefer one clear outcome per Then step |

### 3.4 Tag strategy

| Tag | Purpose |
|-----|---------|
| `@login` | Login module (current default in `ShoppersRunner`) |
| `@positive` | Happy-path scenarios |
| `@negative` | Error / validation scenarios |
| `@smoke` | Critical path subset (recommended for CI) |
| `@regression` | Full regression suite (future) |

**Run by tag:**

```powershell
.\local_run.ps1 -Environment Dev -Tags "@login and @positive"
```

**PowerShell warning:** Do not use `-Dcucumber.filter.tags=...` unquoted — PowerShell parses `-Dcucumber` as its own flag. Use `-Dtags=` via `local_run.ps1` or quote: `"-Dtags=@login and @positive"`.

### 3.5 Test data standards

| Source | When to use |
|--------|-------------|
| **Inline in feature** (`Examples` table) | Small sets, readable in Gherkin |
| **CSV** (`TestData/*.csv`) | Larger datasets, shared across scenarios |
| **JavaFaker** (`FakerData.java`) | Dynamic signup/shipping data (unique email, address) |
| **Excel** (`ExcelReader`/`ExcelWriter`) | Complex multi-sheet data (future) |

Store credentials in CSV or config — **never commit real production passwords**.

### 3.6 Environment configuration

| File | Use |
|------|-----|
| `DevConfig.properties` | Local Docker / WSL app (`base.url`) |
| `QAConfig.properties` | QA/staging environment |
| `ProdConfig.properties` | Production smoke (read-only tests only) |

Key properties:

```properties
base.url=http://172.17.172.217:3000
login.path=/signin
browser=chrome
headless=false
```

Select environment: `-Denvironment=Dev` (loads `config/DevConfig.properties` via `TestConfigLoader`).

**WSL note:** Match `base.url` to the URL where the React app is reachable (often WSL IP, not `localhost`, on Windows — see e-commerce app README).

### 3.7 Context objects

| Class | Scope | Use |
|-------|-------|-----|
| `ScenarioContext` | Single scenario (cleared in `@After`) | Pass data between steps in one scenario (e.g. selected product ID) |
| `SessionContext` | Suite run (cleared in `@AfterAll`) | Share state across scenarios (e.g. auth token after login scenario) |

### 3.8 Waits and timeouts

Centralized in `TimeoutConfig.java`:

| Constant | Seconds |
|----------|---------|
| `IMPLICIT_WAIT_SECONDS` | 10 |
| `EXPLICIT_WAIT_SECONDS` | 30 |
| `PAGE_LOAD_TIMEOUT_SECONDS` | 60 |

Prefer Serenity `waitUntilVisible()` on page objects. Use `ReusableWebUtils` only when not using Serenity PageObject pattern.

### 3.9 Reporting

- **Serenity HTML report:** `target/site/serenity/index.html` (after `mvn verify`)
- **Screenshots:** `FOR_FAILURES` only (configured in `serenity.conf`)
- **Logs:** Log4j2 via `log4j2.xml`

---

## 4. Functional Overview — Test Coverage by App Module

This maps the [Shoppers application functional modules](../../dummy-ecommerce-microservices/docs/FUNCTIONAL_OVERVIEW.md) to automation status.

| App Module | App Route(s) | Automation Status | Feature Folder (planned) | Priority |
|------------|--------------|-------------------|--------------------------|----------|
| **Login / Sign In** | `/signin` | ✅ **Automated** | `features/Login/` | Done |
| **Home & Discovery** | `/` | ✅ **Automated** | `features/Home/` | Done |
| **Search & Suggestions** | Navbar search | ✅ **Automated** | `features/Search/` | Done |
| **Sign Up** | `/signup` | ⬜ Not started | `features/Signup/` | High |
| **Home & Discovery** | `/` | ⬜ Not started | `features/Home/` | High |
| **Navigation & Tabs** | Navbar | ⬜ Not started | `features/Navigation/` | Medium |
| **Search & Suggestions** | Navbar search | ⬜ Not started | `features/Search/` | High |
| **Product Catalog & Filters** | `/products?q=...` | ⬜ Not started | `features/ProductCatalog/` | High |
| **Product Details** | `/products/details?q=...` | ⬜ Not started | `features/ProductDetails/` | High |
| **Shopping Bag** | `/shopping-bag` | ⬜ Not started | `features/ShoppingBag/` | High |
| **Checkout & Shipping** | `/checkout` | ⬜ Not started | `features/Checkout/` | Medium |
| **Payment** | `/checkout` + Stripe | ⬜ Not started | `features/Payment/` | Medium (needs Stripe test key) |
| **Order Confirmation** | `/checkout/success-payment/:id` | ⬜ Not started | `features/Payment/` | Medium |

---

## 5. Current Automated Module — Login

### 5.1 Feature file

**Path:** `src/test/resources/features/Login/login.feature`

| Scenario | Tags | Description |
|----------|------|-------------|
| Successful login with valid credentials | `@login @positive` | Valid user → Sign Out visible (dashboard) |
| Login fails when username does not exist | `@login @negative` | Invalid user → error message |
| Login fails with blank password | `@login @negative` | Unknown user + empty password → error |
| Login with test data from examples | `@login` | Scenario Outline with Examples table |

### 5.2 Step definitions

**Path:** `LoginSteps.java`

| Step | Action |
|------|--------|
| `Given the shopper is on the Shoppers homepage` | Opens `base.url` |
| `When the shopper logs in with username "..." and password "..."` | Header Sign In → form → submit |
| `Then the shopper should see the dashboard` | Verifies Sign Out link visible |
| `Then the shopper should see a login error message` | Verifies error div visible |
| `Then the shopper should see "<expected_element>"` | Routes to dashboard or error check |

### 5.3 Page objects & locators

| Class | Responsibility |
|-------|----------------|
| `HomePage` | Open homepage; click Sign In in header |
| `LoginPage` | Enter credentials; submit; verify success/error |
| `LoginLocators` | XPath for username, password, buttons, Sign Out, error |

**Success indicator:** `Sign Out` text in navbar (`LoginLocators.SIGN_OUT`).

**Error indicator:** `Error: Username does not exist.` (`LoginLocators.LOGIN_ERROR`).

### 5.4 Test data

**CSV:** `TestData/login_test_data.csv`

```csv
testId,username,password,expectedElement
TC001,imran,1234567890,dashboard
TC002,invalid_user,wrong_pass,Error: Username does not exist.
```

**Prerequisite:** User `imran` must exist in the authentication service DB (create via Sign Up or seed before tests).

---

## 6. How to Add a New Test Module (Step-by-Step)

Use this checklist when automating a new app feature (e.g. Shopping Bag).

### Step 1 — Create the feature file

```
src/test/resources/features/ShoppingBag/shopping_bag.feature
```

```gherkin
@shoppingbag
Feature: Shoppers shopping bag
  As a shopper
  I want to manage items in my bag
  So that I can review my order before checkout

  Background:
    Given the shopper is on the Shoppers homepage

  @shoppingbag @positive @smoke
  Scenario: Add product to bag from product details
    When the shopper opens the first product from the catalog
    And the shopper adds 1 item to the bag
    Then the shopping bag should contain 1 item
```

### Step 2 — Create locators class

```java
// pageObjects/ShoppingBagLocators.java
public final class ShoppingBagLocators {
    public static final String BAG_ICON = "//...";
    public static final String BAG_ITEM_COUNT = "//...";
    private ShoppingBagLocators() {}
}
```

Inspect the React UI (browser DevTools) for stable locators. Prefer:

- `name` attributes (login uses `@name="username"`)
- Visible text with `contains(text(), "...")`
- Avoid brittle auto-generated class names

### Step 3 — Create page object

```java
public class ShoppingBagPage extends PageObject {

    @Step("Open shopping bag")
    public void openShoppingBag() {
        $(ShoppingBagLocators.BAG_ICON).waitUntilClickable().click();
    }

    @Step("Verify bag contains {0} item(s)")
    public void verifyItemCount(int count) {
        // assertion logic
    }
}
```

### Step 4 — Create step definitions

```java
public class ShoppingBagSteps {

    @Steps HomePage homePage;
    @Steps ShoppingBagPage shoppingBagPage;

    @When("the shopper adds {int} item to the bag")
    public void theShopperAddsItemToTheBag(int qty) {
        shoppingBagPage.addItem(qty);
    }

    @Then("the shopping bag should contain {int} item")
    public void theShoppingBagShouldContainItem(int count) {
        shoppingBagPage.verifyItemCount(count);
    }
}
```

### Step 5 — Register in runner (if new feature folder)

Update `ShoppersRunner.java`:

```java
@CucumberOptions(
    features = {"classpath:features/Login", "classpath:features/ShoppingBag"},
    glue = {"com.shoppers.stepdefinitions", "com.shoppers.hooks"},
    tags = "@login or @shoppingbag"
)
```

Or use a broader tag like `@smoke` across modules.

### Step 6 — Add test data (if needed)

Create `TestData/shopping_bag_test_data.csv` or use `Scenario Outline` Examples.

### Step 7 — Add config keys (if needed)

In `DevConfig.properties`:

```properties
products.path=/products?q=page=0,16
```

Load via `TestConfigLoader.get("products.path")`.

### Step 8 — Run and verify

```powershell
.\local_run.ps1 -Environment Dev -Tags "@shoppingbag"
```

Open `target/site/serenity/index.html` and confirm steps, screenshots, and pass/fail.

### Step 9 — Add run script (optional)

```powershell
# run_shopping_bag.ps1
& "$PSScriptRoot\local_run.ps1" -Environment Dev -Tags "@shoppingbag and @smoke"
```

---

## 7. Recommended Test Cases by Module (Backlog)

Use this as a backlog for expanding automation beyond login.

### Sign Up (`@signup`)

| ID | Scenario | Type |
|----|----------|------|
| SU-01 | Register with valid unique email | Positive |
| SU-02 | Duplicate username shows error | Negative |
| SU-03 | Duplicate email shows error | Negative |
| SU-04 | Redirect to sign-in after success | Positive |

**Data:** Use `FakerData.email()` for unique registrations.

### Home (`@home`)

| ID | Scenario | Type |
|----|----------|------|
| HM-01 | Home page loads carousel and brand sections | Positive |
| HM-02 | Click brand tile navigates to filtered products | Positive |

### Search (`@search`)

| ID | Scenario | Type |
|----|----------|------|
| SR-01 | Default suggestions appear on search focus | Positive |
| SR-02 | Type prefix shows matching suggestions | Positive |
| SR-03 | Select suggestion opens product listing | Positive |

### Product Catalog (`@catalog`)

| ID | Scenario | Type |
|----|----------|------|
| CT-01 | Default product listing loads | Positive |
| CT-02 | Apply gender filter updates results | Positive |
| CT-03 | Apply brand filter updates results | Positive |
| CT-04 | Sort by price low to high | Positive |
| CT-05 | Pagination shows next page | Positive |
| CT-06 | Clear all filters resets listing | Positive |

### Product Details (`@product`)

| ID | Scenario | Type |
|----|----------|------|
| PD-01 | Product details page shows name and price | Positive |
| PD-02 | Increase quantity updates selector | Positive |
| PD-03 | Add to bag from details page | Positive |

### Shopping Bag (`@shoppingbag`)

| ID | Scenario | Type |
|----|----------|------|
| SB-01 | Empty bag shows empty state message | Positive |
| SB-02 | Change item quantity updates total | Positive |
| SB-03 | Remove item with confirmation | Positive |
| SB-04 | Cart persists after page refresh (cookie) | Positive |

### Checkout (`@checkout`)

| ID | Scenario | Type |
|----|----------|------|
| CO-01 | Checkout blocked when bag is empty | Negative |
| CO-02 | Submit valid shipping address | Positive |
| CO-03 | Select shipping option updates delivery charge | Positive |

### Payment (`@payment`)

| ID | Scenario | Type |
|----|----------|------|
| PY-01 | Place order with Stripe test card 4242... | Positive |
| PY-02 | Success page shows order summary | Positive |
| PY-03 | Failed payment shows cancel page | Negative |

**Prerequisite:** `REACT_APP_STRIPE_PUBLISH_KEY` configured in app `.env`.

---

## 8. Prerequisites & Running Tests

### Prerequisites

| Requirement | Version |
|-------------|---------|
| JDK | 21+ |
| Maven | 3.9+ |
| Google Chrome | Latest (ChromeDriver via WebDriverManager) |
| Shoppers app | Running at `base.url` (Docker Compose recommended) |

### Quick run

```powershell
# All @login scenarios
.\local_run.ps1

# Positive login only
.\run_login_positive.ps1

# Custom environment and tags
.\local_run.ps1 -Environment Dev -Tags "@login and @positive"
```

```bat
local_run.bat
run_login_positive.bat
```

### Maven (CMD or PowerShell with quoted -D)

```bat
mvn clean verify -Denvironment=Dev -Dtags=@login
```

### Report

After `mvn verify`:

```
target/site/serenity/index.html
```

---

## 9. Troubleshooting

| Issue | Fix |
|-------|-----|
| `ChromeDriver` instantiation failed | Install Chrome; run `mvn clean test-compile`; check `WebDriverSetup` logs |
| Tests timeout on homepage | Verify `base.url` in `DevConfig.properties`; ensure app is up (`Compiled successfully!` in React container) |
| `localhost:3000` fails on Windows+WSL | Use WSL IP in `base.url` (see e-commerce README) |
| PowerShell `-Dcucumber` error | Use `local_run.ps1` or quote: `"-Dtags=@login"` |
| Login positive fails | Ensure user exists in auth DB; dev profile resets users on auth service restart — re-register `imran` |
| Element not found | Update locators in `*Locators.java`; Material-UI may render different text casing (SIGN IN vs Sign In) |

---

## 10. CI Integration (Future)

Recommended pipeline steps:

1. Start Shoppers app (Docker Compose)
2. Wait for health / React compile
3. `mvn clean verify -Denvironment=QA -Dtags="@smoke" -Dheadless.mode=true`
4. Publish `target/site/serenity/` as build artifact
5. Fail build on test failures (`maven-failsafe-plugin verify` goal)

---

## 11. Glossary

| Term | Meaning |
|------|---------|
| **BDD** | Behavior-Driven Development — tests written in Gherkin |
| **Glue code** | Step definition classes linked to feature steps |
| **Page Object** | Class encapsulating a page's elements and actions |
| **Serenity `@Step`** | Annotated method appearing as a line in the living documentation report |
| **Tag** | Cucumber label for filtering scenarios (`@login`, `@positive`) |
| **ScenarioContext** | Thread-local store for data within one scenario |
| **base.url** | Application entry URL from environment config |

---

## Related Documentation

- [Quick Start](./README.md)
- [Project Structure](./PROJECT_STRUCTURE.md)
- [Shoppers App — Functional Overview](../../dummy-ecommerce-microservices/docs/FUNCTIONAL_OVERVIEW.md)
- [Shoppers App — Setup & Docker](../../dummy-ecommerce-microservices/README.md)
