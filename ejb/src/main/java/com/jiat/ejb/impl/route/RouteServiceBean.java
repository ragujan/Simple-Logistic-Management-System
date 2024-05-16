package com.jiat.ejb.impl.route;

import com.jiat.ejb.entity.Destination;
import com.jiat.ejb.entity.Route;
import com.jiat.ejb.remote.RouteService;
import jakarta.ejb.*;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.UserTransaction;

import java.util.List;

//Container managed transaction
@Stateful
@TransactionManagement(TransactionManagementType.CONTAINER)
public class RouteServiceBean implements RouteService {


    @PersistenceContext(unitName = "WebPU")
    private EntityManager em;

//    @Inject
//    private UserTransaction transaction;

    @Override
//    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public boolean registerRoute(Destination destination, String name, String startingPoint, String destinationPoint) {
        try {
            // Begin transaction
//            transaction.begin();

            // Create a new Route
            Route route = new Route(destination,startingPoint, destinationPoint, name);

            // Persist the Route
            em.persist(route);

            // Commit transaction
//            transaction.commit();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public List<Route> getAllRoutes() {
        return em.createQuery("SELECT tt FROM Route tt", Route.class)
                .getResultList();
    }

    @Override
    public boolean registerDestination(String name) {
        return false;
    }


}
