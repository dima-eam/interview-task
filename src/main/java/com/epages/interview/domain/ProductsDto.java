package com.epages.interview.domain;

import java.util.List;
import java.util.Map;

/**
 * Result wrapper, allows to extend result without messing with <tt>Map&lt;String, Object&gt;</tt>
 * Assumed using with Jackson serialization capabilities.
 */
public class ProductsDto {

    /**
     * Brand name with list of products
     */
    private final Map<String, List<ProductDto>> allProducts;

    public ProductsDto(Map<String, List<ProductDto>> allProducts) {
        this.allProducts = allProducts;
    }

    public Map<String, List<ProductDto>> getAllProducts() {
        return allProducts;
    }
}
