package com.jiat.ejb.impl.route;

import com.jiat.ejb.entity.Destination;
import com.jiat.ejb.entity.Route;
import com.jiat.ejb.remote.DestinationService;
import com.jiat.ejb.remote.ProductService;
import com.jiat.ejb.remote.RouteService;
import jakarta.ejb.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.stream.IntStream;

//Container managed transaction

@Stateful
@TransactionManagement(TransactionManagementType.CONTAINER)
public class DestinationServiceBean implements DestinationService {
    @PersistenceContext(unitName = "WebPU")
    private EntityManager em;
    @EJB
    RouteService routeService;
    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public boolean addDestination(String name) {
        try {

            Destination destination = new Destination();
            destination.setDestinationName(name);
            em.persist(destination);

            String[] routeNames = name.split("-");

            IntStream.range(0, routeNames.length-1)
                    .forEach(i->{
                        String startingPoint = routeNames[i];
                        String destinationPoint = routeNames[i + 1];
                        String routeName = startingPoint+"-"+destinationPoint;

                        Route route = new Route();
                        route.setStartingPoint(startingPoint);
                        route.setDestinationPoint(destinationPoint);
                        route.setName(routeName);
                        route.setDestinationId(destination);
                        em.persist(route);
                    });

            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
