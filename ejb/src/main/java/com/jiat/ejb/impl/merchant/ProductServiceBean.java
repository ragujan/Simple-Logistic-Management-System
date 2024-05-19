package com.jiat.ejb.impl.merchant;

import com.jiat.ejb.entity.Merchant;
import com.jiat.ejb.entity.Product;
import com.jiat.ejb.remote.ProductService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionManagement;
import jakarta.ejb.TransactionManagementType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;

//Container managed transaction
@Stateless
//@TransactionManagement(TransactionManagementType.BEAN)
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ProductServiceBean implements ProductService {
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
    @RolesAllowed({"merchant"})
    @Override
    public boolean registerProduct(String title, Float weight, String units, String merchantName) {


        try {
            TypedQuery<Merchant> query = em.createQuery("SELECT m FROM Merchant m WHERE m.name = :name", Merchant.class);
            query.setParameter("name", merchantName);
            Merchant merchant = query.getSingleResult();

            Product product = new Product();
            product.setTitle(title);
            product.setWeight(weight);
            product.setMeasurementUnit(units);
            product.setMerchantId(merchant);

            em.persist(product);

        } catch (Exception e) {
            System.out.println("rollback");
            e.printStackTrace();
        }


        return true;
    }

    @Override
    public List<Product> getProductsByMerchantName(String merchantName) {
        TypedQuery<Product> query = em.createQuery(
                "SELECT p FROM Product p WHERE p.merchantId.name = :merchantName",
                Product.class);
        query.setParameter("merchantName", merchantName);
        return query.getResultList();
    }

    @Override
    public Product getProductsByProductName(String productName) {
        System.out.println("product name is " + productName);
        TypedQuery<Product> query = em.createQuery(
                "SELECT p FROM Product p WHERE p.title = :productName",
                Product.class);
        query.setParameter("productName", productName);
        return query.getSingleResult();
    }


}
