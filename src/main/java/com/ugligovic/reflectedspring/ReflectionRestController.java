/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ugligovic.reflectedspring;

import com.ugligovic.reflectedspring.model.Response;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ugligovic
 */
@RestController
@RequestMapping("/Api")
public class ReflectionRestController {

    @Autowired
    ReflectionLogicLocal reflectionLogic;

    private static final Logger logger = Logger.getLogger(ReflectionRestController.class);

    @RequestMapping(method = RequestMethod.POST, value = "/{class}/{method}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    ResponseEntity executePost(@PathVariable("class") String clazz, @PathVariable("method") String method, @RequestBody Map<String, String> requestMap) {

        try {
            logger.info(requestMap);
            Object response= reflectionLogic.processRequest(clazz, method, requestMap);
            logger.info("Successful execution of method " + method + " from class " + clazz + " with parameters" + requestMap);
            return ResponseEntity.status(HttpStatus.OK).body(response);

        } catch (IOException ex) {
            logger.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Internal error", 500));

        }

    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/{class}/{method}/**", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    ResponseEntity executeGet(@PathVariable("class") String clazz, @PathVariable("method") String method, @RequestParam  Map<String,String> request) {

        try {
            logger.info(request);
            Object response= reflectionLogic.processRequest(clazz, method, request);
            logger.info("Successful execution of method " + method + " from class " + clazz + " with parameters" + request);
            return ResponseEntity.status(HttpStatus.OK).body(response);

        } catch (IOException ex) {
            logger.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Internal error", 500));

        }

    }

}
