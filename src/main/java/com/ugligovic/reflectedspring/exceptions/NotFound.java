/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ugligovic.reflectedspring.exceptions;

/**
 *
 * @author ugligovic
 */
public class NotFound extends RuntimeException{
    
     
    public NotFound() {
    }

    public NotFound(String message) {
        super(message);
    }
    
}
