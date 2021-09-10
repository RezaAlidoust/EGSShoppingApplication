package com.egs.shoppingapplication.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
public class ProductRating {
    @EmbeddedId
    ProductRatingKey id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;

    @Min(value = 1)
    @Max(value = 5)
    @Column(nullable = false)
    private int rating;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    @Builder
    public ProductRating(Timestamp createdAt, Timestamp updatedAt, int rating) {
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.rating = rating;
    }
}
