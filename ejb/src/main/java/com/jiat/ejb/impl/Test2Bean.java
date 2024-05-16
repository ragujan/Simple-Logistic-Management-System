package com.jiat.ejb.impl;

import com.jiat.ejb.entity.*;
import com.jiat.ejb.remote.Test2;
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

    @Override
    public boolean action() {
        try {
            transaction.begin();
//            Merchant merchant = new Merchant();
//            merchant.setName("test 2 test 2");
//
//            Product product = new Product();
//            product.setTitle("new product");
//            product.setWeight("55kg");

            Destination destination = new Destination();
            destination.setDestinationName("Shenzhen-Cape Town-Oakland-Texas");
            em.persist(destination);
            Route route1 = new Route();
            route1.setName("Shenzen-Cape Town");
            route1.setStartingPoint("Shenzen");
            route1.setDestinationPoint("CapeTown");
            route1.setDestinationId(destination);
            em.persist(route1);

            Route route2 = new Route();
            route2.setName("Cape Town - Oakland");
            route2.setStartingPoint("Cape Town");
            route2.setDestinationPoint("Oakland");
            route2.setDestinationId(destination);
            em.persist(route2);

            Route route3 = new Route();
            route3.setName("Oakland - Texas");
            route3.setStartingPoint("Oakland");
            route3.setDestinationPoint("Texas");
            route3.setDestinationId(destination);
            em.persist(route3);



            transaction.commit();
            System.out.println("hey");

        } catch (Exception e) {
            System.out.println("exception occurred");
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
