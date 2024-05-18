package com.jiat.ejb.impl;

import com.jiat.ejb.entity.Account;
import com.jiat.ejb.exception.AccountNotFoundException;
import com.jiat.ejb.exception.InsufficientFundsException;
import com.jiat.ejb.remote.AccountService;
import jakarta.annotation.Resource;
import jakarta.annotation.Resources;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionManagement;
import jakarta.ejb.TransactionManagementType;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.SystemException;
import jakarta.transaction.UserTransaction;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class AccountServiceBean implements AccountService {

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction transaction;

    @Override
    public void creditToAccount(String accountId, double amount) {
        try {
            Account account = em.createNamedQuery("Account.findByAccountId", Account.class).setParameter(1, accountId).getSingleResult();
            if (account != null) {
                double balance = account.getBalance();
                if (amount > 0) {
                    account.setBalance(balance + amount);

                    try{
                        transaction.begin();
                        em.merge(account);
                        transaction.commit();
                    }catch (Exception e){
                        try {
                            transaction.rollback();
                        } catch (SystemException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
            } else {
                throw new AccountNotFoundException("Account not found!");
            }
        }catch (NoResultException e){
            throw new AccountNotFoundException("Account not found!");
        }
    }

    @Override
    public void deductToAccount(String accountId, double amount) {
        System.out.println(transaction);
        try {
            Account account = em.createNamedQuery("Account.findByAccountId", Account.class).setParameter(1, accountId).getSingleResult();
            if (account != null) {
                double balance = account.getBalance();
                if (balance >= amount) {
                    account.setBalance(balance - amount);
                    try {
                        transaction.begin();
                        em.merge(account);
                        transaction.commit();
                    }catch (Exception e){
                        try {
                            transaction.rollback();
                        } catch (SystemException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                } else {
                    throw new InsufficientFundsException("Insufficient funds in the account");
                }
            } else {
                throw new AccountNotFoundException("Account not found!");
            }
        }catch (NoResultException e){
            throw new AccountNotFoundException("Account not found!");
        }
    }
}
