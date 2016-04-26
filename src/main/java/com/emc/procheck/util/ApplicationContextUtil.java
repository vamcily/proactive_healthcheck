package com.emc.procheck.util;

import java.util.Collection;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeansException;

/**
 * Application context utility class
 */
@Component
public class ApplicationContextUtil implements ApplicationContextAware
{
    private static ApplicationContext appContext;
    
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        appContext = context;
    }

    /**
     * Looks up bean from bean name
     * 
     * @param name - name of the bean
     * 
     * @return - bean
     */
    public static Object getBean(final String name) {
        return (appContext == null)?null:appContext.getBean(name);
    }

    /**
     * Looks up bean from bean name
     * 
     * @param name - name of the bean
     * @param obj - an argument to the constructor for the bean
     * 
     * @return - bean
     */
    public static Object getBean(final String name, Object obj) {
        return (appContext == null)?null:appContext.getBean(name, obj);
    }

    /**
     * Type-safe looks up bean by class type.
     * 
     * @param name - name of the bean
     * 
     * @return - bean
     */
    public static <T> T getBean(final String name, Class<T> cls) {
        return (appContext == null)?null:appContext.getBean(cls);
    }


    /**
     * Looks up beans from type name. 
     * This method can be used to find all implementing beans from an interface type
     * 
     * @param type - type(interface) of the bean
     * 
     * @return - beans implementing the type
     */
    public static <T> Collection<T> getBeansOfType(final Class<T> type) {
        return (appContext == null)?null:appContext.getBeansOfType(type).values();
    }
}
