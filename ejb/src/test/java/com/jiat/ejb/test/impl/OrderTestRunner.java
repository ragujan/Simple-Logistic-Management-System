package com.jiat.ejb.test.impl;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class OrderTestRunner {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(OrderTest22.class);
        for (Failure failure : result.getFailures()) {
            System.out.println("failure");
            System.out.println(failure.toString());
            failure.getException().printStackTrace();
        }

        System.out.println(result.wasSuccessful());
    }
}
