package com.jiat.ejb.impl;

import com.jiat.ejb.entity.Merchant;
import com.jiat.ejb.remote.Register;
import com.jiat.ejb.remote.RegisterMerchant;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class RegisterMerchantImplBean implements RegisterMerchant {

    @PersistenceContext(unitName = "WebPU")
    private EntityManager em;

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public boolean register(String name, String email, String password) {

        Merchant merchant = new Merchant();
        merchant.setName(name);
        merchant.setEmail(email);
        merchant.setPassword(password);
        em.persist(merchant);

        return false;
    }
}
