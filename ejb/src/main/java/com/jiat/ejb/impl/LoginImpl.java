package com.jiat.ejb.impl;

import com.jiat.ejb.annotation.Auth;
import com.jiat.ejb.entity.Account;
import com.jiat.ejb.entity.User;
import com.jiat.ejb.interceptor.AInterceptor;
import com.jiat.ejb.interceptor.BInterceptor;
import com.jiat.ejb.interceptor.TestInterceptor;
import com.jiat.ejb.remote.Login;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptors;
import jakarta.interceptor.InvocationContext;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

@Interceptors({TestInterceptor.class, AInterceptor.class, BInterceptor.class})
@Stateless
@Auth
public class LoginImpl implements Login {

//    @Resource(lookup = "jdbc/web_db")
//    private DataSource db;

    @PersistenceContext
    private EntityManager em;


    @Override
    public boolean login(String email, String password) {

        User user = em.createQuery("SELECT u FROM User u WHERE u.email=:email and u.password=:password", User.class)
                .setParameter("email",email)
                .setParameter("password",password)
                .getSingleResult();

        if (user != null){
            List<Account> accounts = user.getAccounts();
            System.out.println(accounts);
        }

       return user != null;
    }
}
