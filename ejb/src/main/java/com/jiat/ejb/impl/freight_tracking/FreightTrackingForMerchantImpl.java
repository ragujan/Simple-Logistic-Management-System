package com.jiat.ejb.impl.freight_tracking;

import com.jiat.core.models.FreightTrackingDataModel;
import com.jiat.ejb.entity.Freight;
import com.jiat.ejb.entity.FreightTracking;
import com.jiat.ejb.entity.Merchant;
import com.jiat.ejb.exception.NoMerchantFoundForRouteException;
import com.jiat.ejb.remote.FreightTrackingForMerchant;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.Stateful;
import jakarta.ejb.TransactionManagement;
import jakarta.ejb.TransactionManagementType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Stateful
@TransactionManagement(TransactionManagementType.CONTAINER)
public class FreightTrackingForMerchantImpl implements FreightTrackingForMerchant {

    @PersistenceContext(unitName = "WebPU")
    private EntityManager em;

    @RolesAllowed({"merchant"})
    @Override
    public List<FreightTrackingDataModel> getFreightTrackingByMerchantOrder(String merchantName) {
//        search merchant by the merchant name
        List<Merchant> merchant = em.createQuery(
                        "SELECT f FROM Merchant f WHERE  f.name=:merchantName"
                        , Merchant.class)
                .setParameter("merchantName", merchantName).getResultList();
        List<FreightTrackingDataModel> models = new ArrayList<>();
        if (merchant.size() != 1) {
            throw new NoMerchantFoundForRouteException("No Merchant found");
        } else {
//            use merchant to get the orders that are in the freights, find them in freight tracking
            Merchant merchant1 = merchant.get(0);
            List<FreightTracking> freightTrackingList = em.createQuery(
                    "SELECT ft FROM FreightTracking ft " +
                            "JOIN ft.freight f " +
                            "JOIN f.freightHasOrders fho " +
                            "JOIN fho.orders o " +
                            "JOIN o.productId p " +
                            "JOIN p.merchantId m " +
                            "WHERE m.name = :merchantName"
                    ,FreightTracking.class).setParameter("merchantName", merchantName).getResultList();
            for (FreightTracking freightTracking : freightTrackingList
            ) {
//              add it to the freight tracking data model
                FreightTrackingDataModel model = new FreightTrackingDataModel();

                model.setCoordinates(freightTracking.getCoordinates());
                model.setFreightId(freightTracking.getFreight().getId());
                model.setFreightProgress(freightTracking.getRouteProgress());
                model.setExpectedDelay(freightTracking.getExpectedDelay());
                model.setFreightTrackingId(freightTracking.getId());
                model.setRouteDestination(freightTracking.getFreight().getRoute().getDestinationId().getDestinationName());
                model.setFreightRoute(freightTracking.getFreight().getRoute().getName());

                models.add(model);
            }
        }
        return models;
    }
}
