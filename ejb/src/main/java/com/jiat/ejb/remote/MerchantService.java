package com.jiat.ejb.remote;

import com.jiat.ejb.entity.Merchant;
import jakarta.ejb.Remote;

@Remote
public interface MerchantService {
    public boolean register(String name, String email, String password);
    public boolean login(String name ,String password);

    public Merchant getMerchantByName(String name);
}
