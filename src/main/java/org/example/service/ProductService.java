package org.example.service;

import org.example.entity.Product;
import org.example.entity.Seller;
import org.example.exception.ProductNotFoundException;
import org.example.exception.SellerNotFoundException;
import org.example.repository.ProductRepository;
import org.example.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    ProductRepository productRepository;
    SellerRepository sellerRepository;
    @Autowired
    public ProductService (ProductRepository productRepository, SellerRepository sellerRepository){
        this.productRepository = productRepository;
        this.sellerRepository = sellerRepository;
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public Product addProduct(long id, Product p) throws SellerNotFoundException{
        Optional<Seller> optional = sellerRepository.findById(id);
        Seller s;
        if(optional.isEmpty()){
            throw new SellerNotFoundException("No Seller Registered with ID: "+ id);
        }else{
            s = optional.get();
        }
        p.setSeller(s);
        Product product = productRepository.save(p);
        s.getProductList().add(product);
        sellerRepository.save(s);
        return product;
    }

    public void deleteThisProduct(long id) throws ProductNotFoundException{
        Optional<Product> optional = productRepository.findById(id);
        Product p;
        if(optional.isEmpty()){
            throw new ProductNotFoundException("No Product Exists With ID: " + id);
        }else {
            p = optional.get();
        }
        productRepository.delete(p);
    }

    /*public Product editThisProduct(long id) throws ProductNotFoundException{
        Optional<Product> optional = productRepository.findById(id);
        Product p;
        if(optional.isEmpty()){
            throw new ProductNotFoundException("No Product Exists With ID: " + id);
        }else {
            p = optional.get();
        }
        productRepository.
    }*/

}
