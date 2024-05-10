package com.jiat.ejb.interceptor;


import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.annotation.Resource;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.InvocationContext;

import javax.ejb.SessionContext;
import java.lang.reflect.Method;
import java.util.Map;

public class TestInterceptor {

    @PostConstruct
    public void init(InvocationContext ic){
        System.out.println("TestInterceptor init...");
    }


    @AroundInvoke
    public Object intercept(InvocationContext ic) throws Exception{
        System.out.println("TestInterceptor...");

        Map<String, Object> contextData = ic.getContextData();
        System.out.println(contextData);

        contextData.put("name", "ABCD");

        Object proceed = ic.proceed();
        System.out.println(proceed);

        return proceed;
    }


    @PreDestroy
    public void destroy(InvocationContext ic){
        System.out.println("TestInterceptor destroy...");
    }
}
