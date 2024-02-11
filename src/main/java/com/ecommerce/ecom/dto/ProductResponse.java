package com.ecommerce.ecom.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {
    private String responseMessage;
    private long pid;
    private String name;
    private String  description;
    private double price;
    private int stock;
}
