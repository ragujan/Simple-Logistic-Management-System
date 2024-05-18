package com.jiat.web.servlet.admin.freight;

import com.jiat.core.models.FreightTrackingDataModel;
import com.jiat.ejb.impl.freight_tracking.FreightTrackingDataGenerationImpl;
import com.jiat.ejb.remote.FreightTrackingDataGeneration;
import jakarta.ejb.EJB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/freight-tracking")
public class FreightTrackingServlet extends HttpServlet {


    @EJB
    FreightTrackingDataGeneration freightTrackingDataGeneration;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/freight/freight-tracking.jsp");
        dispatcher.forward(request, response);


    }
}
