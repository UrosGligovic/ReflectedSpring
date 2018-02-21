/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ugligovic.reflectedspring.classprovider;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Uros Gligovic
 */
public class ApiClassHolder {

    private static Map<String, Class> apiMap = new HashMap<>();

    
    public static Map<String, Class> getClassMap() {
        return ApiClassHolder.apiMap;
    }
    
    public static void mapNameToClass(String name, Class clazz){
        apiMap.put(name, clazz);
    }

}
