package com.jiat.ejb.remote;

import jakarta.ejb.Remote;

@Remote
public interface AccountService {
    public void creditToAccount(String accountId, double amount);
    public void deductToAccount(String accountId, double amount);

}
