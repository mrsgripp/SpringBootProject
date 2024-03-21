package org.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "No Seller Exists With Provided Seller ID")
public class SellerNotFoundException extends Exception{
    public SellerNotFoundException(String msg){
        super(msg);
    }
}
