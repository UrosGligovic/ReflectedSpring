/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ugligovic.reflectedspring.injectablelogic;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Uros Gligovic
 */
public class InjectableLogicHolder {

    private static Map<String,Object> injectableLogicMap = new HashMap<>();

    public static Map<String,Object> getInjectableLogicMap() {
        return InjectableLogicHolder.injectableLogicMap;
    }

    public static void mapTypeToInjectableLogic(String type,Object injectableLogic) {
        InjectableLogicHolder.injectableLogicMap.put(type, injectableLogic);
    }

}
