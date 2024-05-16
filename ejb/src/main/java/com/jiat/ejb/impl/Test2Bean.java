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
            destinationService.addDestination("Shenzhen-Cape Town-Oakland-Texas");

//            transaction.commit();
            System.out.println("hey");

        } catch (Exception e) {
            System.out.println("exception occurred");

//                transaction.rollback();
            e.printStackTrace();

        }

        return true;
    }
}
