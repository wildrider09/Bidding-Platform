package com.nitesh.biddingPlatform.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nitesh.biddingPlatform.model.Product;
import com.nitesh.biddingPlatform.model.ProductBids;
import com.nitesh.biddingPlatform.model.User;
import com.nitesh.biddingPlatform.services.ProductBidsService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductBidResourceTest {

    @Autowired
    private MockMvc mvc;
    @Mock
    private ProductBidsService productBidsService;
    @InjectMocks
    private ProductBidResource productBidResource = new ProductBidResource();

    @Before
    @Deprecated
    public void initMocks() {
        mvc = MockMvcBuilders.standaloneSetup(productBidResource).build();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddProduct() throws Exception {
        ProductBids bid = new ProductBids();
        bid.setBidAmount(1000);
        User user1 = new User();
        user1.setId(3);
        bid.setBidOwner(user1);
        User user2 = new User();
        user2.setId(1);
        Product prod = new Product();
        prod.setProductId(2);
        prod.setUser(user2);
        bid.setProductToBid(prod);
        String json = "{\"bidAmount\":1000,\"bidProductId\":2,\"bidOwner\":3}";
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(json);

        when(productBidsService.addBid(any(JsonNode.class))).thenReturn(bid);
        final ResultActions result =
                mvc.perform(
                        post("/bids")
                                .content("{\"bidAmount\":1000,\"bidProductId\":2,\"bidOwner\":3}")
                                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.bidAmount", is(1000)));
    }
 }