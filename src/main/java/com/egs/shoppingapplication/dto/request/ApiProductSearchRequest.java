package com.egs.shoppingapplication.dto.request;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.math.BigDecimal;

public class ApiProductRequest {

    @Length(min = 3, message = "Minimum name length: 3 characters")
    private String name;

    @Min(value = 0)
    @Digits(integer = 5, fraction = 2)
    private BigDecimal minPrice;

    @Min(value = 0)
    @Digits(integer = 5, fraction = 2)
    private BigDecimal maxPrice;

    @Min(value = 1)
    @Max(value = 5)
    private float rate;
}
