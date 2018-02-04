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
public class MethodNotAllowed extends RuntimeException {

    public MethodNotAllowed() {
    }

    public MethodNotAllowed(String message) {
        super(message);
    }

}
