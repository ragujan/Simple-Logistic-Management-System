package com.jiat.web.servlet.transportation;

import com.jiat.ejb.entity.TransportationType;
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

@WebServlet("/register-transportation")
public class TransportationServlet extends HttpServlet {

    @EJB
    private TransportationService transportationService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Dispatch to JSP page
        List<TransportationType> transportationTypeList = transportationService.getAllTransportationTypes();
        request.setAttribute("transportationTypeList",transportationTypeList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/manage-transportation/register-transportation.jsp");
        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IOException {
        String name = request.getParameter("name");
        String transportationTypeName = request.getParameter("transportationType");
        String maximumWeight = request.getParameter("maximumWeight");

        if (name != null && !name.isEmpty() && transportationTypeName != null && !transportationTypeName.isEmpty()) {
            boolean success = transportationService.addTransportation(name, transportationTypeName,Float.parseFloat(maximumWeight));

            if (success) {
                request.setAttribute("success_message","transportation is added");
                RequestDispatcher dispatcher = request.getRequestDispatcher("success_page/common_page.jsp");
                dispatcher.forward(request, response);
            } else {
                response.getWriter().write("Failed to add Transportation.");
            }
        } else {
            response.getWriter().write("Name and transportationType parameters are required.");
        }
    }
}