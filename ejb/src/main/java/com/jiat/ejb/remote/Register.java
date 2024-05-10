package com.jiat.ejb.remote;

import jakarta.ejb.Remote;

@Remote
public interface Register {
    public boolean register(String name, String email, String password);
}
