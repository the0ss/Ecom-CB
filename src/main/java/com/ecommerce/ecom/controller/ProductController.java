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
import jakarta.validation.constraints.NotNull;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("product")
public class ProductController {
    
    @Autowired
    ProductService productService;

    @PostMapping
    public ResponseEntity<Object> createProduct(@Valid @RequestBody CreateRequest createRequest) {
        return productService.createProduct(createRequest);
    }

    @GetMapping("{pid}")
    public ResponseEntity<Object> getProduct(@PathVariable("pid") Long pid) {
        return productService.readProduct(pid);
    }

    @PutMapping("{pid}")
    public ResponseEntity<Object> putProduct(@PathVariable("pid") long pid,@Valid @RequestBody UpdateRequest updateRequest) {
        return productService.updateProduct(pid,updateRequest);
    }
    
    @DeleteMapping("{pid}")
    public ResponseEntity<Object>  deleteProduct(@PathVariable("pid") long pid){
        return productService.deleteProduct(pid);
    }

    @PostMapping("{pId}/apply-discount-tax")
    public ResponseEntity<Object> applyDiscountOrTax(
            @PathVariable Long pId,
            @RequestParam int discountOrTax,
            @RequestParam @NotNull Double applicableValue
    ) {
        if(discountOrTax==1)
            return productService.applyTax(pId, applicableValue);
        else if(discountOrTax ==2)
            return productService.applyDiscount(pId, applicableValue);
        return new ResponseEntity<>(ErrorResponse.builder().errorMessage("Please choose either discount or tax, not both.").build(),HttpStatus.BAD_REQUEST);
    }
}
