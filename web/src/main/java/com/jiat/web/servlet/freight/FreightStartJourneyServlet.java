package com.jiat.web.servlet.freight;

import com.jiat.ejb.remote.FreightRegisterService;
import com.jiat.ejb.remote.FreightStatusUpdateService;
import jakarta.ejb.EJB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/update-freight-journey-status")
public class FreightStartJourneyServlet extends HttpServlet {

    @EJB
    private FreightStatusUpdateService freightStatusUpdateService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String freightId = request.getParameter("freight_id");
        Boolean hasStartedStatus = Boolean.parseBoolean(request.getParameter("has_journey_started"));

        System.out.println("has started "+ hasStartedStatus);

        boolean statusToBeUpdated = !hasStartedStatus;

        boolean updatedFreightJourneyStatus = freightStatusUpdateService.updatedFreightJourneyStatus(freightId,statusToBeUpdated);
        if(updatedFreightJourneyStatus){
            request.setAttribute("success_message", "Freight journey started status successfully changed");
            RequestDispatcher dispatcher = request.getRequestDispatcher("success_page/common_page.jsp");
            dispatcher.forward(request, response);
        } else {
            request.setAttribute("error_message", "Failed to update Freight journey status. Please try again.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("error_page/common_page.jsp");
            dispatcher.forward(request, response);
        }


    }


}
