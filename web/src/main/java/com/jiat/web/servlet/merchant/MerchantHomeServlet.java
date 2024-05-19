package com.jiat.web.servlet.merchant;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/merchant-home")
public class MerchantHomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("login") == null) {
            request.getSession().setAttribute("name", "Test");
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/merchant/merchant-home.jsp");
        dispatcher.forward(request, response);
    }
}
