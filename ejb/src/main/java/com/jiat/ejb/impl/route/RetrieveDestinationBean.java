package com.jiat.ejb.impl.route;

import com.jiat.ejb.entity.Destination;
import com.jiat.ejb.remote.RetrieveDestination;
import jakarta.ejb.Stateful;
import jakarta.ejb.TransactionManagement;
import jakarta.ejb.TransactionManagementType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Stateful
@TransactionManagement(TransactionManagementType.CONTAINER)
public class RetrieveDestinationBean implements RetrieveDestination {
    @PersistenceContext(unitName = "WebPU")
    private EntityManager em;
    @Override
    public List<Destination> retrieveDestinations() {
        return em.createQuery("SELECT tt FROM Destination tt", Destination.class)
                .getResultList();
    }
}
