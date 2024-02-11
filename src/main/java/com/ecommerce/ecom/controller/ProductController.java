package com.ecommerce.ecom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.ecom.dto.CreateRequest;
import com.ecommerce.ecom.dto.ErrorResponse;
import com.ecommerce.ecom.dto.UpdateRequest;
import com.ecommerce.ecom.service.ProductService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/api/v1")
public class ProductController {
    
    @Autowired
    ProductService productService;

    @PostMapping
    public ResponseEntity<Object> createProduct(@Valid @RequestBody CreateRequest createRequest) {
        return productService.createProduct(createRequest);
    }

    @GetMapping("product/{pid}")
    public ResponseEntity<Object> getProductDetails(@PathVariable("pid") Long pid) {
        return productService.readProduct(pid);
    }

    @PutMapping("product/{pid}")
    public ResponseEntity<Object> putMethodName(@PathVariable("pid") long pid,@Valid @RequestBody UpdateRequest updateRequest) {
        return productService.updateProduct(pid,updateRequest);
    }
    
    @DeleteMapping("product/{pid}")
    public ResponseEntity<Object>  deleteProduct(@PathVariable("pid") long pid){
        return productService.deleteProduct(pid);
    }

    @PostMapping("/{pId}/apply-discount-tax")
    public ResponseEntity<Object> applyDiscountOrTax(
            @PathVariable Long pId,
            @RequestParam int discountOrTax,
            @RequestParam @NotNull Double applicableValue
    ) {
        if(discountOrTax==1)
            return productService.applyTax(pId, applicableValue);
        return productService.applyDiscount(pId, applicableValue);
    }
}
