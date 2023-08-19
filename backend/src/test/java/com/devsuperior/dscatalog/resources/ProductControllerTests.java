package com.devsuperior.dscatalog.resources;

import com.devsuperior.dscatalog.services.ProductService;
import com.devsuperior.dscatalog.DTO.ProductDTO;
import com.devsuperior.dscatalog.controllers.exception.ProductController;
import com.devsuperior.dscatalog.services.ProductService;
import com.devsuperior.dscatalog.tests.Factory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.List;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//sempre que ele falar "ProductResource" esta se referindo a "ProductController".//
@WebMvcTest(ProductController.class)
public class ProductControllerTests {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ProductController controller;


    private ProductDTO productDTO;
    private PageImpl<ProductDTO> page;
    @BeforeEach
    void setUp() throws Exception{

        productDTO = Factory.createProductDTO();
        page = new PageImpl<>(List.of(productDTO));
        when(controller.findAll(any())).thenReturn(page);
    }
    @Test
    public void findAllShouldReturnPage() throws Exception {

       mockMvc.perform(get("/products")).andExpect(status().isOk());

    }



}
