package com.ecommerce.ecom;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ecommerce.ecom.controller.ProductController;
import com.ecommerce.ecom.dto.CreateRequest;
import com.ecommerce.ecom.dto.ErrorResponse;
import com.ecommerce.ecom.entity.Product;
import com.ecommerce.ecom.repository.ProductRepository;
import com.ecommerce.ecom.service.ProductService;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {
    
    @InjectMocks
    ProductController productController;

    @Mock
    ProductRepository productRepository;

    @Mock
    ProductService productService;

    @Test
    public void testCreateProduct() {
        // Mock data
        CreateRequest createRequest = new CreateRequest("Product1", "Description", 20.0, 100);
        Product savedProduct = new Product(1L, "Product1", "Description", 20.0, 100);

        when(productService.createProduct(createRequest)).thenReturn(ResponseEntity.status(HttpStatus.CREATED).body(savedProduct));

        ResponseEntity<Object> response = productController.createProduct(createRequest);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void testAddShouldReturn400BadRequest() throws Exception{
        Long productId = 1L;
        int discountOrTax=0;
        Double applicableValue = 10.0;

        // Act
        ResponseEntity<Object> response = productController.applyDiscountOrTax(productId, discountOrTax, applicableValue);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(ErrorResponse.builder().errorMessage("Please choose either discount or tax, not both.").build(), response.getBody());

        // Verify that productRepository.save() was not called
        verify(productRepository, never()).save(any(Product.class));
    }
    @Test
    public void testAddShouldReturnSuccessRequest() throws Exception{
        Long productId = 1L;
        int discountOrTax=1;
        Double applicableValue = 10.0;

        when(productService.applyTax(productId, applicableValue)).thenReturn(ResponseEntity.ok("Tax applied successfully"));

        ResponseEntity<Object> response = productController.applyDiscountOrTax(productId, discountOrTax, applicableValue);
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
    @Test
    public void testGetProductDetails() {
        // Mock data
        Long productId = 1L;
        Product product = new Product(productId, "Product1", "Description", 20.0, 100);

        when(productService.readProduct(productId)).thenReturn(ResponseEntity.ok(product));

        ResponseEntity<Object> response = productController.getProduct(productId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    

}
