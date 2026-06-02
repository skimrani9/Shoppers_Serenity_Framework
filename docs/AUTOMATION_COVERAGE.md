# Full Application Test Automation Coverage

Automated test suites for all Shoppers application modules (Serenity BDD + Cucumber + TestNG).

**Skipped (already implemented):** Home & Discovery (`@home`), Search & Suggestions (`@search`), Product Details (`@product`)

## Module matrix

| # | Module | Tag | Scenarios | Run script |
|---|--------|-----|-----------|------------|
| 1 | Home & Discovery | `@home` | 21 | `run_home.ps1` |
| 2 | Navigation & Category Tabs | `@navigation` | 6 | `run_navigation.ps1` |
| 3 | Search & Suggestions | `@search` | 19 | `run_search.ps1` |
| 4 | Product Catalog & Filtering | `@catalog` | 8 | `run_catalog.ps1` |
| 5 | Product Details | `@product` | 8 | `run_product.ps1` |
| 6 | Shopping Bag | `@bag` | 6 | `run_bag.ps1` |
| 7 | User Authentication | `@login` `@auth` `@signup` | 4+5+1 | `run_login_*` / `run_auth.ps1` |
| 8 | Checkout & Shipping | `@checkout` | 3 | `run_checkout.ps1` |
| 9 | Payment & Order Confirmation | `@payment` | 2 | `run_payment.ps1` |

**New modules in this delivery:** 2, 4, 6, 7 (auth API + signup), 8, 9 — **32 scenarios**

**Grand total (all modules):** ~88 scenarios

## Run everything

```powershell
cd ecommerce_app_test_script\Shoppers_Serenity_Framework
.\run_all.ps1
```

## Layer tags (all modules)

| Layer | Tags |
|-------|------|
| API / Integration | `@api @integration` |
| Multiservice | `@multiservice @integration` |
| E2E / Functional | `@e2e @functional` |
| Smoke | `@smoke` |

## Services & ports (Dev)

| Service | Port | Modules |
|---------|------|---------|
| React UI | 3000 | All UI tests |
| Common Data | 9000 | Home, Navigation, Catalog, Product, Bag |
| Search Suggestion | 10000 | Search |
| Authentication | 7000 | Login, Auth, Signup |
| Payment | 9050 | Payment |

## Config (`DevConfig.properties`)

```properties
base.url=http://<host>:3000
common.data.service.url=http://<host>:9000
search.suggestion.service.url=http://<host>:10000
authentication.service.url=http://<host>:7000
payment.service.url=http://<host>:9050
products.default.query=page=0,16
login.username=imran
login.password=1234567890
```

## Module docs

| Module | Doc |
|--------|-----|
| Home | [HOME_MODULE_TESTS.md](HOME_MODULE_TESTS.md) |
| Search | [SEARCH_MODULE_TESTS.md](SEARCH_MODULE_TESTS.md) |
| Product Details | [PRODUCT_DETAILS_MODULE_TESTS.md](PRODUCT_DETAILS_MODULE_TESTS.md) |
| All modules | [AUTOMATION_COVERAGE.md](AUTOMATION_COVERAGE.md) (this file) |

Report: `target/site/serenity/index.html`
