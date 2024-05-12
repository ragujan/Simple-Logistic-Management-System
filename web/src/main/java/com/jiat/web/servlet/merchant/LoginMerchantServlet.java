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
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/login-merchant")
public class LoginMerchantServlet extends HttpServlet {

    @EJB
    private MerchantService merchantService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Dispatch to JSP page
        RequestDispatcher dispatcher = request.getRequestDispatcher("/merchant/merchant-login.jsp");
        dispatcher.forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String name = request.getParameter("name");
            String password = request.getParameter("password");

            System.out.println("Name: " + name);
            System.out.println("Password: " + password);
            boolean login = merchantService.login(name,password);
//            boolean reg = merchantService.register(name, email, password);
            if(login){
//                request.login("merchant","merchant");
                HttpSession session = request.getSession();
                session.setAttribute("login", true);
                session.setAttribute("name",name);
                response.sendRedirect("merchant/merchant-home.jsp");
            }else{
                response.sendRedirect("error_page/merchant-login.jsp");
            }
        }catch (EJBAccessException e){
            response.sendError(403);
        }
    }
}
