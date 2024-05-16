package com.jiat.ejb.remote;

import com.jiat.ejb.entity.Orders;
import jakarta.ejb.Remote;

@Remote
public interface OrderFreightService {
    public boolean addOrderToFreight(Orders orders);
}
