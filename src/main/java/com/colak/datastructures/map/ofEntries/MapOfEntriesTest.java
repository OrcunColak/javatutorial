package com.colak.datastructures.map.ofEntries;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;

// Map.of() method allows you to create a map with up to 10 key-value pairs.
// If you need more than 10 entries, you would need to use Map.ofEntries() method
@Slf4j
class MapOfEntriesTest {

    public static void main(String[] args) {

        Map<String, String> columnMappings = Map.ofEntries(
                Map.entry("Customer ID", "customer"),
                Map.entry("Age", "age"),
                Map.entry("Gender", "gender"),
                Map.entry("Loyalty Member", "loyaltyMember"),
                Map.entry("Product Type", "productType"),
                Map.entry("SKU", "sku"),
                Map.entry("Rating", "rating"),
                Map.entry("Order Status", "orderStatus"),
                Map.entry("Payment Method", "paymentMethod"),
                Map.entry("Total Price", "totalPrice"),
                Map.entry("Unit Price", "unitPrice"),
                Map.entry("Quantity", "quantity"),
                Map.entry("Purchase Date", "purchaseDate"),
                Map.entry("Shipping Type", "shippingType"),
                Map.entry("Add-ons Purchased", "addonsPurchased"),
                Map.entry("Add-on Total", "addonTotal")

        );
        log.info("Map : {}", columnMappings);
    }
}
