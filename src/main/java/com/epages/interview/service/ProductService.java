package com.epages.interview.service;

import com.epages.interview.domain.ProductsDto;
import org.springframework.data.domain.Sort;

public interface ProductService {

    ProductsDto getAllProducts(Sort sort);

}
