package com.egs.shoppingapplication.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

@Data
public class AdminProductRequest {
    @JsonProperty(required = true)
    @NotEmpty
    @NotBlank
    @Length(min = 3, message = "Minimum name length: 3 characters")
    private String name;

    @Min(value = 0)
    @Digits(integer = 5, fraction = 2)
    private BigDecimal price;
}
