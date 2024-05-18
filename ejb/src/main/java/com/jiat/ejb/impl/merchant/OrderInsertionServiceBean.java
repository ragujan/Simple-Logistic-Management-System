package com.jiat.ejb.impl.merchant;

import com.jiat.ejb.entity.*;
import com.jiat.ejb.exception.NoMatchingFreightForRouteException;
import com.jiat.ejb.remote.OrderInsertionService;
import com.jiat.ejb.remote.ProductService;
import jakarta.ejb.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

//Container managed transaction
@Stateful
@TransactionManagement(TransactionManagementType.CONTAINER)
public class OrderInsertionServiceBean implements OrderInsertionService {
    @PersistenceContext(unitName = "WebPU")
    private EntityManager em;


    @EJB
    ProductService productService;

    @Override
    public boolean createOrder(String destinationId, String merchantName, String productName, String qty, String expectedDate) {

        Product product = productService.getProductsByProductName(productName);
        Destination destination = getDestinationById(destinationId);

//      Defining the formatter for the original data-time format, which received from the user end as a local date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
//      Parse the original date into a LocalDateTime
        LocalDateTime originalDateTime = LocalDateTime.parse(expectedDate, formatter);
//      defining the desired formatter
        DateTimeFormatter desiredFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        format the localdatetime object to the desired formatter. now it will be string verion of the sql datetime.
        String formattedDateTimeString = originalDateTime.format(desiredFormatter);

        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime formattedExpectedDate = LocalDateTime.parse(formattedDateTimeString, formatter);
        String formattedDateTime = LocalDateTime.now().format(formatter);
        LocalDateTime createdAt = LocalDateTime.parse(formattedDateTime, formatter);
        try {
            Orders orders = new Orders();
            orders.setQty(Integer.parseInt(qty));
            orders.setCreatedAt(createdAt);
            orders.setProductId(product);
            orders.setExpectedDate(formattedExpectedDate);
            orders.setDestination(destination);
            orders.setOrderStatus("not_shipped");


            em.persist(orders);

//            after order is created now it's time to add the order to freights
            LocalDateTime orderCreatedDate = orders.getCreatedAt();
            LocalDateTime orderExpectedDate = orders.getExpectedDate();
            Destination orderDestination = orders.getDestination();
            List<Route> routes = em.createQuery("SELECT r FROM Route  r where r.destinationId=:destinationId", Route.class).setParameter("destinationId", orderDestination).getResultList();

            routes.forEach(e -> System.out.println("route names are " + e.getName()));
            List<Freight> possibleFreights = new ArrayList<>();

            routes.forEach(route -> {
                try {
//                    Freight freight = em.createQuery("SELECT fr FROM Freight fr where fr.route=:firstRoute AND fr.startDate > :orderCreatedDate AND fr.endDate <:orderExpectedDate", Freight.class)
                    Freight freight = em.createQuery("SELECT fr FROM Freight fr where fr.route=:firstRoute AND fr.startDate > :orderCreatedDate ", Freight.class)
                            .setParameter("firstRoute", route)
                            .setParameter("orderCreatedDate", orderCreatedDate)
//                            .setParameter("orderExpectedDate", orderExpectedDate)
                            .setMaxResults(1).getSingleResult();
                    possibleFreights.add(freight);
                } catch (NoResultException ex) {
                    throw new NoMatchingFreightForRouteException("Route doesn't have a valid freight");
                }

            });


            if (possibleFreights != null) {
                possibleFreights.forEach(e -> {
                    System.out.println("Freight Ids are " + e.getId());
                    FreightHasOrders freightHasOrders = new FreightHasOrders();
                    freightHasOrders.setOrders(orders);
                    freightHasOrders.setFreight(e);
                    em.persist(freightHasOrders);
                    FreightTracking freightTracking = new FreightTracking();
                    freightTracking.setCoordinates("0 0");
                    freightTracking.setFreight(e);
                    freightTracking.setExpectedDelay("0");
                    freightTracking.setRouteProgress("not started");
                    em.persist(freightTracking);
                });
                return true;
            }



        } catch (Exception ex) {
            ex.printStackTrace();
        }


        return false;
    }

    private Destination getDestinationById(String id) {
        try {
            Destination destination = em.createQuery(
                            "SELECT p FROM Destination p WHERE p.id = :id",
                            Destination.class)
                    .setParameter("id", Integer.parseInt(id)).getSingleResult();

            return destination;
        } catch (NoResultException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public boolean addOrderToAvailableFreight(Orders order) {
        try {


        } catch (Exception ex) {

            ex.printStackTrace();
        }

        return false;
    }

}
