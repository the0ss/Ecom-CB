package com.ecommerce.ecom.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRequest {
    @NotBlank
    @NotNull(message = "Name not provided.")
    String name;
    @NotNull(message = "Description not provided.")
    String description;
    @NotNull(message =  "Price is required and cannot be null.")
    double price;
    @NotNull(message = "Quantity must be provided")
    int quantityAvailable;
}
