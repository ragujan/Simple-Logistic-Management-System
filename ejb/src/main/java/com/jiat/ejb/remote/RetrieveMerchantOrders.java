package com.jiat.ejb.remote;

import com.jiat.ejb.entity.Orders;
import jakarta.ejb.Remote;

import java.util.List;

@Remote
public interface RetrieveMerchantOrders {
    public List<Orders> getOrders(String merchantId);
}
