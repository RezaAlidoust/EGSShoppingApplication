package com.egs.shoppingapplication.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class ProductRatingKey implements Serializable {

    @Column(name = "user_id", length = 36)
    @org.hibernate.annotations.Type(type = "org.hibernate.type.UUIDCharType")
    private UUID userId;

    @Column(name = "product_id", length = 36)
    @org.hibernate.annotations.Type(type = "org.hibernate.type.UUIDCharType")
    private UUID productId;

    public ProductRatingKey(UUID userId, UUID productId) {
        this.userId = userId;
        this.productId = productId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductRatingKey productRatingKey = (ProductRatingKey) o;
        return userId.equals(productRatingKey.userId) &&
                productId.equals(productRatingKey.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, productId);
    }
}
