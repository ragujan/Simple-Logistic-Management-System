package com.jiat.ejb.remote;

import com.jiat.core.models.FreightTrackingDataModel;

import java.util.List;

public interface FreightTrackingForMerchant {
    public List<FreightTrackingDataModel> getFreightTrackingByMerchantOrder(String merchantName);
}
