package com.nitesh.biddingPlatform.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nitesh.biddingPlatform.model.Product;
import com.nitesh.biddingPlatform.model.User;
import com.nitesh.biddingPlatform.services.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(MockitoJUnitRunner.class)
public class ProductResourceTest {
    @Autowired
    private MockMvc mvc;
    @Mock
    private ProductService productService;
    @InjectMocks
    private ProductResource productResource = new ProductResource();

    @Before
    @Deprecated
    public void initMocks() {
        mvc = MockMvcBuilders.standaloneSetup(productResource).build();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddProduct() throws Exception {
        Product product = new Product();
        product.setProductName("Almirah");
        User user = new User();
        user.setId(6);
        product.setUser(user);
        String json = "{\"productName\":\"Almirah\",\"ownerId\":6}";
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(json);

        when(productService.addProduct(any(JsonNode.class))).thenReturn(product);
        final ResultActions result =
                mvc.perform(
                        post("/product")
                                .content("{\"productName\":\"Almirah\",\"ownerId\":6}")
                                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.productName", is("Almirah")));
    }
}
