package com.jiat.ejb.impl.admin;

import com.jiat.ejb.entity.Admin;
import com.jiat.ejb.entity.Freight;
import com.jiat.ejb.entity.Merchant;
import com.jiat.ejb.remote.AdminAuthService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.Stateful;
import jakarta.ejb.TransactionManagement;
import jakarta.ejb.TransactionManagementType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Stateful
@TransactionManagement(TransactionManagementType.CONTAINER)
public class AdminAuthServiceImpl implements AdminAuthService {


    @PersistenceContext(unitName = "WebPU")
    private EntityManager em;


    @Override
    public boolean login(String name, String password) {

        List<Admin> admins = em.createQuery("SELECT f FROM Admin f WHERE f.name=:name AND f.password=:password", Admin.class)
                .setParameter("name", name).setParameter("password", password).getResultList();

        return admins.size() == 1;
    }
    @RolesAllowed("admin")
    @Override
    public Merchant getAdminByName(String name) {
        return null;
    }
}
