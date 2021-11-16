package com.freedom.wishlist.api.v1.controller;

import com.freedom.wishlist.api.v1.dto.WishlistDTO;
import com.freedom.wishlist.domain.Product;
import com.freedom.wishlist.domain.Wishlist;
import com.freedom.wishlist.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController("WishlistControllerV1")
@RequestMapping(RestPath.BASE_PATH + "/wishlist")
public class WishlistController {

    @Autowired
    private WishlistService wishlistService;

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> addProduct(@Valid @RequestBody WishlistDTO wishlistDTO) {
        wishlistService.addProduct(wishlistDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/remove/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Wishlist> removeProduct(@PathVariable String customerId, @Valid @RequestParam String productId) {
        return ResponseEntity.status(HttpStatus.OK).body(wishlistService.removeProduct(customerId, productId));
    }

    @GetMapping("/{customerId}/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Product> findProductOnWishlist(@PathVariable String customerId, @PathVariable String productId) {
        return ResponseEntity.status(HttpStatus.OK).body(wishlistService.findProductOnWishlist(customerId, productId));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Wishlist>> findAll() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(wishlistService.findAll());
    }
}
