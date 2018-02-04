/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ugligovic.reflectedspring.classprovider;

import java.util.Map;

/**
 *
 * @author Uros Gligovic
 */
public interface ApiClassProviderInterface {
    
    public  Map<String,Class> getClassMap();
    
}
