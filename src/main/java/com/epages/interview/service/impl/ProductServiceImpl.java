package com.epages.interview.service.impl;

import com.epages.interview.dao.ProductRepository;
import com.epages.interview.domain.Event;
import com.epages.interview.domain.Product;
import com.epages.interview.domain.ProductDto;
import com.epages.interview.domain.ProductsDto;
import com.epages.interview.service.ProductService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * This implementation use DB sorting capabilities, for simplicity's sake
 * and naive performance boost (assuming reading queries call slave DB node).
 * One can write another implementation with in-memory grouping/sorting.
 */
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductsDto getAllProducts(Sort sort) {
        return convert(productRepository.findAll(sort));
    }

    /**
     * Uses LinkedHashMap as map factory to preserve sorted order
     */
    private ProductsDto convert(List<Product> products) {
        Map<String, List<ProductDto>> dtos = products.stream()
                .map(p -> new ProductDto(p.getId(), p.getName(), p.getPrice(), p.getBrand(), Event.fromBoolean(p.isOnSale())))
                .collect(Collectors.groupingBy(
                        ProductDto::getBrand,
                        LinkedHashMap::new,
                        Collectors.toList()
                ));

        return new ProductsDto(dtos);
    }

}
