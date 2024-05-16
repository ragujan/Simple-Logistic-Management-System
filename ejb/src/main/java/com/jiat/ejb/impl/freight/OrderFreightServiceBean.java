package com.jiat.ejb.impl.freight;

import com.jiat.ejb.entity.Freight;
import com.jiat.ejb.entity.Orders;
import com.jiat.ejb.remote.OrderFreightService;

import java.time.LocalDateTime;

public class OrderFreightServiceBean implements OrderFreightService {
    @Override
    public boolean addOrderToFreight(Orders orders) {
        LocalDateTime expectedArrivalTime = orders.getExpectedDate();

        return false;
    }
}
