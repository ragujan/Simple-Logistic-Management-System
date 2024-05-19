package com.jiat.ejb.impl.route;

import com.jiat.core.models.DestinationDataModel;
import com.jiat.ejb.entity.Destination;
import com.jiat.ejb.entity.Freight;
import com.jiat.ejb.remote.RetrieveDestination;
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
public class RetrieveDestinationBean implements RetrieveDestination {
    @PersistenceContext(unitName = "WebPU")
    private EntityManager em;
    @RolesAllowed({"merchant"})
    @Override
    public List<DestinationDataModel> retrieveDestinations() {
        System.out.println("runinng the retrieve detination method");
//         List<Destination> destinations = em.createQuery("SELECT dd FROM Destination  dd " +
//                        "          INNER JOIN  dd.routeSet r " +
//                        "          INNER JOIN Freight ff ON ff.route= r " +
//                        "          WHERE ff.hasStarted=:hasStarted AND r.routeOrder=:routeOrder", Destination.class)
//                .setParameter("hasStarted", false)
//                .setParameter("routeOrder", 1)
//                .getResultList();
        List<Freight> freights = em.createQuery("SELECT ff FROM Freight ff " +
                        "INNER JOIN ff.route r " +
                        "INNER JOIN r.destinationId f " +
                        "WHERE ff.hasStarted=:hasStarted AND r.routeOrder=:routeOrder", Freight.class)
                .setParameter("hasStarted", false)
                .setParameter("routeOrder", 1).getResultList();
        List<DestinationDataModel> dataModels = new ArrayList<>();
        for (Freight freight: freights
             ) {
            String destinationName = freight.getRoute().getDestinationId().getDestinationName();
            LocalDateTime freightStartingDate = freight.getStartDate();
            String freightId = Integer.toString(freight.getId());
            dataModels.add(new DestinationDataModel(destinationName,freightStartingDate,freightId));
        }

        return dataModels;
//        return em.createQuery("SELECT tt FROM Destination tt", Destination.class)
//                .getResultList();
    }
}
