# Product Details — Automated Test Suite

Test automation for **Module 5 — Product Details** from the [application functional overview](../../dummy-ecommerce-microservices/docs/FUNCTIONAL_OVERVIEW.md).

## Test layers

| Layer | Tag | Feature files | Scenarios |
|-------|-----|---------------|-----------|
| **API / Integration** | `@product @api @integration` | `product_details_api.feature` | 2 |
| **Multiservice** | `@product @multiservice @integration` | `product_details_multiservice.feature` | 2 |
| **E2E / Functional UI** | `@product @e2e @functional` | `product_details_e2e.feature` | 4 |
| **Smoke subset** | `@product @smoke` | Cross-cutting | 5 |

**Total:** 8 scenarios

## Services under test

| Service | Port | APIs |
|---------|------|------|
| Common Data Service | 9000 | `GET /products?q=`, `GET /products?product_id=` |
| React UI | 3000 | Product details route, quantity, add to bag, proceed to bag |

## Run commands

```powershell
cd ecommerce_app_test_script\Shoppers_Serenity_Framework

# All Product Details tests
.\run_product.ps1

# Smoke only
.\run_product_smoke.ps1

# API / integration only (fastest; no UI)
.\run_product_api.ps1
```

**Prerequisite:** Docker Compose app running and `DevConfig.properties` URLs reachable.

## Configuration

```properties
base.url=http://<host>:3000
common.data.service.url=http://<host>:9000
products.default.query=page=0,16
```

## New files

```
features/ProductDetails/
  product_details_api.feature
  product_details_multiservice.feature
  product_details_e2e.feature

pageObjects/
  ProductDetailsPage.java
  ProductDetailsLocators.java
  ShoppingBagPage.java

stepdefinitions/
  ProductDetailsApiSteps.java
  ProductDetailsUiSteps.java

servicehelpers/
  ProductDetailsApiValidator.java

run_product.ps1 / run_product_smoke.ps1 / run_product_api.ps1
```

## Scenario coverage map

| App capability | Test type | Automated |
|----------------|-----------|-----------|
| Paginated product catalog (`/products?q=`) | API | Yes |
| Product by id (`/products?product_id=`) | API | Yes |
| List vs by-id name consistency | API | Yes |
| API health before UI | Multiservice | Yes |
| UI details match API product name | Multiservice E2E | Yes |
| Catalog → details navigation | E2E | Yes |
| Image, breadcrumbs, tax label | E2E | Yes |
| Quantity increase (+/-) | E2E | Yes |
| Add to bag + bag badge | E2E | Yes |
| Proceed to bag from details | E2E | Yes |

## Architecture notes

- **ProductDetailsApiSteps** — catalog and by-id API glue; stores id/name in `ScenarioContext`
- **ProductDetailsPage** — opens `/products/details?q=<query>::product_id=<id>`, qty, bag actions
- **ProductCatalogPage** — default catalog listing and first-product navigation
- **ProductDetailsApiValidator** — JSON validation for list and by-id payloads
- **ShoppingBagPage** — minimal bag verification for proceed-to-bag flow (`/products/details/shopping-bag` or `/shopping-bag`)

## Report

```
target/site/serenity/index.html
```
