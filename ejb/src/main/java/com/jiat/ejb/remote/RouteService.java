package com.jiat.ejb.remote;

import com.jiat.ejb.entity.Destination;
import com.jiat.ejb.entity.Route;
import jakarta.ejb.Remote;

import java.util.List;

@Remote
public interface RouteService {
    public boolean registerRoute(Destination destination, String name, String startingPoint, String destinationPoint);
    public List<Route> getAllRoutes();

    public boolean registerDestination(String name);
}
