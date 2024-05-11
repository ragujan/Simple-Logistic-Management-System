package com.jiat.ejb.impl;

import com.jiat.ejb.entity.Merchant;
import com.jiat.ejb.entity.User;
import com.jiat.ejb.remote.Register;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class RegisterImpl implements Register {

    @PersistenceContext(unitName = "WebPU")
    private EntityManager em;

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public boolean register(String name, String email, String password) {



//        Merchant merchant = new Merchant();
//        merchant.setName(name);
//        merchant.setId(1L);
//
//        em.persist(merchant);

        return false;
    }
}
