package com.freedom.wishlist.configuration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@PropertySource("classpath:application.properties")
@Component
@Data
public class WishlistConfig {

    @Value("${wishlist.max.size}")
    private int size;
}
