package com.epages.interview;

import com.epages.interview.domain.Product;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.nio.charset.Charset;
import java.util.Map;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

@SpringBootTest
@AutoConfigureMockMvc
public class InterviewApplicationTests extends AbstractStorageTest {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnCorrectOrderWithParam() throws Exception {
        String expected = IOUtils.toString(getClass().getResourceAsStream("/expectedProducts.json"), Charset.defaultCharset());

        String result = mockMvc.perform(MockMvcRequestBuilders.get("/interview/api/product/getAllProducts")
                .param("sorting", "brandPrice"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();

        JSONAssert.assertEquals(expected, result, true);
    }

    @Test
    public void shouldReturnAllProducts() throws Exception {
        String result = mockMvc.perform(MockMvcRequestBuilders.get("/interview/api/product/getAllProducts"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();

        Map<String, Object> products = OBJECT_MAPPER.readValue(result, new TypeReference<Map<String, Object>>() {
        });
        assertNotNull(products);
        assertTrue(products.size() == 1);
        assertNotNull(products.get("allProducts"));
    }

    @BeforeClass
    public void setUpStorage() {
        Product product = new Product();
        product.setName("Product A");
        product.setBrand("Brand A");
        product.setOnSale(true);
        product.setPrice(1.01);
        productRepository.save(product);

        product = new Product();
        product.setName("Product B");
        product.setBrand("Brand A");
        product.setOnSale(false);
        product.setPrice(0.65);
        productRepository.save(product);

        product = new Product();
        product.setName("Product C");
        product.setBrand("Brand B");
        product.setPrice(22);
        productRepository.save(product);

        product = new Product();
        product.setName("Product D");
        product.setBrand("Brand B");
        product.setPrice(45);
        productRepository.save(product);
    }

}
