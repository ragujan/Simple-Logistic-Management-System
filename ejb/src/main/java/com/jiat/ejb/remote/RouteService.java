package com.jiat.ejb.remote;

import jakarta.ejb.Remote;

@Remote
public interface RouteService {
    public boolean registerRoute(String name,String startingPoint, String destinationPoint);
}
