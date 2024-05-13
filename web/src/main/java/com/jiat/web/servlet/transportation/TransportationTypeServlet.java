package com.jiat.web.servlet.transportation;

import com.jiat.ejb.entity.Product;
import com.jiat.ejb.remote.TransportationService;
import jakarta.ejb.EJB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/register-transportation-type")
public class TransportationTypeServlet extends HttpServlet {

    @EJB
    private TransportationService transportationService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Dispatch to JSP page
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/manage-transportation/register-transportation-type.jsp");
        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IOException {
        String name = request.getParameter("name");

        if (name != null && !name.isEmpty()) {
            boolean success = transportationService.addTransportationType(name);

            if (success) {
                response.getWriter().write("TransportationType successfully added.");
            } else {
                response.getWriter().write("Failed to add TransportationType.");
            }
        } else {
            response.getWriter().write("Name parameter is required.");
        }
    }
}