/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ugligovic.reflectedspring.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author ugligovic
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Not Found")
public class NotFound extends RuntimeException{
    
     
    public NotFound() {
    }

    public NotFound(String message) {
        super(message);
    }
    
}
