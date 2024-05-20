package com.jiat.ejb.impl;

import com.jiat.ejb.entity.*;
import com.jiat.ejb.remote.DestinationService;
import com.jiat.ejb.remote.Test2;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionManagement;
import jakarta.ejb.TransactionManagementType;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//for testing purposes
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class Test2Bean implements Test2 {
    @PersistenceContext(unitName = "WebPU")
    private EntityManager em;

    @Inject
    private UserTransaction transaction;

    @EJB
    private DestinationService destinationService;


    @Override
    public boolean action() {
        try {
            transaction.begin();
            FreightHasOrders freightHasOrders = new FreightHasOrders();
            Freight freight = em.createQuery("SElECT f FROM Freight f WHERE f.id=:id",Freight.class).setParameter("id",1).getSingleResult();
            Orders orders = em.createQuery("SELECT o FROM Orders o WHERE o.id=:id", Orders.class).setParameter("id",1).getSingleResult();
            freightHasOrders.setFreight(freight);
            freightHasOrders.setOrders(orders);
            em.persist(freightHasOrders);
//            transaction.begin();
//            Merchant merchant = new Merchant();
//            merchant.setName("test 2 test 2");
//
//            Product product = new Product();
//            product.setTitle("new product");
//            product.setWeight("55kg");

//            Destination destination = new Destination();
//            destination.setDestinationName("Shenzhen-Cape Town-Oakland-Texas");
//            em.persist(destination);
//            destinationService.addDestination("Shenzhen-Cape Town-Oakland-Texas");

            transaction.commit();
            System.out.println("hey");

        } catch (Exception e) {
            System.out.println("exception occurred");

            try {
                transaction.rollback();
            } catch (SystemException ex) {
                throw new RuntimeException(ex);
            }
            e.printStackTrace();

        }

        return true;
    }
}
