package com.egs.shoppingapplication.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
public class AdminCategoryRequest {
    @JsonProperty(required = true)
    @NotEmpty
    @NotBlank
    @Length(min = 2, message = "Minimum name length: 3 characters")
    private String name;
}
