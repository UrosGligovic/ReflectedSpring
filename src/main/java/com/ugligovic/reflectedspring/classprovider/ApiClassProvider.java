/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ugligovic.reflectedspring.classprovider;

import com.ugligovic.reflectedspring.classprovider.ApiClassProviderInterface;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Uros Gligovic
 */
public class ApiClassProvider implements ApiClassProviderInterface {

    private  Map<String, Class> apiMap = new HashMap<>();

    @Override
    public Map<String, Class> getClassMap() {
        return apiMap;
    }
    
    public void mapNameToClass(String name, Class clazz){
        apiMap.put(name, clazz);
    }

}
