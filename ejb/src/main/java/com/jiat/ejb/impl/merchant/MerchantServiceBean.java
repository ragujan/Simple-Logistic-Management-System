package com.jiat.ejb.impl.merchant;

import com.jiat.ejb.entity.Merchant;
import com.jiat.ejb.remote.MerchantService;
import jakarta.ejb.*;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.SystemException;
import jakarta.transaction.UserTransaction;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class MerchantServiceBean implements MerchantService {

    @PersistenceContext(unitName = "WebPU")
    private EntityManager em;

    @Inject
    private UserTransaction transaction;

    @Override
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

    @Override
    public boolean login(String name, String password) {
        TypedQuery<Merchant> query = em.createQuery("SELECT m FROM Merchant m WHERE m.name = :name", Merchant.class);
        query.setParameter("name", name);
        Merchant merchant = query.getSingleResult();
        System.out.println("merchant name is " + merchant.getName());

        return merchant.getPassword().equals(password);

    }

}
