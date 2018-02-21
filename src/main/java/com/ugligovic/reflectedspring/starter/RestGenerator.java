/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ugligovic.reflectedspring.starter;

import com.ugligovic.reflectedspring.Main;

/**
 *
 * @author Uros Gligovic
 */

public class RestGenerator {

    public static void restRun() {

        String[] argsi = {};
        Main.main(argsi);

    }
          
      public static void restRunWithCustomPort(String port) {

        String[] argsi = {port};
        Main.main(argsi);

    }

}
