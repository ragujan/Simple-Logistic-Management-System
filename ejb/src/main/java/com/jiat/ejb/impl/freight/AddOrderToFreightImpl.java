package com.jiat.ejb.impl.freight;

import com.jiat.ejb.entity.*;
import com.jiat.ejb.remote.AddOrderToFreight;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.Stateful;
import jakarta.ejb.TransactionManagement;
import jakarta.ejb.TransactionManagementType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Stateful
@TransactionManagement(TransactionManagementType.CONTAINER)
public class AddOrderToFreightImpl implements AddOrderToFreight {

    @PersistenceContext(unitName = "WebPU")
    private EntityManager em;

    @RolesAllowed({"merchant"})
    @Override
    public boolean addOrderToAvailableFreight(Orders order) {
        try {
//
            LocalDateTime orderCreatedDate = order.getCreatedAt();
            LocalDateTime orderExpectedDate = order.getExpectedDate();
//
            Destination orderDestination = order.getDestination();
//          get Routes of the order destination
//          one Destination many routes
            List<Route> routes = em.createQuery("SELECT r FROM Route  r where r.destinationId=:destinationId", Route.class).setParameter("destinationId", orderDestination).getResultList();

//          process to identify the possible routes that could match the order's expectations
            List<Freight> possibleFreights = new ArrayList<>();
//          Freight Search on route, freight start date, freight end date
            routes.forEach(route -> {
                Freight freight = em.createQuery("SELECT fr FROM Freight fr where fr.route=:firstRoute AND fr.startDate > :orderCreatedDate ", Freight.class)
                        .setParameter("firstRoute", route)
                        .setParameter("orderCreatedDate", orderCreatedDate)
                        .getSingleResult();
                if (freight != null) {
//                    add it to the freight list
                    possibleFreights.add(freight);
                }
            });

            if (possibleFreights != null) {
//                time to add the freight and the order to the Freight Has Orders table, since we know there are possible freights
                possibleFreights.forEach(e -> {
                    System.out.println("Freight Ids are " + e.getId());
                    FreightHasOrders freightHasOrders = new FreightHasOrders();
                    freightHasOrders.setOrders(order);
                    freightHasOrders.setFreight(e);
                    em.persist(freightHasOrders);

                });


            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return false;
    }
}
