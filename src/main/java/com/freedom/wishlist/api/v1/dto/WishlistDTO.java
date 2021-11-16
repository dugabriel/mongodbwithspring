package com.freedom.wishlist.api.v1.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class WishlistDTO {

    @NotBlank
    @NotNull
    private String customerId;

    @NotNull
    @NotBlank
    private String productId;
}
