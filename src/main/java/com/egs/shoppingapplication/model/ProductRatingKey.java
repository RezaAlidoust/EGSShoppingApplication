package com.egs.shoppingapplication.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

@Embeddable
public class ProductRatingKey implements Serializable {

    @Column(name = "user_id")
    UUID userId;

    @Column(name = "product_id")
    UUID productId;
}
