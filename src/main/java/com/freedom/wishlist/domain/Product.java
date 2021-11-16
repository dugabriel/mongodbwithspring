package com.freedom.wishlist.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("product")
public class Product extends BaseEntity {

    @Id
    private String id;

    private String name;
}
