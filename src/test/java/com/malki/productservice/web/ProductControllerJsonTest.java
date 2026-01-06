
package com.store.productservice.web;

import com.store.productservice.controller.ProductController;
import com.store.productservice.dto.ProductResponse;
import com.store.productservice.service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
class ProductControllerJsonTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    void getProductById_returns200() throws Exception {
        // Retourne un mock du DTO sans dépendre de sa forme exacte
        ProductResponse dto = Mockito.mock(ProductResponse.class);
        Mockito.when(productService.getById(1L)).thenReturn(dto);

        mockMvc.perform(get("/api/products/1"))
               .andExpect(status().isOk());
    }
}
