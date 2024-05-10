package com.jiat.ejb.remote;

import jakarta.ejb.Remote;

@Remote
public interface Login {
    public boolean login(String email, String password);
}
