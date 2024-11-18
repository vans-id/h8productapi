package com.example.productapi.service;

import com.example.productapi.model.Product;
import com.example.productapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Integer id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product Not Found"));
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Integer id, Product updatedProduct) {
        return productRepository.findById(id).map(existingProduct -> {
           existingProduct.setName(updatedProduct.getName());
           existingProduct.setDescription(updatedProduct.getDescription());
           existingProduct.setPrice(updatedProduct.getPrice());
           existingProduct.setStock(updatedProduct.getStock());

           return productRepository.save(existingProduct);
        }).orElseThrow(() -> new RuntimeException("Product Not Found"));
    }

    public void deleteProduct(Integer id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product Not Found"));
        productRepository.delete(product);
    }
}
