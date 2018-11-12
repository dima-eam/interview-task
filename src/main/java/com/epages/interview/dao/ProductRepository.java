package com.epages.interview.dao;

import com.epages.interview.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Since the only requirement is to load all products, no special methods needed.
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
}
