package com.jiat.ejb.impl.merchant;

import com.jiat.ejb.entity.Merchant;
import com.jiat.ejb.entity.Orders;
import com.jiat.ejb.entity.Product;
import com.jiat.ejb.remote.ProductService;
import com.jiat.ejb.remote.Test2;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionManagement;
import jakarta.ejb.TransactionManagementType;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.SystemException;
import jakarta.transaction.UserTransaction;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Stateless
//@TransactionManagement(TransactionManagementType.BEAN)
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ProductBean implements ProductService {
    @PersistenceContext(unitName = "WebPU")
    private EntityManager em;

//    @Inject
//    private UserTransaction transaction;
//
//    @Override
//    public boolean registerProduct(String title, Float weight, String units) {
//
//
//        try {
//            transaction.begin();
//
//            Product product = new Product();
//            product.setTitle(title);
//            product.setWeight(weight);
//            product.setMeasurementUnit(units);
//
//            em.persist(product);
//            transaction.commit();
//
//        } catch (Exception e) {
//            try {
//                System.out.println("rollback");
//                transaction.rollback();
//                e.printStackTrace();
//            } catch (SystemException ex) {
//                throw new RuntimeException(ex);
//            }
//        }
//
//
//        return true;
//    }

    @Override
    public boolean registerProduct(String title, Float weight, String units) {


        try {

            Product product = new Product();
            product.setTitle(title);
            product.setWeight(weight);
            product.setMeasurementUnit(units);

            em.persist(product);

        } catch (Exception e) {
            System.out.println("rollback");
            e.printStackTrace();
        }


        return true;
    }


}
