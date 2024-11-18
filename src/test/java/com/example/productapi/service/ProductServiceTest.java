package com.example.productapi.service;

import com.example.productapi.model.Product;
import com.example.productapi.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddProduct_Success() {
        Product product = new Product("Laptop", "Gaming Laptop", BigDecimal.valueOf(1500), 10);

        when(productRepository.save(product)).thenReturn(product);

        Product res = productService.addProduct(product);

        assertNotNull(res);
        assertEquals("Laptop", res.getName());
        verify(productRepository, times(1)).save(product);
    }

    @Test
    void testGetProductById_NotFound() {
        when(productRepository.findById(1)).thenReturn(Optional.empty());

        Exception e = assertThrows(RuntimeException.class, () -> {
            productService.getProductById(1);
        });

        assertEquals("Product Not Found", e.getMessage());
    }
}
