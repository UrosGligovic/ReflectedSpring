/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ugligovic.reflectedspring;

import java.io.IOException;
import java.util.Map;

/**
 *
 * @author Uros Gligovic
 */
public interface ReflectionLogicLocal {
    
    public Object processRequest(String clazz, String method, String requestMap) throws IOException;
    
}
