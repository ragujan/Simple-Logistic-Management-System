package com.jiat.ejb.remote;

import com.jiat.core.models.FreightTrackingDataModel;
import com.jiat.ejb.entity.Freight;

public interface FreightTrackingDataGeneration {
    public FreightTrackingDataModel getFreightTracking(Freight freightHasOrders);
}
