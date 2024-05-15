package com.jiat.ejb.remote;

import com.jiat.ejb.entity.Route;
import com.jiat.ejb.entity.Transportation;
import jakarta.ejb.Remote;

import java.util.List;

@Remote
public interface RouteService {
    public boolean registerRoute(String name,String startingPoint, String destinationPoint);
    public List<Route> getAllRoutes();
}
