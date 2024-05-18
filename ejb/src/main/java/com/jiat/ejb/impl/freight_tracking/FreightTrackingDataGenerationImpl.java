package com.jiat.ejb.impl.freight_tracking;

import com.jiat.core.models.FreightTrackingDataModel;
import com.jiat.ejb.entity.Freight;
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
    public FreightTrackingDataModel getFreightTracking(Freight freight) {
        FreightTracking freightTracking = em.createQuery("SELECT f FROM FreightTracking f WHERE f.freight=:freight", FreightTracking.class)
                .setParameter("freight", freight)
                .getSingleResult();
        freightTracking.setCoordinates(Integer.toString(new Random().nextInt(500)));
        String progress = freightTracking.getRouteProgress();
        if(progress.equals("not started")){
            freightTracking.setRouteProgress("20%");
        }
        if (progress.equals("20%")) {
            freightTracking.setRouteProgress("40%");
            progress = "40%";
        }

        if (progress.equals("40%")) {
            freightTracking.setRouteProgress("60%");
            progress = "60%";
        }

        if (progress.equals("60%")) {
            freightTracking.setRouteProgress("80%");
            progress = "80%";
        }

        if (progress.equals("80%")) {
            freightTracking.setRouteProgress("100%");
            progress = "100%";
            freightTracking.getFreight().setDelivered(true);
        }
        freightTracking.getFreight().setHasStarted(true);

        em.merge(freightTracking);

        FreightTrackingDataModel model = new FreightTrackingDataModel();

        model.setCoordinates(freightTracking.getCoordinates());
        model.setFreightId(freight.getId());
        model.setFreightProgress(freightTracking.getRouteProgress());
        model.setExpectedDelay(freightTracking.getExpectedDelay());
        model.setFreightTrackingId(freightTracking.getId());
        model.setRouteDestination(freight.getRoute().getDestinationId().getDestinationName());
        model.setFreightRoute(freight.getRoute().getName());

        return model;
    }
}
