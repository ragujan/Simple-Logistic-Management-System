package com.jiat.ejb.impl;

import com.jiat.ejb.entity.Merchant;
import com.jiat.ejb.entity.Product;
import com.jiat.ejb.interceptor.AInterceptor;
import com.jiat.ejb.interceptor.BInterceptor;
import com.jiat.ejb.interceptor.TestInterceptor;
import com.jiat.ejb.interceptor.TestInterceptor2;
import com.jiat.ejb.remote.Test2;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionManagement;
import jakarta.ejb.TransactionManagementType;
import jakarta.inject.Inject;
import jakarta.interceptor.Interceptors;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.*;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class Test2Bean implements Test2 {
    @PersistenceContext(unitName = "WebPU")
    private EntityManager em;

    @Inject
    private UserTransaction transaction;

    @Override
    public boolean action() {
        try {
            transaction.begin();
            Merchant merchant = new Merchant();
            merchant.setName("test 2 test 2");

            Product product = new Product();
            product.setTitle("new product");
            product.setWeight("55kg");

            em.persist(merchant);
            em.persist(product);
            transaction.commit();

        } catch (Exception e) {
            try {
                transaction.rollback();
            } catch (SystemException ex) {
                throw new RuntimeException(ex);
            }
        }

        return true;
    }
}
