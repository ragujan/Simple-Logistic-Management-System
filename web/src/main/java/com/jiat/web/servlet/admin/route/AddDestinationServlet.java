package com.jiat.web.servlet.admin.route;

import com.jiat.ejb.entity.Destination;
import com.jiat.ejb.remote.DestinationService;
import com.jiat.ejb.remote.RouteService;
import jakarta.ejb.EJB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/add-destination")
public class AddDestinationServlet extends HttpServlet {

    @EJB
    DestinationService destinationService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/routes/add-destination.jsp");
        dispatcher.forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");


        if (name != null && !name.isEmpty() ) {
            boolean success = destinationService.addDestination(name);

            if (success) {
                request.setAttribute("success_message", "Destination Added Successfully successfully registered");
                RequestDispatcher dispatcher = request.getRequestDispatcher("success_page/common_page.jsp");
                dispatcher.forward(request, response);
            } else {
                request.setAttribute("error_message", "Failed to register Destination. Please try again.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("error_page/common_page.jsp");
                dispatcher.forward(request, response);
            }
        } else {
            request.setAttribute("error_message", "Name, starting point, and destination point are required.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("error_page/common_page.jsp");
            dispatcher.forward(request, response);
        }
    }
}