package org.example.service;

import org.example.entity.Seller;
import org.example.exception.SellerIdNotFoundException;
import org.example.exception.SellerNameExistsException;
import org.example.exception.SellerNameNotFoundException;
import org.example.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SellerService {

    SellerRepository sellerRepository;

    @Autowired
    public SellerService(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    public List<Seller> getAllSellers() {
        return sellerRepository.findAll();

    }

    public List<Seller> getSellerByName(String name) {
        return sellerRepository.findByName(name);
    }

    public Seller getSellerById(Long sellerId) throws SellerIdNotFoundException {
        Optional<Seller> s = sellerRepository.findById(sellerId);
        if (s.isPresent()) {
            return s.get();
        }
        throw new SellerIdNotFoundException("Seller id " + sellerId + " not found.");

    }

    public boolean sellerIdExists(Long sellerId) {
        return sellerRepository.existsById(sellerId);
    }

    public boolean sellerNameExists(String name) {
        return !sellerRepository.findByName(name).isEmpty();
    }

    public Seller saveSeller(Seller s) throws SellerNameExistsException {
        if (!(sellerNameExists(s.getName()))) {
            return sellerRepository.save(s);
        } else {
            throw new SellerNameExistsException("The seller name already exists");
        }
    }
}
