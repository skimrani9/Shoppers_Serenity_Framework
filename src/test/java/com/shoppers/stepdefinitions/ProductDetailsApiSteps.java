package com.shoppers.stepdefinitions;

import com.shoppers.constants.Constants;
import com.shoppers.helpers.ScenarioContext;
import com.shoppers.helpers.TestConfigLoader;
import com.shoppers.servicehelpers.ProductDetailsApiValidator;
import com.shoppers.servicehelpers.ServiceClientFactory;
import com.shoppers.servicehelpers.ServiceResponse;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/**
 * API and multiservice steps for Product Details module.
 */
public class ProductDetailsApiSteps {

    private static final String PRODUCT_LIST_KEY = "productListResponse";
    private static final String PRODUCT_BY_ID_KEY = "productByIdResponse";
    private static final String SELECTED_PRODUCT_ID_KEY = "selectedProductId";
    private static final String SELECTED_PRODUCT_NAME_KEY = "selectedProductName";

    @When("the common data service product catalog API is called with default pagination")
    public void theCommonDataServiceProductCatalogApiIsCalledWithDefaultPagination() throws Exception {
        String query = TestConfigLoader.get("products.default.query", Constants.DEFAULT_CATALOG_QUERY);
        ServiceResponse response = ServiceClientFactory.commonDataClient().getProductsByQuery(query);
        ScenarioContext.put(PRODUCT_LIST_KEY, response);
    }

    @When("the common data service product by id API is called for the first catalog product")
    public void theCommonDataServiceProductByIdApiIsCalledForTheFirstCatalogProduct() throws Exception {
        ServiceResponse list = ScenarioContext.get(PRODUCT_LIST_KEY);
        String productId = ProductDetailsApiValidator.extractFirstProductId(list);
        ScenarioContext.put(SELECTED_PRODUCT_ID_KEY, productId);
        ScenarioContext.put(SELECTED_PRODUCT_NAME_KEY, ProductDetailsApiValidator.extractFirstProductName(list));
        ServiceResponse byId = ServiceClientFactory.commonDataClient().getProductsById(productId);
        ScenarioContext.put(PRODUCT_BY_ID_KEY, byId);
    }

    @When("the common data service product by id API is called for product id {string}")
    public void theCommonDataServiceProductByIdApiIsCalledForProductId(String productId) throws Exception {
        ScenarioContext.put(SELECTED_PRODUCT_ID_KEY, productId);
        ServiceResponse byId = ServiceClientFactory.commonDataClient().getProductsById(productId);
        ScenarioContext.put(PRODUCT_BY_ID_KEY, byId);
    }

    @Given("product catalog and product-by-id APIs are healthy")
    public void productCatalogAndProductByIdApisAreHealthy() throws Exception {
        String query = TestConfigLoader.get("products.default.query", Constants.DEFAULT_CATALOG_QUERY);
        ServiceResponse list = ServiceClientFactory.commonDataClient().getProductsByQuery(query);
        ProductDetailsApiValidator.assertSuccessfulProductListResponse(list);
        String productId = ProductDetailsApiValidator.extractFirstProductId(list);
        String productName = ProductDetailsApiValidator.extractFirstProductName(list);
        ServiceResponse byId = ServiceClientFactory.commonDataClient().getProductsById(productId);
        ProductDetailsApiValidator.assertSuccessfulProductByIdResponse(byId, productId);
        ScenarioContext.put(PRODUCT_LIST_KEY, list);
        ScenarioContext.put(PRODUCT_BY_ID_KEY, byId);
        ScenarioContext.put(SELECTED_PRODUCT_ID_KEY, productId);
        ScenarioContext.put(SELECTED_PRODUCT_NAME_KEY, productName);
    }

    @Then("the product catalog API should return a valid product list")
    public void theProductCatalogApiShouldReturnAValidProductList() {
        ServiceResponse response = ScenarioContext.get(PRODUCT_LIST_KEY);
        ProductDetailsApiValidator.assertSuccessfulProductListResponse(response);
    }

    @Then("the product by id API should return matching product details")
    public void theProductByIdApiShouldReturnMatchingProductDetails() {
        ServiceResponse list = ScenarioContext.get(PRODUCT_LIST_KEY);
        ServiceResponse byId = ScenarioContext.get(PRODUCT_BY_ID_KEY);
        String productId = ScenarioContext.get(SELECTED_PRODUCT_ID_KEY);
        ProductDetailsApiValidator.assertSuccessfulProductByIdResponse(byId, productId);
        String listName = ProductDetailsApiValidator.extractFirstProductName(list);
        String byIdName = ProductDetailsApiValidator.extractProductName(byId, productId);
        if (!listName.equals(byIdName)) {
            throw new AssertionError("Product name mismatch: list='" + listName + "', byId='" + byIdName + "'");
        }
    }

    @Then("the product by id API should return valid product for id {string}")
    public void theProductByIdApiShouldReturnValidProductForId(String productId) {
        ServiceResponse byId = ScenarioContext.get(PRODUCT_BY_ID_KEY);
        ProductDetailsApiValidator.assertSuccessfulProductByIdResponse(byId, productId);
    }
}
