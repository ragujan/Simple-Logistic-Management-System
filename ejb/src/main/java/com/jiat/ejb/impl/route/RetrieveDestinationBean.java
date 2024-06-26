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
import java.time.format.DateTimeFormatter;
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
//This process is to retrieve destinations that the destination's all routes are clear and available
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = LocalDateTime.now().format(formatter);
        LocalDateTime currentTime = LocalDateTime.parse(formattedDateTime, formatter);
        List<Freight> freights = em.createQuery("SELECT ff FROM Freight ff " +
                        "INNER JOIN ff.route r " +
                        "INNER JOIN r.destinationId f " +
                        "WHERE ff.hasStarted=:hasStarted AND r.routeOrder=:routeOrder AND ff.startDate>:startingDate", Freight.class)
                .setParameter("hasStarted", false)
                .setParameter("startingDate", currentTime)
                .setParameter("routeOrder", 1).getResultList();
        List<DestinationDataModel> dataModels = new ArrayList<>();
        for (Freight freight : freights
        ) {
            String destinationName = freight.getRoute().getDestinationId().getDestinationName();
            LocalDateTime freightStartingDate = freight.getStartDate();
            String freightId = Integer.toString(freight.getId());
            dataModels.add(new DestinationDataModel(destinationName, freightStartingDate, freightId));
        }

        return dataModels;
//        return em.createQuery("SELECT tt FROM Destination tt", Destination.class)
//                .getResultList();
    }
}
