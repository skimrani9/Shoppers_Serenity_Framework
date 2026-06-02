# Home & Discovery — Automated Test Suite

Test automation for **Module 1 — Home & Discovery** from the [application functional overview](../../dummy-ecommerce-microservices/docs/FUNCTIONAL_OVERVIEW.md).

## Test layers

| Layer | Tag | Feature files | Count |
|-------|-----|---------------|-------|
| **API / Integration** | `@home @api @integration` | `home_api.feature`, `home_search_api.feature` | 4 scenarios |
| **Multiservice** | `@home @multiservice @integration` | `home_multiservice.feature` | 2 scenarios |
| **E2E / Functional UI** | `@home @e2e @functional` | `home_discovery.feature` | 15 scenarios |
| **Smoke subset** | `@home @smoke` | Cross-cutting | 3 scenarios |

**Total:** 21 scenarios (all passing against Dev environment).

## Services under test

| Service | Port | APIs |
|---------|------|------|
| Common Data Service | 9000 | `GET /home`, `GET /tabs` |
| Search Suggestion Service | 10000 | `GET /default-search-suggestion` |
| React UI | 3000 | Home page, carousel, tiles, search bar |

## Run commands

```powershell
cd ecommerce_app_test_script\Shoppers_Serenity_Framework

# All Home tests
.\run_home.ps1

# Smoke only (API health + core E2E)
.\run_home_smoke.ps1

# API / integration only (no UI assertions)
.\run_home_api.ps1
```

## Configuration

Update `src/test/resources/config/DevConfig.properties`:

```properties
base.url=http://<host>:3000
common.data.service.url=http://<host>:9000
search.suggestion.service.url=http://<host>:10000
```

Use the same host as the React app (often WSL IP on Windows).

## New files added

```
features/Home/
  home_api.feature
  home_search_api.feature
  home_multiservice.feature
  home_discovery.feature

pageObjects/
  HomeDiscoveryPage.java
  HomeDiscoveryLocators.java
  ProductCatalogPage.java

stepdefinitions/
  HomeDiscoverySteps.java
  HomeApiSteps.java

servicehelpers/
  ApiClient.java
  CommonDataApiClient.java
  SearchSuggestionApiClient.java
  HomeApiValidator.java
  ServiceClientFactory.java

TestData/home_test_data.csv
run_home.ps1 / run_home_smoke.ps1 / run_home_api.ps1
```

## Scenario coverage map

| App capability | Scenario type | Automated |
|----------------|---------------|-----------|
| Hero carousel | E2E | Yes |
| Shop Top Brands | E2E + API | Yes |
| Shop Top Categories | E2E + API | Yes |
| Home menu icons | E2E | Yes (md viewport) |
| Default search hints | E2E + API + Multiservice | Yes |
| Brand tile → catalog | E2E | Yes |
| Category tile → catalog | E2E | Yes |
| `/home` payload structure | API | Yes |
| `/tabs` gender data | API | Yes |
| Backend health before UI | Multiservice | Yes |

## Report

After `mvn verify`:

```
target/site/serenity/index.html
```
