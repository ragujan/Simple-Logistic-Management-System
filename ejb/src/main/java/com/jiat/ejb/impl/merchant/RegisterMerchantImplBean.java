package com.jiat.ejb.impl.merchant;

import com.jiat.ejb.entity.Merchant;
import com.jiat.ejb.entity.Product;
import com.jiat.ejb.remote.Register;
import com.jiat.ejb.remote.RegisterMerchant;
import jakarta.ejb.*;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.SystemException;
import jakarta.transaction.UserTransaction;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class RegisterMerchantImplBean implements RegisterMerchant {

    @PersistenceContext(unitName = "WebPU")
    private EntityManager em;

    @Inject
    private UserTransaction transaction;
    @Override
//    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public boolean register(String name, String email, String password) {



        try {
            transaction.begin();


            Merchant merchant = new Merchant();
            merchant.setName(name);
            merchant.setEmail(email);
            merchant.setPassword(password);
            em.persist(merchant);

            transaction.commit();

        } catch (Exception e) {
            try {
                System.out.println("rollback");
                transaction.rollback();
                e.printStackTrace();
            } catch (SystemException ex) {
                throw new RuntimeException(ex);
            }
        }

        return true;
    }
}
