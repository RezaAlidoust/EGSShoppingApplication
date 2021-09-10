package com.egs.shoppingapplication.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
public class ApiRateRequest {

    @JsonProperty(required = true)
    @Min(value = 1)
    @Max(value = 5)
    private int rate;
}
