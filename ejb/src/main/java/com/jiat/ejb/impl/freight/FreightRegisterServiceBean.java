package com.jiat.ejb.impl.freight;

import com.jiat.core.models.FreightDataModel;
import com.jiat.ejb.entity.Freight;
import com.jiat.ejb.entity.Route;
import com.jiat.ejb.entity.Transportation;
import com.jiat.ejb.remote.FreightRegisterService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionManagement;
import jakarta.ejb.TransactionManagementType;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.SystemException;
import jakarta.transaction.UserTransaction;

import java.util.List;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class FreightRegisterServiceBean implements FreightRegisterService {
    @PersistenceContext(unitName = "WebPU")
    private EntityManager em;

    @Inject
    private UserTransaction transaction;

    @RolesAllowed({"admin"})
    @Override
    public boolean addFreight(FreightDataModel model) {

        try {
            transaction.begin();

            Freight freight = new Freight();

            TypedQuery<Transportation> transportation = em.createQuery(
                    "SELECT p FROM Transportation p WHERE p.id = :id",
                    Transportation.class);
            transportation.setParameter("id", model.getTransportationId());

            TypedQuery<Route> route = em.createQuery(
                    "SELECT p FROM Route p WHERE p.id = :id",
                    Route.class);
            route.setParameter("id", model.getRouteId());


            freight.setTransportation(transportation.getSingleResult());
            freight.setEta(model.getEta());
            freight.setDelivered(true);
            freight.setFailed(false);
            freight.setStartDate(model.getStartDate());
            freight.setEndDate(model.getEndDate());
            freight.setRoute(route.getSingleResult());
            freight.setWeight(Integer.parseInt(model.getWeight()));
            freight.setHasStarted(false);
            em.persist(freight);


            transaction.commit();

            return true;
        } catch (Exception e) {
            try {
                System.out.println("rollback");
                transaction.rollback();
                e.printStackTrace();
            } catch (SystemException ex) {
                throw new RuntimeException(ex);
            }
            return false;
        }

    }
    @RolesAllowed({"admin"})
    @Override
    public List<Freight> getAllFreights() {
        return em.createQuery("SELECT tt FROM Freight tt", Freight.class)
                .getResultList();
    }
    @RolesAllowed("admin")
    @Override
    public List<Route> getAllRoutes() {
        return em.createQuery("SELECT tt FROM Route tt", Route.class)
                .getResultList();
    }

    @RolesAllowed({"admin"})
    @Override
    public List<Transportation> getAllTransportations() {
        return em.createQuery("SELECT tt FROM Transportation tt", Transportation.class)
                .getResultList();
    }
}
