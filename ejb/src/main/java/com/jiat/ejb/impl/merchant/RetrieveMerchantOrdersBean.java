package com.jiat.ejb.impl.merchant;

import com.jiat.ejb.entity.Merchant;
import com.jiat.ejb.entity.Orders;
import com.jiat.ejb.interceptor.MerchantInterceptor;
import com.jiat.ejb.remote.RetrieveMerchantOrders;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.Stateful;
import jakarta.ejb.TransactionManagement;
import jakarta.ejb.TransactionManagementType;
import jakarta.interceptor.Interceptors;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Interceptors({MerchantInterceptor.class})
@Stateful
@TransactionManagement(TransactionManagementType.CONTAINER)
public class RetrieveMerchantOrdersBean implements RetrieveMerchantOrders {

    @PersistenceContext(unitName = "WebPU")
    private EntityManager em;
    @RolesAllowed({"merchant"})
    @Override
    public List<Orders> getOrders(String merchantName) {
        try {
            Merchant merchant =  em.createQuery(
                            "SELECT p FROM Merchant p WHERE p.name = :name",
                            Merchant.class)
                    .setParameter("name", merchantName).getSingleResult();

            List<Orders> orders = em.createQuery(
                            "SELECT p FROM Orders p WHERE p.productId.merchantId = :id",
                            Orders.class)
                    .setParameter("id", merchant).getResultList();

            return orders;
        } catch (NoResultException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
