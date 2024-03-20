package org.example.controller;

import org.apache.coyote.Response;
import org.example.entity.Seller;
import org.example.service.SellerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SellerController {

    SellerService sellerService;

    public SellerController(SellerService sellerService){
        this.sellerService = sellerService;
    }

    @GetMapping("sellers")
    public ResponseEntity<List<Seller>> getAllSellers(){
        List<Seller> sellerList = sellerService.getAllSellers();
        return new ResponseEntity<>(sellerList, HttpStatus.OK);
    }

    @PostMapping("/seller")
    public ResponseEntity<Seller> addSeller(@RequestBody Seller s){
        Seller seller = sellerService.saveSeller(s);
        return new ResponseEntity<>(seller, HttpStatus.CREATED);
    }
}
