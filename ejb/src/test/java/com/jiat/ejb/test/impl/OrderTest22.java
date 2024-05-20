package com.jiat.ejb.test.impl;

import com.jiat.ejb.entity.Destination;
import com.jiat.ejb.entity.Orders;
import com.jiat.ejb.entity.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;


import java.time.LocalDateTime;
import java.util.List;

public class OrderTest22 {
    //    @PersistenceContext(unitName = "WebPU")
    private EntityManager em;
    private EntityManagerFactory emf= Persistence.createEntityManagerFactory("WebPU");
    @BeforeAll
    public static void setUpClass() {
    }

    @Test
    public void testOrderCreation() {
        em = emf.createEntityManager();
        if (em.isOpen()) {
            System.out.println("is open");
        }
        List<Destination> destinationList = em.createQuery("SELECT dt FROM Destination dt WHERE dt.id=:id", Destination.class)
                .setParameter("id", 1).getResultList();
        if (destinationList == null) {
            return;
        }
        Destination destination = destinationList.get(0);
        if (destinationList.size() != 1) {
            System.out.println("couldn't find destination");
            return;
        }
        List<Product> productList = em.createQuery("SELECT pdt FROM Product pdt WHERE pdt.id=:id", Product.class)
                .setParameter("id", 1).getResultList();

        if (productList.size() != 1) {
            System.out.println("couldn't find product");
            return;
        }
        Product product = productList.get(0);

        Orders order = new Orders();
        order.setProductId(product);
        order.setDestination(destination);
        order.setQty(10);
        order.setCreatedAt(LocalDateTime.now());
        order.setExpectedDate(LocalDateTime.now().plusDays(5));
        order.setOrderStatus("not_shipped");
        em.persist(order);
        System.out.println("Insertion Success");
    }
}
