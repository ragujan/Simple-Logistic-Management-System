package com.jiat.ejb.interceptor;


import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.InvocationContext;

public class BInterceptor {

    @PostConstruct
    public void init(InvocationContext ic){
        System.out.println("BInterceptor init...");
    }


    @AroundInvoke
    public Object intercept(InvocationContext ic) throws Exception{
        System.out.println("BInterceptor...");

        return  ic.proceed();
    }


    @PreDestroy
    public void destroy(InvocationContext ic){
        System.out.println("BInterceptor destroy...");
    }
}
