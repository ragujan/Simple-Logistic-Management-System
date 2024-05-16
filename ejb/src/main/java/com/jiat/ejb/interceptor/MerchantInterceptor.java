package com.jiat.ejb.interceptor;

import com.jiat.ejb.annotation.Auth;
import com.jiat.ejb.entity.Merchant;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;


@Auth
@Interceptor
public class MerchantInterceptor {

    @PersistenceContext(unitName = "WebPU")
    private EntityManager em;

    @AroundInvoke
    public Object intercept(InvocationContext context) throws Exception {
        Object[] parameters = context.getParameters();
        String merchantName = (String) parameters[0];

        System.out.println("merchant name is "+merchantName);

        Merchant merchant = null;
        try {
            merchant   = em.createQuery(
                    "SELECT u FROM Merchant u WHERE u.name = :name", Merchant.class).setParameter("name", merchantName).getSingleResult();
        } catch (NoResultException e) {
            e.printStackTrace();

        } catch (Exception ex){
            ex.printStackTrace();
        }



        Object result = context.proceed();

        return result;
    }
}
