package com.jiat.web.servlet;

import com.jiat.ejb.remote.Login;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @EJB(lookup = "java:global/ear-1.0/com.jiat-ejb-1.0/LoginImpl")
    private Login login;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String email = req.getParameter("email");
        String password = req.getParameter("password");

        boolean log = login.login(email, password);

        if (log){
            req.login("superadmin","123");
            resp.getWriter().write("Login: "+": success");
        }else{

            resp.getWriter().write("Login: "+": failed");
        }

        HttpSession session = req.getSession();
        session.setAttribute("login", true);
        session.setAttribute("email", email);

        System.out.println("ok ok login servlet success");
        resp.sendRedirect("account.jsp");


//        resp.getWriter().write("Login: "+log);
    }
}
