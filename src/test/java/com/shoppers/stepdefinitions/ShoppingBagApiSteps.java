package com.shoppers.stepdefinitions;

import com.shoppers.helpers.ScenarioContext;
import com.shoppers.servicehelpers.ProductDetailsApiValidator;
import com.shoppers.servicehelpers.ServiceClientFactory;
import com.shoppers.servicehelpers.ServiceResponse;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/**
 * Shopping Bag — API steps (Module 6).
 */
public class ShoppingBagApiSteps {

    private static final String BAG_PRODUCTS_KEY = "bagProductsResponse";

    @When("the common data service product by id API is called for bag product ids")
    public void theCommonDataServiceProductByIdApiIsCalledForBagProductIds() throws Exception {
        String productId = ScenarioContext.get("selectedProductId");
        if (productId == null) {
            String query = com.shoppers.helpers.TestConfigLoader.get("products.default.query",
                    com.shoppers.constants.Constants.DEFAULT_CATALOG_QUERY);
            ServiceResponse list = ServiceClientFactory.commonDataClient().getProductsByQuery(query);
            productId = ProductDetailsApiValidator.extractFirstProductId(list);
            ScenarioContext.put("selectedProductId", productId);
            ScenarioContext.put("selectedProductName",
                    ProductDetailsApiValidator.extractFirstProductName(list));
        }
        ServiceResponse byId = ServiceClientFactory.commonDataClient().getProductsById(productId);
        ScenarioContext.put(BAG_PRODUCTS_KEY, byId);
    }

    @Then("the bag product lookup API should return valid products")
    public void theBagProductLookupApiShouldReturnValidProducts() {
        ServiceResponse response = ScenarioContext.get(BAG_PRODUCTS_KEY);
        String productId = ScenarioContext.get("selectedProductId");
        ProductDetailsApiValidator.assertSuccessfulProductByIdResponse(response, productId);
    }
}
