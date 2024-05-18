package com.jiat.ejb.impl.freight;

import com.jiat.ejb.entity.Freight;
import com.jiat.ejb.remote.FreightRetrieval;
import jakarta.ejb.Stateful;
import jakarta.ejb.TransactionManagement;
import jakarta.ejb.TransactionManagementType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.NotFoundException;

@Stateful
@TransactionManagement(TransactionManagementType.CONTAINER)
public class FreightRetrievalImpl implements FreightRetrieval {

    @PersistenceContext(unitName = "WebPU")
    private EntityManager em;

    @Override
    public Integer getFreightDestinationIdByFreightId(String id) {
        try {
            Freight freight = em.createQuery("SELECT f FROM Freight f WHERE f.id=:id", Freight.class)
                    .setParameter("id", Integer.parseInt(id)).getSingleResult();
            return freight.getRoute().getDestinationId().getId();
        } catch (NotFoundException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}