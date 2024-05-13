package com.jiat.ejb.impl.merchant;

import com.jiat.ejb.entity.Merchant;
import com.jiat.ejb.entity.Orders;
import com.jiat.ejb.entity.Product;
import com.jiat.ejb.remote.OrderService;
import com.jiat.ejb.remote.ProductService;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionManagement;
import jakarta.ejb.TransactionManagementType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
//Container managed transaction
@Stateless
//@TransactionManagement(TransactionManagementType.BEAN)
@TransactionManagement(TransactionManagementType.CONTAINER)
public class OrderServiceBean implements OrderService {
    @PersistenceContext(unitName = "WebPU")
    private EntityManager em;




    @EJB
    ProductService productService;

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

    public List<Product> getProductsByMerchantName(String merchantName) {
        TypedQuery<Product> query = em.createQuery(
                "SELECT p FROM Product p WHERE p.merchantId.name = :merchantName",
                Product.class);
        query.setParameter("merchantName", merchantName);
        return query.getResultList();
    }

    public Product getProductsByProductName(String productName) {
        System.out.println("product name is " + productName);
        TypedQuery<Product> query = em.createQuery(
                "SELECT p FROM Product p WHERE p.title = :productName",
                Product.class);
        query.setParameter("productName", productName);
        return query.getSingleResult();
    }


    @Override
    public boolean createOrder(String merchantName, String productName, String qty) {

        Product product = productService.getProductsByProductName(productName);

        // Format LocalDateTime to the specified pattern
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = LocalDateTime.now().format(formatter);

        try {
            Orders orders = new Orders();
            orders.setQty(Integer.parseInt(qty));
            orders.setCreatedAt(LocalDateTime.parse(formattedDateTime,formatter));
            orders.setProductId(product);

            em.persist(orders);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }


        return false;
    }
}
