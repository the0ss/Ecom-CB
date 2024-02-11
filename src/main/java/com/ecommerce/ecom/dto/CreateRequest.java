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
public class CreateRequest {
    @NotBlank
    @NotNull(message = "Must be some value!")
    private String name;
    @NotNull(message =  "Description must have a value!")
    private String description;
    @NotNull(message =  "Price must not be empty!")
    private Double price;
    @NotNull(message = "Quantity must not be null!")
    private int quantityAvailable;
}
