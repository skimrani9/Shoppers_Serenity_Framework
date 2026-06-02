# Shoppers Serenity BDD Framework

Maven-based UI automation using **Serenity BDD**, **Cucumber**, and **TestNG** — currently scoped to **login** only.

## Prerequisites

- JDK 21+
- Maven 3.9+
- Google Chrome installed (ChromeDriver is auto-downloaded via WebDriverManager before tests run)

### ChromeDriver errors

If you see `Could not instantiate class org.openqa.selenium.chrome.ChromeDriver`:

1. Ensure Chrome is installed (default path: `C:\Program Files\Google\Chrome\Application\chrome.exe`).
2. Run `mvn clean test-compile` after pulling latest changes (fixes Selenium 4.27 / 4.41 version mismatch).
3. First run downloads ChromeDriver via `WebDriverSetup` in `TestSuiteHooks`.

## Quick start

1. Update `src/test/resources/config/*Config.properties` with your login page URL (`base.url`).
2. Add locators and assertions in `LoginPage.java`.
3. Run login tests:

   **CMD / double-click:** `local_run.bat`

   **PowerShell:**

   ```powershell
   .\local_run.ps1
   ```

   Positive login only:

   ```powershell
   .\run_login_positive.ps1
   ```

4. Open the Serenity report at `target/site/serenity/index.html`.

## Tags

Default tag: `@login`

**PowerShell** — do **not** use `-Dcucumber.filter.tags=...` on the command line; PowerShell treats `-Dcucumber` as its own parameter. Use one of these:

```powershell
# Recommended: project script
.\run_login_positive.ps1

# Or --% (stop parsing) so Maven receives -D flags correctly
mvn --% clean verify -Denvironment=Dev -Dtags=@login and @positive

# Or quote the whole -D property (not only the value)
mvn clean verify "-Denvironment=Dev" "-Dtags=@login and @positive"
```

**CMD:**

```bat
run_login_positive.bat
```

## Test data

Login credentials and expected results: `src/test/resources/TestData/login_test_data.csv`

Valid test credentials are configured in `login.feature` and `login_test_data.csv` (username: `imran`).

## Application URL

Default: `http://172.17.172.217:3000` (configured in `DevConfig.properties` and `QAConfig.properties`)
