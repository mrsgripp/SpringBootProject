package org.example.controller;

import org.example.entity.Product;
import org.example.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    ProductService productService;
    public ProductController (ProductService productService){
        this.productService = productService;
    }

    @GetMapping(value="product")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> productList;
        productList = productService.getAllProducts();
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }
}
