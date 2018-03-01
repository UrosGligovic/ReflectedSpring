/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ugligovic.reflectedspring;

import com.google.gson.Gson;
import com.ugligovic.reflectedspring.annotations.ParameterDesc;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import com.ugligovic.reflectedspring.exceptions.BadRequest;
import com.ugligovic.reflectedspring.exceptions.InternalServerError;
import com.ugligovic.reflectedspring.exceptions.MethodNotAllowed;
import com.ugligovic.reflectedspring.exceptions.NotFound;
import com.ugligovic.reflectedspring.injectablelogic.InjectableLogicHolder;
import com.ugligovic.reflectedspring.util.Constants;
import com.ugligovic.reflectedspring.annotations.InjectableLogic;
import com.ugligovic.reflectedspring.classprovider.ApiClassHolder;
import java.lang.reflect.Parameter;

/**
 *
 * @author Uros Gligovic
 */
@Service
public class ReflectionLogic implements ReflectionLogicLocal {

    private static final Logger logger = Logger.getLogger(ReflectionLogic.class);

    public Object processRequest(String clazz, String method, Map<String, String> requestMap) throws IOException {

        Class neededClass = ApiClassHolder.getClassMap().get(clazz);

        if (neededClass == null) {
            logger.error("Unexisting class " + clazz);
            throw new NotFound("Unexisting class " + clazz);
        }

        Method neededMethod = findMethod(method, neededClass);

        if (neededMethod == null) {
            logger.error("Unexisting method " + method + " from class " + clazz);
            throw new NotFound("Unexisting method " + method + " from class " + clazz);
        }

        List<Object> listOfArgs = prepareParameters(neededMethod, requestMap);

        return invokeTheMethod(neededMethod, listOfArgs, neededClass);

    }

    public String getRequestExample(String clazz, String method) throws IOException {

        Class neededClass = ApiClassHolder.getClassMap().get(clazz);

        if (neededClass == null) {
            logger.error("Unexisting class " + clazz);
            throw new NotFound("Unexisting class " + clazz);
        }

        Method neededMethod = findMethod(method, neededClass);

        if (neededMethod == null) {
            logger.error("Unexisting method " + method + " from class " + clazz);
            throw new NotFound("Unexisting method " + method + " from class " + clazz);
        }

        return new Gson().toJson(prepareExampleParameters(neededMethod));

    }

    private Object invokeTheMethod(Method neededMethod, List<Object> listOfArgs, Class neededClass) {
        try {

            return neededMethod.invoke(null, listOfArgs.toArray());

        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
            logger.error("Unsuccessful 1 execution of method " + neededMethod + " from class " + neededClass, ex);
            throw new MethodNotAllowed("Unsuccessful 1 execution of method " + neededMethod + " from class " + neededClass);
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
            logger.error("Unsuccessful 2 execution of method " + neededMethod + " from class " + neededClass, ex);
            throw new BadRequest("Unsuccessful 2 execution of method " + neededMethod + " from class " + neededClass);
        } catch (InvocationTargetException ex) {
            ex.printStackTrace();
            logger.error("Unsuccessful 3 execution of method " + neededMethod + " from class " + neededClass, ex);
            throw new InternalServerError("Unsuccessful 3 execution of method " + neededMethod + " from class " + neededClass + " cause: " + ex.getCause());
        }
    }

    private List<Object> prepareParameters(Method neededMethod, Map<String, String> requestMap) {

        Class[] methodParameterTypes = neededMethod.getParameterTypes();
        // Map<String, String> requestHelpMap = new HashMap<>();
        List<Object> listOfArgs = new ArrayList();
        Parameter[] parameters = neededMethod.getParameters();

        for (int i = 0; i < parameters.length; i++) {

            if (parameters[i].isAnnotationPresent(InjectableLogic.class)) {
                logger.info("type InjectableLogic");
                InjectableLogic paramDesc = parameters[i].getAnnotationsByType(InjectableLogic.class)[0];
                listOfArgs.add(InjectableLogicHolder.getInjectableLogicMap().get(paramDesc.type()));
                
            } else if (parameters[i].isAnnotationPresent(ParameterDesc.class)) {

                ParameterDesc paramDesc = parameters[i].getAnnotationsByType(ParameterDesc.class)[0];

                try {
                    listOfArgs.add(typeConverter(parameters[i].getType(), requestMap.get(paramDesc.name())));
                } catch (ParseException ex) {
                    logger.error("Problem with parsing parameter ", ex);
                    throw new BadRequest("Problem with parsing parameter " + paramDesc.name());
                }

            } else {

                try {
                    listOfArgs.add(typeConverter(parameters[i].getType(), requestMap.get(parameters[i].getName())));
                } catch (ParseException ex) {
                    logger.error("Problem with parsing parameter ", ex);
                    throw new BadRequest("Problem with parsing parameter " + parameters[i].getName());
                }
            }
        }

        return listOfArgs;
    }

    private List<Object> prepareExampleParameters(Method neededMethod) {

        Class[] methodParameterTypes = neededMethod.getParameterTypes();
        Annotation[][] methodParameterAnotations = neededMethod.getParameterAnnotations();

        Map<String, String> requestHelpMap = new HashMap<>();
        List<Object> listOfArgs = new ArrayList();

        for (int i = 0; i < methodParameterTypes.length; i++) {

            for (Annotation annotation : methodParameterAnotations[i]) {

                if (annotation instanceof ParameterDesc) {

                    ParameterDesc paramDesc = (ParameterDesc) annotation;
                    requestHelpMap.put(paramDesc.name(), typeConverterForExample(methodParameterTypes[i]));

                }
            }
        }
        return listOfArgs;
    }

    static Method findMethod(String methodName, Class clazz) {

        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            if (method.getName().equals(methodName) && !method.isAnnotationPresent(Deprecated.class)) {
                return method;
            }
        }

        return null;

    }

    static Object typeConverter(Class targetClass, String value) throws ParseException {

        if (value == null) {
            return null;
        }

        DateFormat dateParser = new SimpleDateFormat(Constants.DATE_FORMAT);

        logger.info(" type " + targetClass.getSimpleName());
        switch (targetClass.getSimpleName()) {

            case "Integer":
            case "int":
                return Integer.valueOf(value);
            case "Double":
            case "double":
                return Double.valueOf(value);
            case "Long":
            case "long":
                return Long.valueOf(value);
            case "Date":
                return dateParser.parse(value);
            case "Boolean":
            case "boolean":
                return Boolean.getBoolean(value);
            case "String":
                return value;
            default:
                return new Gson().fromJson(value, targetClass);

        }

    }

    static String typeConverterForExample(Class targetClass) {

        System.out.println(" type " + targetClass.getSimpleName());
        switch (targetClass.getSimpleName()) {

            case "Integer":
            case "int":
                return "123123";
            case "Double":
            case "double":
                return "1321312.123";
            case "Long":
            case "long":
                return "123123123";
            case "Date":
                return "yyyy-MM-dd HH:mm:ss";
            case "Boolean":
            case "boolean":
                return "true";
            case "String":
                return "stringExample";
            default:
                return "unknown";

        }

    }

}
