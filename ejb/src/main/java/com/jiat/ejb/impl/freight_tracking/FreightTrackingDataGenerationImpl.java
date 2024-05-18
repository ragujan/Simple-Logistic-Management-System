package com.jiat.ejb.impl.freight_tracking;

import com.jiat.ejb.entity.FreightHasOrders;
import com.jiat.ejb.entity.FreightTracking;
import com.jiat.ejb.remote.FreightTrackingDataGeneration;
import jakarta.ejb.Stateful;
import jakarta.ejb.TransactionManagement;
import jakarta.ejb.TransactionManagementType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.Random;

@Stateful
@TransactionManagement(TransactionManagementType.CONTAINER)
public class FreightTrackingDataGenerationImpl implements FreightTrackingDataGeneration {

    @PersistenceContext(unitName = "WebPU")
    private EntityManager em;

    @Override
    public FreightTracking getFreightTracking(FreightHasOrders freightHasOrders) {
        FreightTracking freightTracking = em.createQuery("SELECT f FROM FreightTracking f WHERE f.freightHasOrders=:freightHasOrders", FreightTracking.class)
                .setParameter("freightHasOrders", freightHasOrders)
                .getSingleResult();
        freightTracking.setCoordinates(Integer.toString(new Random().nextInt(500)));

        return null;
    }
}
