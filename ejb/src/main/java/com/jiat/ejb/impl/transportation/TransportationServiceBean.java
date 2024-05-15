package com.jiat.ejb.impl.transportation;

import com.jiat.ejb.entity.Transportation;
import com.jiat.ejb.entity.TransportationType;
import com.jiat.ejb.remote.TransportationService;
import jakarta.ejb.Stateful;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionManagement;
import jakarta.ejb.TransactionManagementType;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.UserTransaction;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

//Bean managed transaction
@Stateless
//@Stateful
@TransactionManagement(TransactionManagementType.BEAN)
public class TransportationServiceBean implements TransportationService {

    @PersistenceContext(unitName = "WebPU")
    private EntityManager em;

    @Inject
    private UserTransaction transaction;

    @Override
    public boolean addTransportationType(String name) {
        try {
            transaction.begin();

            TransportationType transportationType = new TransportationType(name);

            em.persist(transportationType);

            transaction.commit();

            System.out.println("Success transportation type");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean addTransportation(String name, String transportationTypeName, Integer maximumWeight) {
        try {
            // Find the TransportationType by name
            Optional<TransportationType> optionalTransportationType = findTransportationTypeByName(transportationTypeName);

            if (optionalTransportationType.isPresent()) {
                TransportationType transportationType = optionalTransportationType.get();

                // Begin transaction
                transaction.begin();

                // Create a new Transportation
                Transportation transportation = new Transportation();
                transportation.setName(name);
                transportation.setTransportationType(transportationType);
                transportation.setMaximumWeight(maximumWeight);

                // Persist the Transportation
                em.persist(transportation);

                // Commit transaction
                transaction.commit();

                return true;
            } else {
                // TransportationType not found
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Transportation> getAllTransportations() {
        return em.createQuery("SELECT t FROM Transportation t", Transportation.class)
                .getResultList();
    }

    @Override
    public List<TransportationType> getAllTransportationTypes() {
        return em.createQuery("SELECT tt FROM TransportationType tt", TransportationType.class)
                .getResultList();
    }

    private Optional<TransportationType> findTransportationTypeByName(String name) {
        return em.createQuery("SELECT tt FROM TransportationType tt WHERE tt.name = :name", TransportationType.class)
                .setParameter("name", name)
                .setMaxResults(1)
                .getResultList()
                .stream()
                .findFirst();
    }
}
