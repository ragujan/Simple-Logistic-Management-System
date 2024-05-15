package com.jiat.web.servlet.freight;

import com.jiat.core.models.TransportationDataModel;
import com.jiat.ejb.entity.Route;
import com.jiat.ejb.entity.Transportation;
import com.jiat.ejb.entity.TransportationType;
import com.jiat.ejb.remote.FreightService;
import com.jiat.ejb.remote.TransportationService;
import jakarta.ejb.EJB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/freight-registration")
public class FreightRegistrationServlet extends HttpServlet {

    @EJB
    private FreightService freightService;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Transportation> transportationList = freightService.getAllTransportations();
        List<Route> routeList = freightService.getAllRoutes();

        request.setAttribute("transportationList", transportationList);
        request.setAttribute("routeList", routeList);

        request.getRequestDispatcher("/freight/freight-registration.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String transportationId = request.getParameter("transportation");
        String routeId = request.getParameter("route");
        String weight = request.getParameter("weight");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String eta = request.getParameter("eta");

        System.out.println("Transportation ID: " + transportationId);
        System.out.println("Route ID: " + routeId);
        System.out.println("Weight: " + weight);
        System.out.println("Start Date: " + startDate);
        System.out.println("End Date: " + endDate);
        System.out.println("ETA: " + eta);



    }


}
