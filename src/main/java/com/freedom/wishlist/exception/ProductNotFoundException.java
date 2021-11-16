package com.freedom.wishlist.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.webjars.NotFoundException;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends NotFoundException {

    public ProductNotFoundException(String productId) {
        super(productId);
    }
}
