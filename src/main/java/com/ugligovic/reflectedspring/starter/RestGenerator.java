/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ugligovic.reflectedspring.starter;

import com.ugligovic.reflectedspring.Main;
import com.ugligovic.reflectedspring.classprovider.ApiClassProviderInterface;
import com.ugligovic.reflectedspring.classprovider.ApiClassesProvider;
import com.ugligovic.reflectedspring.injectablelogic.InjectableLogicHolder;

/**
 *
 * @author Uros Gligovic
 */

public class RestGenerator {

    public static void restRun(ApiClassProviderInterface apiClassProvider) {

        ApiClassesProvider.setClassProvider(apiClassProvider);

        String[] argsi = {"1"};
        Main.main(argsi);

    }
    
     public static void restRunWithInjectableLogic(ApiClassProviderInterface apiClassProvider, Object injectableLogic) {

        InjectableLogicHolder.setInjectableLogic(injectableLogic);
        ApiClassesProvider.setClassProvider(apiClassProvider);

        String[] argsi = {"1"};
        Main.main(argsi);

    }

}
