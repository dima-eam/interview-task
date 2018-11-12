package com.epages.interview.controller;

import com.epages.interview.domain.ProductsDto;
import com.epages.interview.service.ProductService;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Objects;

@RestController
@RequestMapping("/interview/api/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * By default, the response entries are not sorted. One could argue it's not convenient,
     * but it's explicit and save a couple of cycles
     */
    @GetMapping("/getAllProducts")
    public ProductsDto getAllProducts(@RequestParam(required = false, name = "sorting") String sorting) {
        return productService.getAllProducts(Sorting.from(sorting).getSort());
    }

    /**
     * Explicitly links incoming request param and sorting type,
     * rather than parse it to match entity/DB fields. Valid only for
     * DB sorting, for in-memory implementation use {@link java.util.Comparator}
     */
    private enum Sorting {

        NO_SORTING("noSorting", Sort.unsorted()),
        BY_BRAND_AND_PRICE("brandPrice", Sort.by("brand", "price"));

        private final String paramName;
        private final Sort sort;

        Sorting(String paramName, Sort sort) {
            this.paramName = paramName;
            this.sort = sort;
        }

        public Sort getSort() {
            return sort;
        }

        @Nonnull
        public static Sorting from(@Nullable String sorting) {
            if (sorting == null) {
                return NO_SORTING;
            }

            return Arrays.stream(values())
                    .filter(v -> Objects.equals(sorting, v.paramName))
                    .findFirst()
                    .orElse(NO_SORTING);
        }

    }

}
