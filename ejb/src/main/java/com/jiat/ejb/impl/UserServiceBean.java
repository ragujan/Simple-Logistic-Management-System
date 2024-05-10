package com.jiat.ejb.impl;

import com.jiat.ejb.remote.UserService;
import jakarta.annotation.security.DenyAll;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.Stateless;

@Stateless
public class UserServiceBean implements UserService {

    @PermitAll
    @Override
    public void method1() {
        System.out.println("UserServiceBean: method1...");
    }

    @DenyAll
    @Override
    public void method2() {
        System.out.println("UserServiceBean: method2...");
    }

    @RolesAllowed("superadmin")
    @Override
    public void method3() {
        System.out.println("UserServiceBean: method3...");
    }

    @RolesAllowed({"superadmin","admin"})
    @Override
    public void method4() {
        System.out.println("UserServiceBean: method4...");
    }

    @RolesAllowed({"superadmin","admin","user"})
    @Override
    public void method5() {
        System.out.println("UserServiceBean: method5... all threee users can access");
    }
}
