package com.example.two;


import com.example.two.controllers.ProductController;
import com.example.two.dto.ProductDto;
import com.example.two.repository.ProductRepository;
import com.example.two.services.ProductService;

import org.junit.runner.RunWith;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
public class ProductControllerTests {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;


    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }


    @Mock
    private ProductRepository productRepository;

    @MockBean
    private ProductService productService;


    @Test
    public void getAllProducts() throws Exception {
        Mockito.when(productService.fetchAllProductsInShop()).thenReturn(Arrays.asList(
            new ProductDto("Grand Chair", "Amazing vintage chair", true, 211.0),
                new ProductDto("Lord of the rings" , "Will always be good", true, 31.0),
                new ProductDto("Grand Chair", "Amazing vintage chair", true, 11.0)
        ));
        mockMvc.perform(
                MockMvcRequestBuilders.get("/product")).andExpect(content().json("[{}, {}, {}]"));


    }


}
