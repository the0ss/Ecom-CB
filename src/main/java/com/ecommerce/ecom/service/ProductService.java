package com.ecommerce.ecom.service;

import org.springframework.http.ResponseEntity;

import com.ecommerce.ecom.dto.CreateRequest;
import com.ecommerce.ecom.dto.UpdateRequest;

public interface ProductService {
    ResponseEntity<Object> createProduct(CreateRequest createRequest);
    ResponseEntity<Object> readProduct(Long  pid);
    ResponseEntity<Object> updateProduct(Long pid,UpdateRequest updateRequest);
    ResponseEntity<Object> deleteProduct(Long pid);
    ResponseEntity<Object> applyDiscount(Long pId,Double value);
    ResponseEntity<Object> applyTax(Long pId,Double value);
}
