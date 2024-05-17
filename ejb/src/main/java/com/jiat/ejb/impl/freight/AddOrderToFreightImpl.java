package com.jiat.ejb.impl.freight;

import com.jiat.ejb.entity.*;
import com.jiat.ejb.remote.AddOrderToFreight;
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

    @Override
    public boolean addOrderToAvailableFreight(Orders order) {
        try {
            System.out.println("hey Hey add order to available freight");
            LocalDateTime orderCreatedDate = order.getCreatedAt();
            LocalDateTime orderExpectedDate = order.getExpectedDate();
//
            Destination orderDestination = order.getDestination();
            List<Route> routes = em.createQuery("SELECT r FROM Route  r where r.destinationId=:destinationId", Route.class).setParameter("destinationId", orderDestination).getResultList();

            routes.forEach(e -> System.out.println("route names are " + e.getName()));
            List<Freight> possibleFreights = new ArrayList<>();

            routes.forEach(route -> {
                Freight freight = em.createQuery("SELECT fr FROM Freight fr where fr.route=:firstRoute AND fr.startDate > :orderCreatedDate AND fr.endDate <:orderExpectedDate", Freight.class)
                        .setParameter("firstRoute", route)
                        .setParameter("orderCreatedDate", orderCreatedDate)
                        .setParameter("orderExpectedDate", orderExpectedDate)
                        .getSingleResult();
//                Freight freight = em.createQuery("SELECT fr FROM Freight fr where fr.route=:firstRoute AND fr.startDate > :orderCreatedDate AND fr.endDate <:orderExpectedDate", Freight.class)
//                        .setParameter("firstRoute", route).setParameter("orderCreatedDate", orderCreatedDate).setParameter("orderExpectedDate", orderExpectedDate).getSingleResult();
                if (freight != null) {
                    possibleFreights.add(freight);
                }
            });



            if (possibleFreights != null) {
                possibleFreights.forEach(e -> {
                    System.out.println("Freight Ids are " + e.getId());
                    FreightHasOrders freightHasOrders = new FreightHasOrders();
                    freightHasOrders.setOrders(order);
                    freightHasOrders.setFreight(e);
                    em.persist(freightHasOrders);

                });


            }


//          freight end date
//          shouldn't be higher than order expected date
//          freight end date 05/05/2024 > order expected date 01/05/2024 => not valid
//          freight end date 05/05/2024 < order expected date 08/05/2024 => valid


        } catch (Exception ex) {
            ex.printStackTrace();
        }


        return false;
    }
}
