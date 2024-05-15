package com.jiat.ejb.remote;

import com.jiat.core.models.FreightDataModel;
import com.jiat.ejb.entity.Freight;
import com.jiat.ejb.entity.Route;
import com.jiat.ejb.entity.Transportation;

import java.util.List;

public interface FreightRegisterService {
    public boolean addFreight(FreightDataModel model);
    public List<Freight> getAllFreights();
    public List<Route> getAllRoutes();
    public List<Transportation> getAllTransportations();
}
