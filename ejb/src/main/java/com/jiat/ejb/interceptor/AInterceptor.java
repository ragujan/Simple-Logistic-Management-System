package com.jiat.ejb.interceptor;


import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.InvocationContext;

public class AInterceptor {

    @PostConstruct
    public void init(InvocationContext ic){
        System.out.println("AInterceptor init...");
    }


    @AroundInvoke
    public Object intercept(InvocationContext ic) throws Exception{
        System.out.println("AInterceptor...");

        System.out.println(ic.getContextData());

        Object proceed = ic.proceed();
        System.out.println(proceed);

        return proceed;
    }


    @PreDestroy
    public void destroy(InvocationContext ic){
        System.out.println("AInterceptor destroy...");
    }
}
