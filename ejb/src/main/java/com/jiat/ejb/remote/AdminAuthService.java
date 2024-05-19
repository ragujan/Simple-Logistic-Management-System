package com.jiat.ejb.remote;

import com.jiat.ejb.entity.Merchant;
import jakarta.ejb.Remote;

@Remote
public interface AdminAuthService {
    public boolean login(String name ,String password);

    public Merchant getAdminByName(String name);
}
