package com.epages.interview.dao;

import com.epages.interview.AbstractStorageTest;
import com.epages.interview.domain.Product;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

@SpringBootTest
public class ProductRepositoryTest extends AbstractStorageTest {

    @Test
    public void productRepositoryTestSuite() {
        shouldSuccessStore();

        shouldSuccessFindAll();
    }

    private void shouldSuccessStore() {
        Product product = new Product();
        product.setName("name");
        product.setBrand("brand");
        product.setOnSale(true);

        Product saved = productRepository.save(product);

        Assert.assertNotNull(saved.getId());
        assertEquals(product.getName(), "name");
        assertEquals(product.getBrand(), "brand");
        assertTrue(product.isOnSale());
    }

    private void shouldSuccessFindAll() {
        List<Product> all = productRepository.findAll();

        assertNotNull(all);
        assertEquals(1, all.size());
    }

}