package com.jiat.ejb.interceptor;

import com.jiat.ejb.entity.*;
import com.jiat.ejb.exception.NoMatchingFreightForRouteException;
import com.jiat.ejb.remote.OrderInsertionService;
import com.jiat.ejb.remote.ProductService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateful;
import jakarta.ejb.TransactionManagement;
import jakarta.ejb.TransactionManagementType;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.InvocationContext;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import util.DateFormatterUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

//Container managed transaction
public class RouteOptimizerInterceptor {
    @PersistenceContext(unitName = "WebPU")
    private EntityManager em;

    @EJB
    ProductService productService;
    @AroundInvoke
    public Object getPossibleFreights(InvocationContext context) {
        Object[] parameters = context.getParameters();
        String destinationId = (String) parameters[0];
        String expectedDate = (String) parameters[1];
        Object[] newParameters = new Object[parameters.length+1];
        System.arraycopy(parameters,0,newParameters,0,parameters.length);


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

//            after order is created now it's time to add the order to freights
            LocalDateTime orderCreatedDate = createdAt;
            Destination orderDestination = destination;
            List<Route> routes = em.createQuery("SELECT r FROM Route  r where r.destinationId=:destinationId", Route.class).setParameter("destinationId", orderDestination).getResultList();

            routes.forEach(e -> System.out.println("route names are " + e.getName()));
            List<Freight> possibleFreights = new ArrayList<>();

            routes.forEach(route -> {
                try {
                    Freight freight = em.createQuery("SELECT fr FROM Freight fr where fr.route=:firstRoute AND fr.startDate > :orderCreatedDate ", Freight.class)
                            .setParameter("firstRoute", route)
                            .setParameter("orderCreatedDate", orderCreatedDate)
                            .setMaxResults(1).getSingleResult();
                    possibleFreights.add(freight);
                } catch (NoResultException ex) {
                    throw new NoMatchingFreightForRouteException("Route doesn't have a valid freight");
                }

            });
            newParameters[parameters.length] = possibleFreights;
            context.setParameters(newParameters);
            return context.proceed();


        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;

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

    private Destination getDestinationByName(String name) {
        try {
            Destination destination = em.createQuery(
                            "SELECT p FROM Destination p WHERE p.destinationName = :name",
                            Destination.class)
                    .setParameter("name", name).getSingleResult();

            return destination;
        } catch (NoResultException ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
