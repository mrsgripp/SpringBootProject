package org.example.controller;

import org.example.entity.Product;
import org.example.exception.ProductNotFoundException;
import org.example.exception.ProductPriceException;
import org.example.exception.SellerNotFoundException;
import org.example.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = "product/{id}")
    public ResponseEntity<?> getProductByID(@PathVariable long id) throws Exception{
        try {
            Product product = productService.getThisProduct(id);
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (ProductNotFoundException e) {
            return new ResponseEntity<>("Product Not Found",HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("seller/{id}/product")
    public ResponseEntity<?> addProduct(@RequestBody Product p, @PathVariable long id) throws SellerNotFoundException, ProductPriceException {
        try{
            Product product = productService.addProduct(id, p);
            return new ResponseEntity<>(product, HttpStatus.CREATED);
        }catch (SellerNotFoundException e) {
            return new ResponseEntity<>("Seller Not Found", HttpStatus.NOT_FOUND);
        }catch (ProductPriceException e) {
            return new ResponseEntity<>("Invalid Product Request: Product Price Must Be Above 0", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Invalid Product Request: Products must have a Name. Names must be unique from other Product Names", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("product/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable long id) throws Exception{
        try {
            productService.deleteThisProduct(id);
            return new ResponseEntity<>("Product Deleted!",HttpStatus.OK);
        } catch (ProductNotFoundException e) {
            return new ResponseEntity<>("Product Not Found",HttpStatus.OK);
        }
    }

    @PutMapping("seller/{id1}/product/{id2}")
    public ResponseEntity<?> updateProduct(@RequestBody Product p, @PathVariable("id1") long sellerID, @PathVariable("id2") long productID) throws SellerNotFoundException, ProductPriceException, ProductNotFoundException{
        try{
            Product product = productService.editThisProduct(sellerID, productID, p);
            return new ResponseEntity<>(product, HttpStatus.OK);
        }catch (SellerNotFoundException e) {
            return new ResponseEntity<>("Seller Not Found", HttpStatus.NOT_FOUND);
        }catch (ProductPriceException e) {
            return new ResponseEntity<>("Invalid Product Request: Product Price Must Be Above 0", HttpStatus.BAD_REQUEST);
        }catch (ProductNotFoundException e){
            return new ResponseEntity<>("Product Not Found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Invalid Product Request: Products must have a Name. Names must be unique from other Product Names", HttpStatus.BAD_REQUEST);
        }

    }

}

