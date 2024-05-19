package com.jiat.web.servlet.admin.auth;

import com.jiat.ejb.remote.AdminAuthService;
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

@WebServlet("/admin-login")
public class LoginAdminServlet extends HttpServlet {

    @EJB
    private AdminAuthService adminAuthService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Dispatch to JSP page
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/login.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String name = request.getParameter("name");
            String password = request.getParameter("password");

            System.out.println("Name: " + name);
            System.out.println("Password: " + password);
            boolean reg = adminAuthService.login(name, password);
            if (reg) {

                request.login("admin", "admin");
                HttpSession session = request.getSession();
                session.setAttribute("admin-login", true);
                session.setAttribute("admin-name", name);
                response.sendRedirect("admin/admin-home.jsp");
            } else {
                request.setAttribute("error_message", "Admin Login Failed");
                RequestDispatcher dispatcher = request.getRequestDispatcher("error_page/common_page.jsp");
                dispatcher.forward(request, response);
            }
        } catch (EJBAccessException e) {
            response.sendError(403);
        }
    }
}
