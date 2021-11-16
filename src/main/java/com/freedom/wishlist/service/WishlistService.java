package com.freedom.wishlist.service;

import com.freedom.wishlist.api.v1.dto.WishlistDTO;
import com.freedom.wishlist.configuration.WishlistConfig;
import com.freedom.wishlist.domain.Product;
import com.freedom.wishlist.domain.Wishlist;
import com.freedom.wishlist.exception.CustomerNotFoundException;
import com.freedom.wishlist.exception.ProductNotFoundException;
import com.freedom.wishlist.exception.WishlistMaxSizeException;
import com.freedom.wishlist.exception.WishlistNotFountException;
import com.freedom.wishlist.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
public class WishlistService {

    @Autowired
    WishlistRepository wishlistRepository;

    @Autowired
    ProductService productService;

    @Autowired
    CustomerService customerService;

    @Autowired
    WishlistConfig wishlistConfig;

    @Transactional
    public Wishlist create(Wishlist wishlist) {
        return wishlistRepository.insert(wishlist);
    }

    @Transactional(readOnly = true)
    public List<Wishlist> findAll() {
        return wishlistRepository.findAll();
    }

    @Transactional
    public void addProduct(WishlistDTO wishlistDTO) {
        Product product = productService.findById(wishlistDTO.getProductId());
        verifyUser(wishlistDTO.getCustomerId());

        wishlistRepository.findById(wishlistDTO.getCustomerId())
                .ifPresentOrElse(wishlist -> {
                    if (wishlistConfig.getSize() >= wishlist.getProducts().size()) {
                        wishlist.getProducts().add(product);
                        wishlistRepository.save(wishlist);
                    } else {
                        throw new WishlistMaxSizeException();
                    }
                }, () -> create(Wishlist.builder()
                        .customerId(wishlistDTO.getCustomerId())
                        .products(Arrays.asList(product))
                        .build()));
    }

    @Transactional
    public Wishlist removeProduct(String customerId, String productId) {
        Wishlist wishlist = findById(customerId);
        wishlist.getProducts().removeIf(product -> product.getId().equals(productId));

        return wishlistRepository.save(wishlist);
    }

    @Transactional(readOnly = true)
    public Product findProductOnWishlist(String customerId, String productId) {
        Wishlist wishlist = findById(customerId);

        return wishlist.getProducts().stream()
                .filter(product -> product.getId().equals(productId))
                .findAny()
                .orElseThrow(() -> new ProductNotFoundException(productId));
    }

    private Wishlist findById(String customerId) {
        return wishlistRepository.findById(customerId)
                .orElseThrow(() -> new WishlistNotFountException(customerId));
    }

    private void verifyUser(String customerId) {
        if (Boolean.FALSE.equals(customerService.customerIsPresent(customerId))) {
            throw new CustomerNotFoundException(customerId);
        }
    }
}
