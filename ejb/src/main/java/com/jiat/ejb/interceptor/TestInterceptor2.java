package com.jiat.ejb.interceptor;


import com.jiat.ejb.interceptor_interfaces.Test;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;

@Test
@Interceptor

//for testing purposes

public class TestInterceptor2 {

    @PostConstruct
    public void init(InvocationContext ic){
        System.out.println("AInterceptor init...");
    }


    @AroundInvoke
    public Object intercept(InvocationContext ic) throws Exception{
        System.out.println("Test Interceptor 2 ...");

        System.out.println("Printing out from test interceptor 2");
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
