/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ugligovic.reflectedspring.model;

/**
 *
 * @author Uros Gligovic
 */
public class Response {
    
    private String message;
    private Integer responseCode;

    public Response(String message, Integer responseCode) {
        this.message = message;
        this.responseCode = responseCode;
    }

    public Response() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }
    
    
    
}
