package com.epages.interview;

import com.epages.interview.dao.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterClass;

@SpringBootTest
public class AbstractStorageTest extends AbstractTestNGSpringContextTests {

    @Autowired
    protected ProductRepository productRepository;

    @AfterClass
    void clearStorage() {
        productRepository.deleteAll();
    }

}