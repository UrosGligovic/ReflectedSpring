/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ugligovic.reflectedspring.dummy;

import com.ugligovic.reflectedspring.injectablelogic.InjectableLogicExample;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
/**
 *
 * @author ugligovic
 */
public class DummyOutput implements InjectableLogicExample {

    @Override
    public void addEvent(Object event) {

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        String jsonString = gson.toJson(event);
        System.out.println("Event "+event.getClass().getSimpleName()+" "+ jsonString);
    }

}
