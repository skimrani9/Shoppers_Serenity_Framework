# Search & Suggestions — Automated Test Suite

Test automation for **Module 3 — Search & Suggestions** from the [application functional overview](../../dummy-ecommerce-microservices/docs/FUNCTIONAL_OVERVIEW.md).

## Test layers

| Layer | Tag | Feature files | Scenarios |
|-------|-----|---------------|-----------|
| **API / Integration** | `@search @api @integration` | `search_api.feature` | 6 |
| **Multiservice** | `@search @multiservice @integration` | `search_multiservice.feature` | 2 |
| **E2E / Functional UI** | `@search @e2e @functional` | `search_e2e.feature` | 11 |
| **Smoke subset** | `@search @smoke` | Cross-cutting | 3 |

**Total:** 19 scenarios

## Services under test

| Service | Port | APIs |
|---------|------|------|
| Search Suggestion Service | 10000 | `GET /search-suggestion?q=`, `GET /default-search-suggestion` |
| Common Data Service | 9000 | `GET /search-suggestion-list` (keyword index source) |
| React UI | 3000 | Navbar search bar, autocomplete, mobile search |

## Run commands

```powershell
cd ecommerce_app_test_script\Shoppers_Serenity_Framework

# All Search tests
.\run_search.ps1

# Smoke only
.\run_search_smoke.ps1

# API / integration only (fastest; no UI)
.\run_search_api.ps1
```

**Prerequisite:** Docker Compose app running and `DevConfig.properties` URLs reachable.

## Configuration

```properties
base.url=http://<host>:3000
common.data.service.url=http://<host>:9000
search.suggestion.service.url=http://<host>:10000
```

## New files

```
features/Search/
  search_api.feature
  search_multiservice.feature
  search_e2e.feature

pageObjects/
  SearchBarPage.java
  SearchLocators.java

stepdefinitions/
  SearchApiSteps.java
  SearchUiSteps.java

servicehelpers/
  SearchApiValidator.java

TestData/search_test_data.csv
run_search.ps1 / run_search_smoke.ps1 / run_search_api.ps1
```

## Scenario coverage map

| App capability | Test type | Automated |
|----------------|-----------|-----------|
| Default suggestions on focus | E2E + API | Yes |
| Live typeahead while typing | E2E + API | Yes |
| Prefix search (`/search-suggestion?q=`) | API | Yes |
| Keyword source list (`/search-suggestion-list`) | API + Multiservice | Yes |
| Brand/apparel/gender keyword matching | API Scenario Outline | Yes |
| Select suggestion → product catalog | E2E | Yes |
| Mobile expandable search | E2E | Yes |
| Multiservice health (common-data + search) | Integration | Yes |
| UI typeahead backed by live API | Multiservice E2E | Yes |

## Architecture notes

- **SearchApiSteps** — shared API glue (also used by Home `home_search_api.feature`)
- **SearchBarPage** — desktop + mobile search; case-insensitive suggestion matching
- **SearchApiValidator** — JSON validation for prefix/default/list APIs
- **ProductCatalogPage.verifyUrlContainsSearchFilter()** — asserts catalog navigation from search

## Report

```
target/site/serenity/index.html
```
