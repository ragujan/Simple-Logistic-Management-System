package com.jiat.web.servlet;

import com.jiat.ejb.remote.Register;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    @EJB(lookup = "java:global/ear-1.0/com.jiat-ejb-1.0/RegisterImpl")
    private Register register;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        boolean reg = register.register(name, email, password);
        if (reg){
            response.sendRedirect("index.jsp");
        }
    }

}
