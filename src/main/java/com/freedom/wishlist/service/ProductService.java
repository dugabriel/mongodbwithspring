package com.freedom.wishlist.service;

import com.freedom.wishlist.domain.Product;
import com.freedom.wishlist.exception.ProductNotFoundException;
import com.freedom.wishlist.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public Product create(Product product) {
        return productRepository.insert(product);
    }

    @Transactional(readOnly = true)
    public Product findById(String productId) {
        return productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException(productId));
    }
}
