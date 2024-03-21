package org.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.OK, reason = "Product Not Found")
public class ProductNotFoundException extends Exception{
    public ProductNotFoundException(String msg){

        super(msg);
    }
}
