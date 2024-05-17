package com.jiat.ejb.impl.order;

import com.jiat.ejb.entity.Orders;
import com.jiat.ejb.remote.OrderRetrievalService;
import jakarta.ejb.Stateful;
import jakarta.ejb.TransactionManagement;
import jakarta.ejb.TransactionManagementType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateful
@TransactionManagement(TransactionManagementType.CONTAINER)
public class OrderRetrievalServiceBean implements OrderRetrievalService {

    @PersistenceContext(unitName = "WebPU")
    private EntityManager em;

    @Override
    public Orders retrieveOrderById(String id) {
        return em.createQuery("SELECT f FROM Orders f WHERE f.id=:id"
                        , Orders.class)
                .setParameter("id", Long.parseLong(id))
                .getSingleResult();
    }
}
