/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ugligovic.reflectedspring.injectablelogic;

/**
 *
 * @author Uros Gligovic
 */
public class InjectableLogicHolder {

    private static Object injectableLogic;

    public static Object getInjectableLogic() {
        return injectableLogic;
    }

    public static void setInjectableLogic(Object injectableLogic) {
        InjectableLogicHolder.injectableLogic = injectableLogic;
    }

}
