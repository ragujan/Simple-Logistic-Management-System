package com.jiat.ejb.remote;

import com.jiat.ejb.entity.Freight;

import java.util.List;

public interface FreightRetrieval {
    //    get the freight's destination id using the freight
    public Integer getFreightDestinationIdByFreightId(String id);
    public List<Freight> getAllFreights();
}
