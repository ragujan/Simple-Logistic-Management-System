package com.jiat.web.servlet.merchant;

import com.jiat.ejb.remote.RegisterMerchant;
import com.jiat.ejb.remote.UserService;
import jakarta.ejb.EJB;
import jakarta.ejb.EJBAccessException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/register-merchant")
public class RegisterMerchantServlet extends HttpServlet {

    @EJB
    private RegisterMerchant registerMerchant;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            boolean reg = registerMerchant.register(name, email, password);
            if(reg){
                response.sendRedirect("index.jsp");
            }
        }catch (EJBAccessException e){
            response.sendError(403);
        }
    }
}
