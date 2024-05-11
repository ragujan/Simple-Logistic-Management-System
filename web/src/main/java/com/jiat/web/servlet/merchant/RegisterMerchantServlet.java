package com.jiat.web.servlet.merchant;

import com.jiat.ejb.remote.MerchantService;
import jakarta.ejb.EJB;
import jakarta.ejb.EJBAccessException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/register-merchant")
public class RegisterMerchantServlet extends HttpServlet {

    @EJB
    private MerchantService merchantService;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Dispatch to JSP page
        RequestDispatcher dispatcher = request.getRequestDispatcher("/merchant/register-merchant.jsp");
        dispatcher.forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            boolean reg = merchantService.register(name, email, password);
            if(reg){
                response.sendRedirect("success_page/merchant-register.jsp");
            }else{
                response.sendRedirect("error_page/merchant-register.jsp");
            }
        }catch (EJBAccessException e){
            response.sendError(403);
        }
    }
}
