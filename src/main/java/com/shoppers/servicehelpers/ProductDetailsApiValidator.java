package com.shoppers.servicehelpers;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Validates Common Data Service product catalog and product-by-id API payloads.
 */
public final class ProductDetailsApiValidator {

    private ProductDetailsApiValidator() {
    }

    public static void assertSuccessfulProductListResponse(ServiceResponse response) {
        if (!response.isSuccessful()) {
            throw new AssertionError("Expected HTTP 2xx from /products?q= but got "
                    + response.getStatusCode() + " body=" + truncate(response.getBody()));
        }
        JsonObject json = JsonParser.parseString(response.getBody()).getAsJsonObject();
        if (!json.has("products") || !json.get("products").isJsonArray()) {
            throw new AssertionError("Expected 'products' array in catalog response");
        }
        JsonArray products = json.getAsJsonArray("products");
        if (products.isEmpty()) {
            throw new AssertionError("Expected non-empty product list");
        }
        assertProductFields(products.get(0).getAsJsonObject());
    }

    public static void assertSuccessfulProductByIdResponse(ServiceResponse response, String productId) {
        if (!response.isSuccessful()) {
            throw new AssertionError("Expected HTTP 2xx from /products?product_id= but got "
                    + response.getStatusCode() + " body=" + truncate(response.getBody()));
        }
        JsonObject json = JsonParser.parseString(response.getBody()).getAsJsonObject();
        if (!json.has(productId)) {
            throw new AssertionError("Expected product id '" + productId + "' in response");
        }
        assertProductFields(json.getAsJsonObject(productId));
    }

    public static String extractFirstProductId(ServiceResponse listResponse) {
        JsonObject json = JsonParser.parseString(listResponse.getBody()).getAsJsonObject();
        JsonObject first = json.getAsJsonArray("products").get(0).getAsJsonObject();
        return String.valueOf(first.get("id").getAsInt());
    }

    public static String extractFirstProductName(ServiceResponse listResponse) {
        JsonObject json = JsonParser.parseString(listResponse.getBody()).getAsJsonObject();
        JsonObject first = json.getAsJsonArray("products").get(0).getAsJsonObject();
        return first.get("name").getAsString();
    }

    public static String extractProductName(ServiceResponse byIdResponse, String productId) {
        JsonObject json = JsonParser.parseString(byIdResponse.getBody()).getAsJsonObject();
        return json.getAsJsonObject(productId).get("name").getAsString();
    }

    private static void assertProductFields(JsonObject product) {
        requireField(product, "id");
        requireField(product, "name");
        requireField(product, "price");
        requireField(product, "imageURL");
        if (!product.has("productBrandCategory") || !product.get("productBrandCategory").isJsonObject()) {
            throw new AssertionError("Expected productBrandCategory object on product");
        }
    }

    private static void requireField(JsonObject obj, String field) {
        if (!obj.has(field) || obj.get(field).isJsonNull()) {
            throw new AssertionError("Expected product field '" + field + "'");
        }
    }

    private static String truncate(String body) {
        if (body == null) {
            return "";
        }
        return body.length() > 300 ? body.substring(0, 300) + "..." : body;
    }
}
