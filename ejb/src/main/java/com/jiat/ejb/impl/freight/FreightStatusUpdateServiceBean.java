package com.jiat.ejb.impl.freight;

import com.jiat.core.models.FreightDataModel;
import com.jiat.ejb.entity.Freight;
import com.jiat.ejb.entity.Route;
import com.jiat.ejb.entity.Transportation;
import com.jiat.ejb.exception.SameStatusFoundException;
import com.jiat.ejb.remote.FreightRegisterService;
import com.jiat.ejb.remote.FreightStatusUpdateService;
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

//Bean managed transaction
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class FreightStatusUpdateServiceBean implements FreightStatusUpdateService {
    @PersistenceContext(unitName = "WebPU")
    private EntityManager em;

    @Inject
    private UserTransaction transaction;


    @Override
    public boolean updatedFreightJourneyStatus(String freightId, Boolean statusToBeUpdated) {

        try {
            transaction.begin();


            Freight freight = em.createQuery(
                            "SELECT p FROM Freight p WHERE p.id = :id",
                            Freight.class)
                    .setParameter("id", Integer.parseInt(freightId)).getSingleResult();
            if (freight.isHasStarted() != statusToBeUpdated) {
                freight.setHasStarted(statusToBeUpdated);
            }else{
                throw new SameStatusFoundException("Same Status found");
            }
            em.merge(freight);
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
}
