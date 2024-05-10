package com.jiat.ejb.impl;

import com.jiat.ejb.remote.AccountService;
import com.jiat.ejb.remote.TransferService;
import jakarta.ejb.*;
import jakarta.inject.Inject;
import jakarta.transaction.SystemException;
import jakarta.transaction.UserTransaction;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class TransferServiceBean implements TransferService {

    @EJB
    private AccountService accountService;

    @Inject
    private UserTransaction transaction;

    @Override
    //@TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void transferAmount(String sourceAccountNo, String destinationAccountNo, double amount) {
        try {
            transaction.begin();
            accountService.deductToAccount(sourceAccountNo, amount);
            accountService.creditToAccount(destinationAccountNo, amount);
            transaction.commit();
        }catch (Exception e){
            try {
                transaction.rollback();
            } catch (SystemException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
