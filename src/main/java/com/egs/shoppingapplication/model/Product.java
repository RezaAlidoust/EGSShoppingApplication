package com.egs.shoppingapplication.model;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Product extends BaseEntity {

    @Length(min = 3, message = "Minimum name length: 3 characters")
    @Column(nullable = false)
    private String name;

    @Min(value = 0)
    @Digits(integer = 5, fraction = 2)
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Category category;

    @Builder
    public Product(UUID id, Timestamp createdAt, Timestamp updatedAt, String name, BigDecimal price) {
        super(id, createdAt, updatedAt);
        this.name = name;
        this.price = price;
    }
}
