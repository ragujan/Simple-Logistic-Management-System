package com.jiat.ejb.impl.route;

import com.jiat.ejb.entity.Route;
import com.jiat.ejb.remote.RouteService;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionManagement;
import jakarta.ejb.TransactionManagementType;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.UserTransaction;

//Bean managed transaction
@Stateless
//@Stateful
@TransactionManagement(TransactionManagementType.BEAN)
public class RouteServiceBean implements RouteService {


    @PersistenceContext(unitName = "WebPU")
    private EntityManager em;

    @Inject
    private UserTransaction transaction;
    @Override
    public boolean registerRoute(String name, String startingPoint, String destinationPoint) {
        try {
            // Begin transaction
            transaction.begin();

            // Create a new Route
            Route route = new Route(startingPoint, destinationPoint, name);

            // Persist the Route
            em.persist(route);

            // Commit transaction
            transaction.commit();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
}
