package com.freedom.wishlist.api.v1.controller;

import com.freedom.wishlist.api.v1.dto.ProductDTO;
import com.freedom.wishlist.api.v1.mapper.Mapper;
import com.freedom.wishlist.domain.Product;
import com.freedom.wishlist.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController("ProductControllerV1")
@RequestMapping(RestPath.BASE_PATH + "/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private Mapper mapper;

    @PostMapping
    public ResponseEntity<Product> createProduct(@Valid @RequestBody ProductDTO productDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(productService.create(mapper.map(productDTO, Product.class)));
    }
}
