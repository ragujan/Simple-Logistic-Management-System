package com.jiat.web.servlet.auth;


import com.jiat.ejb.remote.AdminAuthService;
import com.jiat.ejb.remote.MerchantService;
import jakarta.ejb.EJB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {


    @EJB
    private AdminAuthService adminAuthService;
    @EJB
    private MerchantService merchantService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Dispatch to JSP page
        HttpSession session = request.getSession();
        if (session.getAttribute("admin-login") !=null && (Boolean) request.getAttribute("admin-login")) {
            request.getSession().setAttribute("admin-login", null);
            request.getSession().setAttribute("admin-name", null);
            request.getSession().setAttribute("login", null);
            request.getSession().setAttribute("name", null);
        }
        if (session.getAttribute("login") !=null && (Boolean) request.getSession().getAttribute("login")) {
            request.getSession().setAttribute("login", null);
            request.getSession().setAttribute("name", null);
            request.getSession().setAttribute("admin-login", null);
            request.getSession().setAttribute("admin-name", null);
        }
        request.logout();
        RequestDispatcher dispatcher = request.getRequestDispatcher("/login");
        dispatcher.forward(request, response);
    }
}