package com.jiat.ejb.remote;

import com.jiat.ejb.entity.FreightHasOrders;
import com.jiat.ejb.entity.FreightTracking;

public interface FreightTrackingDataGeneration {
    public FreightTracking getFreightTracking(FreightHasOrders freightHasOrders);
}
