package com.jiat.web.servlet.auth;

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

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @EJB
    private AdminAuthService adminAuthService;
    @EJB
    private MerchantService merchantService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Dispatch to JSP page
        RequestDispatcher dispatcher = request.getRequestDispatcher("/auth/login.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String name = request.getParameter("name");
            String password = request.getParameter("password");
            String userType = request.getParameter("userType");


            if (userType.equals("admin")) {
                boolean reg = adminAuthService.login(name, password);
                if (reg) {

                    request.login("admin", "admin");
                    HttpSession session = request.getSession();
                    session.setAttribute("admin-login", true);
                    session.setAttribute("admin-name", name);
//                    response.sendRedirect("admin/admin-home.jsp");
                    response.sendRedirect("admin-home");
                } else {
                    request.setAttribute("error_message", "Admin Login Failed");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("error_page/common_page.jsp");
                    dispatcher.forward(request, response);
                }
            }
            if (userType.equals("merchant")) {
                boolean login = merchantService.login(name,password);
                if(login){
                    request.login("merchant","merchant");
                    HttpSession session = request.getSession();
                    session.setAttribute("login", true);
                    session.setAttribute("name",name);
//                    response.sendRedirect("merchant/merchant-home.jsp");
                    response.sendRedirect("merchant-home");
                }else{
                    response.sendRedirect("error_page/merchant-login.jsp");
                }
            }
            System.out.println("Name: " + name);
            System.out.println("Password: " + password);

        } catch (EJBAccessException e) {
            response.sendError(403);
        }
    }
}
