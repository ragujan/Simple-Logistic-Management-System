package com.jiat.ejb.remote;

import jakarta.ejb.Remote;

@Remote
public interface MerchantService {
    public boolean register(String name, String email, String password);
    public boolean login(String name ,String password);
}
