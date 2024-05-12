package com.jiat.ejb.impl;

import com.jiat.ejb.entity.Merchant;
import com.jiat.ejb.entity.Orders;
import com.jiat.ejb.entity.Product;
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

            Merchant merchant = em.find(Merchant.class, 1L); // Assuming merchant id is 1
            Product product = em.find(Product.class, 1L);
            System.out.println("merchant name is "+merchant.getName());
            System.out.println("product name is "+product.getTitle());

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDateTime = LocalDateTime.now().format(formatter);


            Orders orders = new Orders();
            orders.setId(11L);
            orders.setCreatedAt(LocalDateTime.parse(formattedDateTime, formatter));
            orders.setQty(5);
            orders.setProductId(product);

            System.out.println("order product is "+ orders.getProductId().getTitle());

            em.persist(orders);
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
