package com.ecommerce.ecom.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ecommerce.ecom.dto.CreateRequest;
import com.ecommerce.ecom.dto.ErrorResponse;
import com.ecommerce.ecom.dto.ProductResponse;
import com.ecommerce.ecom.dto.SuccessResponse;
import com.ecommerce.ecom.dto.UpdateRequest;
import com.ecommerce.ecom.entity.Product;
import com.ecommerce.ecom.repository.ProductRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public ResponseEntity<Object> createProduct(CreateRequest createRequest) {
        if(productRepository.existsByName(createRequest.getName())){
            return new ResponseEntity<>(ErrorResponse.builder().errorMessage("This name is already in use").build(),HttpStatus.BAD_REQUEST);
        }

        Product newProduct=Product.builder()
                                .name(createRequest.getName())
                                .description(createRequest.getDescription())
                                .price(createRequest.getPrice())
                                .quantityAvailable(createRequest.getQuantityAvailable())
                                .build();

        Product savedProduct= productRepository.save(newProduct);

        return new ResponseEntity<>(ProductResponse.builder()
                            .responseMessage("Created")
                            .pid(savedProduct.getProductId())
                            .name(savedProduct.getName())
                            .description(savedProduct.getDescription())
                            .price(savedProduct.getPrice())
                            .quantityAvailable(savedProduct.getQuantityAvailable())
                            .build(),HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Object> readProduct(Long pid) {
        if(!productRepository.existsByProductId(pid))
            return new ResponseEntity<>(ErrorResponse.builder()
            .errorMessage("This product not exist.")
            .build(),HttpStatus.NOT_FOUND);

        Product product=productRepository.findByProductId(pid);

        return new ResponseEntity<>(ProductResponse.builder()
                    .responseMessage("Reading Successful")
                    .pid(product.getProductId())
                    .name(product.getName())
                    .description(product.getDescription())
                    .price(product.getPrice())
                    .quantityAvailable(product.getQuantityAvailable())
                    .build() , HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> updateProduct(Long pid,UpdateRequest updateRequest) {
        if(!productRepository.existsByProductId(pid))
            return new ResponseEntity<>(ErrorResponse.builder()
            .errorMessage("This product not exist.")
            .build(),HttpStatus.NOT_FOUND);

        Product saveProduct=productRepository.findByProductId(pid);
        saveProduct.setName(updateRequest.getName());
        saveProduct.setDescription(updateRequest.getDescription());
        saveProduct.setPrice(updateRequest.getPrice());
        saveProduct.setQuantityAvailable(updateRequest.getQuantityAvailable());

        productRepository.save(saveProduct);
        return new ResponseEntity<>(SuccessResponse.builder().successMessage("Updated Successfully").build(),HttpStatus.OK);
    }


    @Override
    public ResponseEntity<Object> deleteProduct(Long pid) {
        if(!productRepository.existsByProductId(pid))
            return new ResponseEntity<>(ErrorResponse.builder()
            .errorMessage("This product not exist.")
            .build(),HttpStatus.NOT_FOUND);

        Product savedProduct=productRepository.findByProductId(pid);
        productRepository.delete(savedProduct);
        return  new ResponseEntity<>(SuccessResponse.builder().successMessage("Deleted Successfully").build(),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> applyDiscount(Long pId, Double value) {
        if(!productRepository.existsByProductId(pId))
        return new ResponseEntity<> ( ErrorResponse.builder().errorMessage("The product doesn't exist.")
                .build(),HttpStatus.BAD_REQUEST);

        Product product = productRepository.findByProductId(pId);
        double discountedPrice = product.getPrice() - (product.getPrice() * (value / 100));
        product.setPrice(discountedPrice);
        productRepository.save(product);

        return new ResponseEntity<>(ProductResponse.builder().responseMessage("Discount applied successfully")
                                            .pid(product.getProductId())
                                            .name(product.getName())
                                            .description(product.getDescription())
                                            .price(product.getPrice())
                                            .quantityAvailable(product.getQuantityAvailable())
                                            .build(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> applyTax(Long pId, Double value) {
        if(!productRepository.existsByProductId(pId))
        return new ResponseEntity<> ( ErrorResponse.builder().errorMessage("The product doesn't exist.")
                .build(),HttpStatus.BAD_REQUEST);

        Product product = productRepository.findByProductId(pId);
        double discountedPrice = product.getPrice() + (product.getPrice() * (value / 100));
        product.setPrice(discountedPrice);
        productRepository.save(product);

        return new ResponseEntity<>(ProductResponse.builder().responseMessage("Tax applied successfully")
                                            .pid(product.getProductId())
                                            .name(product.getName())
                                            .description(product.getDescription())
                                            .price(product.getPrice())
                                            .quantityAvailable(product.getQuantityAvailable())
                                            .build(), HttpStatus.OK);
    }
    
}
