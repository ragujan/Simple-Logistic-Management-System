package com.jiat.ejb.remote;

import com.jiat.core.models.FreightDataModel;
import com.jiat.ejb.entity.Freight;
import com.jiat.ejb.entity.Route;
import com.jiat.ejb.entity.Transportation;

import java.util.List;

public interface FreightStatusUpdateService {
    public boolean updatedFreightJourneyStatus(String freightId, Boolean statusToBeUpdated);

}
