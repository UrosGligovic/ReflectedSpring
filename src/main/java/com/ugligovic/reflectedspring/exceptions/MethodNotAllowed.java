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
 * @author Uros Gligovic
 */
@ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED, reason = "Method Not Allowed")
public class MethodNotAllowed extends RuntimeException {

    public MethodNotAllowed() {
    }

    public MethodNotAllowed(String message) {
        super(message);
    }

}
