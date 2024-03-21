package org.example.controller;

import org.example.entity.Product;
import org.example.exception.ProductNotFoundException;
import org.example.exception.SellerNotFoundException;
import org.example.service.ProductService;
import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class ProductController {

    ProductService productService;
    public ProductController (ProductService productService){
        this.productService = productService;
    }

    @GetMapping(value="products")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> productList;
        productList = productService.getAllProducts();
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @PostMapping("seller/{id}/product")
    public ResponseEntity<Product> addProduct(@RequestBody Product p, @PathVariable long id) throws Exception{
            Product product = productService.addProduct(id, p);
            return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @DeleteMapping("product/{id}")
    public ResponseEntity deleteProduct(@PathVariable long id) throws Exception{
        try {
            productService.deleteThisProduct(id);
            return new ResponseEntity<>("Product Deleted!",HttpStatus.OK);
        } catch (ProductNotFoundException e) {
            return new ResponseEntity<>("Product Not Found",HttpStatus.OK);
        }
    }

}

