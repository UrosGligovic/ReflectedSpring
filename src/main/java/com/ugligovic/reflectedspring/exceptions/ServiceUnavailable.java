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
@ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE, reason = "Service Unavailable")
public class ServiceUnavailable  extends RuntimeException{
    
     
    public ServiceUnavailable() {
    }

    public ServiceUnavailable(String message) {
        super(message);
    }
    
}
