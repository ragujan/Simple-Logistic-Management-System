package com.jiat.web.servlet;

import com.jiat.ejb.remote.Test2;
import com.jiat.ejb.remote.UserService;
import jakarta.ejb.EJB;
import jakarta.ejb.EJBAccessException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
@WebServlet("/test2")
public class Test2Servlet extends HttpServlet {

    @EJB
    private Test2 test2Bean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            System.out.println("about to call TestBean");
            test2Bean.action();

        }catch (EJBAccessException e){
            response.sendError(403);
        }
    }
}
