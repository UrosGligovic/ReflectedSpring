/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ugligovic.reflectedspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;

/**
 *
 * @author ugligovic
 */
@SpringBootApplication
public class Main {
    
    public static void main(String[] args) {
        
        if(args.length>0){
            System.getProperties().put( "server.port", args[0] );
        }
        
        SpringApplication.run(Main.class, args);
    }
    
}
